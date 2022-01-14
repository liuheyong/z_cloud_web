package com.cloud.web.loadbalance.lb;

import com.cloud.web.loadbalance.entity.LBDto;
import com.cloud.web.loadbalance.entity.RoundRobinWeighted;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 平滑加权轮询负载均衡
 */
public class SmoothRoundRobinLoadBalance implements LoadBalance {

    public static final String NAME = "smoothroundrobin";
    // 嵌套 Map 结构，存储的数据结构示例如下：
    // {
    //     "LBDto.id": {
    //         "LBDto.id": WeightedRoundRobin@123,
    //         "LBDto.id": WeightedRoundRobin@456
    //     }
    // }
    private static final ConcurrentMap<String, ConcurrentMap<String, RoundRobinWeighted>> srrWeightMap = new ConcurrentHashMap<>();
    // 原子更新锁
    private static final AtomicBoolean updateLock = new AtomicBoolean();
    // 回收周期
    private static int RECYCLE_PERIOD = 60000;

    /**
     * 通过负载均衡算法在lbDtos列表中调度选择
     *
     * @param hashArguments 根据hashArguments进行hash(一致性hash时必传)
     * @param lbDtos        待选择实体列表
     */
    @Override
    public LBDto dolbSelect(List<LBDto> lbDtos, String hashArguments) {
        String key = lbDtos.get(0).getId();
        // 获取 key 到 WeightedRoundRobin 映射表，如果为空，则创建一个新的
        ConcurrentMap<String, RoundRobinWeighted> map = srrWeightMap.get(key);
        if (map == null) {
            srrWeightMap.putIfAbsent(key, new ConcurrentHashMap<>());
            map = srrWeightMap.get(key);
        }

        int totalWeight = 0;
        long maxCurrent = Long.MIN_VALUE;
        // 获取当前时间
        long now = System.currentTimeMillis();
        // 选中的lbDto
        LBDto selectedLBDto = null;
        // 选中的lbDto的RoundRobinWeighted
        RoundRobinWeighted selectedWRR = null;
        for (LBDto lbDtoItem : lbDtos) {
            String identifyString = lbDtoItem.getId();
            RoundRobinWeighted weightedRoundRobin = map.get(identifyString);
            int weight = lbDtoItem.getWeight();
            // 检测当前 LBDto 是否有对应的 WeightedRoundRobin，没有则创建
            if (weightedRoundRobin == null) {
                weightedRoundRobin = new RoundRobinWeighted();
                // 初始化 LBDto 权重
                weightedRoundRobin.setWeight(weight);
                // 存储 唯一标识 identifyString 到 weightedRoundRobin 的映射关系
                map.putIfAbsent(identifyString, weightedRoundRobin);
                weightedRoundRobin = map.get(identifyString);
            }
            // 动态权重  ⭐️
            // LBDto 权重不等于 WeightedRoundRobin 中保存的权重，说明权重变化了，此时进行更新
            if (weight != weightedRoundRobin.getWeight()) {
                weightedRoundRobin.setWeight(weight);
            }
            // 让 current 加上自身权重，等价于 current += weight
            long cur = weightedRoundRobin.increaseCurrent();
            // 设置 lastUpdate，表示近期更新过
            weightedRoundRobin.setLastUpdate(now);
            // 找出最大的 current
            if (cur > maxCurrent) {
                maxCurrent = cur;
                // 将具有最大 current 权重的 LBDto 赋值给 selectedInvoker
                selectedLBDto = lbDtoItem;
                // 将 LBDto 对应的 weightedRoundRobin 赋值给 selectedWRR，留作后用
                selectedWRR = weightedRoundRobin;
            }
            // 计算权重总和
            totalWeight += weight;
        }

        // 过期删除  ⭐️
        // 对 <identifyString, WeightedRoundRobin> 进行检查，过滤掉长时间未被更新的LBDto。
        // LBDtos 中不包含该节点，该LBDto可能已经被踢出了，不再参与负载均衡，所以该节点的 lastUpdate 长时间无法被更新。
        // 若未更新时长超过阈值后，就会被移除掉，默认阈值为60秒。
        if (!updateLock.get() && lbDtos.size() != map.size()) {
            if (updateLock.compareAndSet(false, true)) {
                try {
                    // 拷贝
                    ConcurrentMap<String, RoundRobinWeighted> newMap = new ConcurrentHashMap<>(map);
                    // 遍历修改，即移除过期记录
                    newMap.entrySet().removeIf(item -> now - item.getValue().getLastUpdate() > RECYCLE_PERIOD);
                    // 更新引用
                    srrWeightMap.put(key, newMap);
                } finally {
                    updateLock.set(false);
                }
            }
        }

        if (selectedLBDto != null) {
            // 让 current 减去权重总和，等价于 current -= totalWeight
            selectedWRR.sel(totalWeight);
            // 返回具有最大 current 的 LBDto
            return selectedLBDto;
        }

        // 通常不会执行到这里
        return lbDtos.get(ThreadLocalRandom.current().nextInt(lbDtos.size()));
    }
}
