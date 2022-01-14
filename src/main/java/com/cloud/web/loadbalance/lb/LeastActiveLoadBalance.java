package com.cloud.web.loadbalance.lb;

import com.cloud.web.loadbalance.entity.LBDto;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 加权最少连接负载平衡
 */
public class LeastActiveLoadBalance implements LoadBalance {

    public static final String NAME = "leastactive";
    //活跃数缓存
    private static final ConcurrentMap<String, ConcurrentMap<String, AtomicInteger>> leActiveMap = new ConcurrentHashMap<>();

    /**
     * 通过负载均衡算法在lbDtos列表中调度选择
     *
     * @param hashArguments 根据hashArguments进行hash(一致性hash时必传)
     * @param lbDtos        待选择实体列表
     */
    @Override
    public LBDto dolbSelect(List<LBDto> lbDtos, String hashArguments) {
        String key = lbDtos.get(0).getId();
        // 获取 key 到 活跃数 映射表，如果为空，则创建一个新的
        ConcurrentMap<String, AtomicInteger> map = leActiveMap.get(key);
        if (map == null) {
            leActiveMap.putIfAbsent(key, new ConcurrentHashMap<>());
            map = leActiveMap.get(key);
        }
        // lbDtos的数量
        int length = lbDtos.size();
        // 存储lbDtos中最少连接
        int leastActive = -1;
        // 具有相同“最小活跃数”的数量
        int leastCount = 0;
        // 具有相同“最小活跃数”在lbDtos的下标，
        int[] leastIndexs = new int[length];
        // 具有相同“最小活跃数”的总权重
        int totalWeight = 0;
        // 第一个最小活跃数的 权重值
        int firstWeight = 0;
        // 是否所有具有相同“最小活跃数”的权重都相等
        boolean sameWeight = true;
        for (int i = 0; i < length; i++) {
            LBDto lbDto = lbDtos.get(i);
            String identifyString = lbDto.getId();
            AtomicInteger curActive = map.get(identifyString);
            if (curActive == null) {
                map.putIfAbsent(identifyString, new AtomicInteger());
                curActive = map.get(identifyString);
            }
            // Active number
            int active = curActive.get();
            // Weight
            int weight = lbDto.getWeight();
            // 找到最小活动值
            if (leastActive == -1 || active < leastActive) {
                leastActive = active; // 使用当前活跃数 active 更新最小活跃数 leastActive
                leastCount = 1; // 更新 leastCount 为 1
                leastIndexs[0] = i; // 记录当前下标值到 leastIndexs 中
                totalWeight = weight; // 记录总权重
                firstWeight = weight; // 记录上一个invoker权重
                sameWeight = true; // 记录invoker权重是否相等
            } else if (active == leastActive) {
                leastIndexs[leastCount++] = i; // 在 leastIndexs 中记录下当前 lbDto 在集合中的下标
                totalWeight += weight; // 累加权重
                // 权重不都相等则将 sameWeight 置为 false
                if (sameWeight && weight != firstWeight) {
                    sameWeight = false;
                }
            }
        }
        // 当只有一个最小活跃数
        if (leastCount == 1) {
            // 直接返回
            LBDto returnDto = lbDtos.get(leastIndexs[0]);
            map.get(returnDto.getId()).incrementAndGet();
            return returnDto;
        }
        // 有多个 具有相同的最小活跃数，但它们之间的权重不同
        if (!sameWeight && totalWeight > 0) {
            // 随机
            int offsetWeight = ThreadLocalRandom.current().nextInt(totalWeight);
            for (int i = 0; i < leastCount; i++) {
                int leastIndex = leastIndexs[i];
                offsetWeight -= lbDtos.get(leastIndex).getWeight();
                if (offsetWeight < 0) {
                    LBDto returnDto = lbDtos.get(leastIndex);
                    map.get(returnDto.getId()).incrementAndGet();
                    return returnDto;
                }
            }
        }
        // 如果活跃数都相同，随机返回一个
        LBDto returnDto = lbDtos.get(leastIndexs[ThreadLocalRandom.current().nextInt(leastCount)]);
        map.get(returnDto.getId()).incrementAndGet();
        return returnDto;
    }
}
