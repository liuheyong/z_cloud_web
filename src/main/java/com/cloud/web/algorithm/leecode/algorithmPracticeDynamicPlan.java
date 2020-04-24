package com.cloud.web.algorithm.leecode;

import java.util.Stack;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 动态规划
 */
public class algorithmPracticeDynamicPlan {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 买卖股票的最佳时机（可以1次交易）
     */
    public int maxProfit1(int[] prices) {
        if (prices.length == 0)
            return 0;
        int minNum = prices[0];
        int index = 0;
        int earn = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minNum) {
                minNum = prices[i];
                index = i;
            }
            int temp = prices[i] - minNum;
            if (temp > earn)
                earn = temp;
        }
        return earn;
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 买卖股票的最佳时机（可以多次交易）
     */
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int gap = 0;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i + 1 >= prices.length) {
                continue;
            }
            gap = prices[i + 1] - prices[i];    //相邻两天的收益
            if (gap > 0) {                    //如果大于0
                profit += gap;              //加到总收益中
            }
        }
        return profit;
    }

    /**
    * @Date:  2020-04-23
    * @Param:
    * @return:
    * @Description:  打家劫舍
    */
    public static int rob(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 0) {
            return nums[0];
        }
        int[] C = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                C[i] = nums[i];

            }
            if (i == 1) {
                C[i] = Math.max(nums[0], nums[1]);
            }
            if (i >= 2) {
                C[i] = Math.max(C[i - 1], nums[i] + C[i - 2]);
            }
        }
        return C[C.length - 1];
    }


    static int[] a = {2,7,9,3,1};

    public static void main(String[] args) throws Exception {
        System.out.println(rob(a));
    }

}
