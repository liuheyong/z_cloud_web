package com.cloud.web.loadbalance.service;

import com.cloud.web.loadbalance.entity.LBDto;
import com.cloud.web.loadbalance.enums.LBTypeEnum;

import java.util.List;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 负载均衡调度 业务插件
 */
public interface PluginLoadBalanceService {

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
    LBDto lbSelect(List<LBDto> lbDtos, LBTypeEnum lbType, String hashArguments);
}
