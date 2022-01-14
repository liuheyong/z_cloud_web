package com.cloud.web.loadbalance.lb;

import com.cloud.web.loadbalance.entity.LBDto;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: heyongliu
 * @date: 2022/1/4
 * @description: 加权随机负载均衡
 */
public class RandomLoadBalance implements LoadBalance {

    public static final String NAME = "random";

    /**
     * 通过负载均衡算法在lbDtos列表中调度选择
     *
     * @param hashArguments 根据hashArguments进行hash(一致性hash时必传)
     * @param lbDtos        待选择实体列表
     */
    @Override
    public LBDto dolbSelect(List<LBDto> lbDtos, String hashArguments) {
        // lbDto的数量
        int length = lbDtos.size();
        // 总权重
        int totalWeight = 0;
        // 标明是否每个dto的权重值都相等
        boolean sameWeight = true;
        for (int i = 0; i < length; i++) {
            int weight = lbDtos.get(i).getWeight();
            // 计算总权重
            totalWeight += weight;
            // 除了第一个之外判断每一个是否和前一个相等
            if (sameWeight && i > 0 && weight != lbDtos.get(i - 1).getWeight()) {
                sameWeight = false;
            }
        }
        if (totalWeight > 0 && !sameWeight) {
            // 如果所有的dto权重值不都相等，从总权重值中随机取一个值
            int offset = ThreadLocalRandom.current().nextInt(totalWeight);
            // 循环让 offset 数减去服务提供者权重值，当 offset 小于0时，返回相应的dto
            for (LBDto lbDto : lbDtos) {
                offset -= lbDto.getWeight();
                if (offset < 0) {
                    return lbDto;
                }
            }
        }
        // 如果所有的dto的权重都相等，从list中随机返回一个
        return lbDtos.get(ThreadLocalRandom.current().nextInt(length));
    }
}
