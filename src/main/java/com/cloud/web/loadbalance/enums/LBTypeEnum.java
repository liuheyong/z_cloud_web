package com.cloud.web.loadbalance.enums;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 负载均衡类型枚举
 */
public enum LBTypeEnum {

    /**
     * 加权随机
     */
    RANDOM,
    /**
     * 加权轮询(一般的轮询方式)
     */
    ROUND_ROBIN,
    /**
     * 平滑加权轮询
     */
    SMOOTH_ROUND_ROBIN,
    /**
     * 最少活跃连接
     */
    LEAST_ACTIVE,
    /**
     * 一致性哈希
     */
    CONSISTENT_HASH
}
