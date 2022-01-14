package com.cloud.web.loadbalance.service.impl;

import com.cloud.web.loadbalance.entity.LBDto;
import com.cloud.web.loadbalance.enums.LBTypeEnum;
import com.cloud.web.loadbalance.lb.*;
import com.cloud.web.loadbalance.service.PluginLoadBalanceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 负载均衡调度 业务插件
 */
@Service
public class PluginLoadBalanceServiceImpl implements PluginLoadBalanceService {

    /**
     * 通过负载均衡算法在lbDtos列表中调度选择
     *
     * @param lbDtos        待选择实体列表(必填)
     *                      id 业务标识id(必填切唯一)
     *                      weight 权重(必填切大于0)
     *                      hostPort LBDto之间需保证唯一(一致性hash时才使用, 将LBDto hash到hash环上使用)
     * @param lbType        负载均衡类型(必填)
     * @param hashArguments 根据hashArguments进行hash(一致性hash时必填)
     */
    @Override
    public LBDto lbSelect(List<LBDto> lbDtos, LBTypeEnum lbType, String hashArguments) {
        // 待选择实体列表无可选数据，无需进行负载均衡
        if (lbDtos.size() == 0)
            return null;
        // 如果 lbDtos 列表中仅有一个，直接返回即可，无需进行负载均衡
        if (lbDtos.size() == 1)
            return lbDtos.get(0);
        // 负载均衡
        LoadBalance loadBalance;
        switch (lbType) {
            case RANDOM:
                loadBalance = new RandomLoadBalance();
                break;
            case ROUND_ROBIN:
                loadBalance = new RoundRobinLoadBalance();
                break;
            case SMOOTH_ROUND_ROBIN:
                loadBalance = new SmoothRoundRobinLoadBalance();
                break;
            case LEAST_ACTIVE:
                loadBalance = new LeastActiveLoadBalance();
                break;
            case CONSISTENT_HASH:
                loadBalance = new ConsistentHashLoadBalance();
                break;
            default:
                loadBalance = new RandomLoadBalance();
        }
        return loadBalance.dolbSelect(lbDtos, hashArguments);
    }
}