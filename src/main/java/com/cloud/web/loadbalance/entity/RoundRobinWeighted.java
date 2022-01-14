package com.cloud.web.loadbalance.entity;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: heyongliu
 * @date: 2022/1/7
 * @description: 平滑加权轮询负载均衡的辅助类
 */
public class RoundRobinWeighted {

    // 服务提供者权重(固定的)
    private int weight;
    // 当前权重(动态调整, 初始值为0)
    private AtomicLong current = new AtomicLong(0);
    // 最后一次更新时间
    private long lastUpdate;

    public long increaseCurrent() {
        // current = current + weight；
        return current.addAndGet(weight);
    }

    public void sel(int total) {
        // current = current - total;
        current.addAndGet(-1 * total);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        // 初始情况下，current = 0
        current.set(0);
    }

    public AtomicLong getCurrent() {
        return current;
    }

    public void setCurrent(AtomicLong current) {
        this.current = current;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
