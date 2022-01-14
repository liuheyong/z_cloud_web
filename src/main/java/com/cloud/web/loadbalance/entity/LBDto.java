package com.cloud.web.loadbalance.entity;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 负载均衡实体
 */
public class LBDto {

    /**
     * 业务标识id(必填且唯一)
     */
    private String id;
    /**
     * 权重(必填且大于0)
     */
    private int weight;
    /**
     * LBDto之间需保证唯一(一致性hash时才使用, 将LBDto hash到hash环上)
     */
    private String hostPort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public String toString() {
        return "LBDto{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                '}';
    }
}
