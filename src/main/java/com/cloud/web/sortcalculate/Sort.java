package com.cloud.web.sortcalculate;

import java.util.Arrays;

/**
 * @author: HeYongLiu
 * @create: 10-24-2019
 * @description: 排序
 **/
public class Sort {

    static int[] arr = {12, 32, 3, 6, 74, 23, 62, 884};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maoPao(arr)));
    }

    private static int[] maoPao(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }

}
