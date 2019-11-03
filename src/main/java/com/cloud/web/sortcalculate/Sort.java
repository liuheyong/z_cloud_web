package com.cloud.web.sortcalculate;

import java.util.Arrays;

/**
 * @author: HeYongLiu
 * @create: 10-24-2019
 * @description: 排序
 **/
public class Sort {

    static int[] arr = {12, 4, 3, 6, 74, 23, 62, 884};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(chaRu(arr)));
    }

    /**
     * @date: 2019/10/25
     * @description: 插入排序
     */
    private static int[] chaRu(int[] arr) {
        int len = arr.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }

    /**
     * @date: 2019/10/25
     * @description: 选择排序
     */
    private static int[] xuanZe(int[] arr) {
        int len = arr.length;
        int minIndex, temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {    // 寻找最小的数
                    minIndex = j;                // 将最小数的索引保存
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

    /**
     * @date: 2019/10/25
     * @description: 冒泡排序
     */
    private static int[] maoPao(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (a[j] > a[j + 1]) {          // 相邻元素两两对比
                    int temp = a[j + 1];        // 元素交换
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }

}
