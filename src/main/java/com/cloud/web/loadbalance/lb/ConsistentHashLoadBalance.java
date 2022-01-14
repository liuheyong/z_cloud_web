package com.cloud.web.loadbalance.lb;

import com.cloud.web.loadbalance.entity.ConsistentHashSelector;
import com.cloud.web.loadbalance.entity.LBDto;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 一致性哈希负载平衡
 */
public class ConsistentHashLoadBalance implements LoadBalance {

    private static final ConcurrentMap<String, ConsistentHashSelector> selectorsMap = new ConcurrentHashMap<>();

    /**
     * 通过负载均衡算法在lbDtos列表中调度选择
     *
     * @param hashArguments 根据hashArguments进行hash(一致性hash时必传)
     * @param lbDtos        待选择实体列表
     */
    @Override
    public LBDto dolbSelect(List<LBDto> lbDtos, String hashArguments) {
        String key = lbDtos.get(0).getId();
        // 获取一致性hash选择器
        ConsistentHashSelector selector = selectorsMap.get(key);
        // 获取 lbDtos 原始的 hashcode
        int identityHashCode = System.identityHashCode(lbDtos);
        // 若不存在则创建新的选择器
        if (selector == null || selector.identityHashCode != identityHashCode) {
            // 创建新的 ConsistentHashSelector
            selectorsMap.put(key, new ConsistentHashSelector(lbDtos, identityHashCode));
            selector = selectorsMap.get(key);
        }
        // 调用 ConsistentHashSelector 的 select 方法进行选择
        return selector.select(hashArguments);
    }
}
