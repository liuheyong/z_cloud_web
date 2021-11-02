package com.cloud.web.algorithm.test;

/**
 * @author: heyongliu
 * @date: 2021/11/1
 * @description:
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {21, 33, 54, 9, 11};
        System.out.println(binarySearch(a, 54));
    }

    public static int binarySearch(int[] a, int target) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (target == a[middle]) {
                return middle;
            } else if (target < a[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
