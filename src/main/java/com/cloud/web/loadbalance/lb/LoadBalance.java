package com.cloud.web.loadbalance.lb;

import com.cloud.web.loadbalance.entity.LBDto;

import java.util.List;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 负载均衡接口
 */
public interface LoadBalance {

    /**
     * 通过负载均衡算法在lbDtos列表中调度选择
     *
     * @param lbDtos        待选择实体列表
     * @param hashArguments 根据hashArguments进行hash(一致性hash时才使用)
     */
    LBDto dolbSelect(List<LBDto> lbDtos, String hashArguments);
}
