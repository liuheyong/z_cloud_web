package com.cloud.web.loadbalance.lb;

import com.cloud.web.loadbalance.entity.AtomicPositiveInteger;
import com.cloud.web.loadbalance.entity.LBDto;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 加权轮询负载均衡(一般的轮询方式 : 不支持动态权重和过期删除)
 */
public class RoundRobinLoadBalance implements LoadBalance {

    public static final String NAME = "roundrobin";

    //调用序号(value取值为lbDtos的最大权重的取余范围)
    private static final ConcurrentMap<String, AtomicPositiveInteger> sequencesMap = new ConcurrentHashMap<>();
    //下标缓存(value取值为lbDtos的下标范围)
    private static final ConcurrentMap<String, AtomicPositiveInteger> indexSecsMap = new ConcurrentHashMap<>();

    /**
     * 通过负载均衡算法在lbDtos列表中调度选择
     *
     * @param hashArguments 根据hashArguments进行hash(一致性hash时必传)
     * @param lbDtos        待选择实体列表
     */
    @Override
    public LBDto dolbSelect(List<LBDto> lbDtos, String hashArguments) {
        String key = lbDtos.get(0).getId();
        int length = lbDtos.size();
        int maxWeight = Integer.MIN_VALUE;
        int minWeight = Integer.MAX_VALUE;

        // 查找最大和最小权重
        for (LBDto lbDto : lbDtos) {
            int weight = lbDto.getWeight();
            maxWeight = Math.max(maxWeight, weight);
            minWeight = Math.min(minWeight, weight);
        }

        // 获取当前key对应的调用序列对象 AtomicPositiveInteger
        AtomicPositiveInteger sequence = sequencesMap.get(key);
        if (sequence == null) {
            // 创建 AtomicPositiveInteger，默认值为0
            sequencesMap.putIfAbsent(key, new AtomicPositiveInteger());
            sequence = sequencesMap.get(key);
        }

        // 获取当前key对应的下标序列对象 AtomicPositiveInteger
        AtomicPositiveInteger indexSeq = indexSecsMap.get(key);
        if (indexSeq == null) {
            // 创建 AtomicPositiveInteger，默认值为 -1
            indexSecsMap.putIfAbsent(key, new AtomicPositiveInteger(-1));
            indexSeq = indexSecsMap.get(key);
        }

        // 权重不都相等，进行负载选择
        if (maxWeight > 0 && minWeight < maxWeight) {
            while (true) {
                int index = indexSeq.incrementAndGet() % length;
                int currentWeight = sequence.get() % maxWeight;
                // 只要每循环一轮(index = 0), 重新计算 currentWeight+1
                if (index == 0) {
                    currentWeight = sequence.incrementAndGet() % maxWeight;
                }
                // 检测 Dto 的权重是否大于 currentWeight，大于则命中返回
                if (lbDtos.get(index).getWeight() > currentWeight) {
                    return lbDtos.get(index);
                }
            }
        }
        // 所有 权重相等，此时进行普通的轮询即可
        return lbDtos.get(sequence.incrementAndGet() % length);
    }
}
