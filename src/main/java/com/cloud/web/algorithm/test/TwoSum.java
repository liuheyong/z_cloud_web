package com.cloud.web.algorithm.test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: heyongliu
 * @date: 2021/11/1
 * @description: 两个数之和问题
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] a = {21, 33, 54, 9, 11};
        System.out.println(Arrays.toString(twoSum(a, 32)));
    }

    public static int[] twoSum(int[] a, int target) {
        if (a == null || a.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int num = target = a[i];
            if (map.get(num) != null) {
                return new int[]{map.get(num), i};
            } else {
                map.put(a[i], i);
            }
        }
        return null;
    }
}
