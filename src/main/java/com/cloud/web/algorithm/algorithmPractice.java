package com.cloud.web.algorithm;

import java.util.Arrays;

/**
 * @author: LiuHeYong
 * @create: 2020-04-02
 * @description:
 */
public class algorithmPractice {

    /**
    * @Date:  2020-04-02
    * @Description: 数组去重(打乱原来数组的顺序)
    */
    public static int removeDuplicates01(int a[]) {
        int n = a.length;
        int p = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {

                if (a[i] == a[j]) {
                    a[j] = a[j] + a[n - 1];
                    a[n - 1] = a[j] - a[n - 1];
                    a[j] = a[j] - a[n - 1];
                    n--;
                    p++;
                }
            }
        }
        String str = "[";
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                str += a[i] + ",";
            } else {
                str += a[i];
            }
        }
        str += "]";
        System.out.print(str);
        return 1;
    }
    /**
     * @Date:  2020-04-02
     * @Description: 数组去重(保留原来数组的大小顺序)
     */
    public static int removeDuplicates02(int a[]) {
        int n = a.length;
        int p = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (a[i] == a[j]) {
                    for (int k = j; k < n - 1; k++) {
                        a[k] = a[k + 1];
                    }
                    n--;
                    p++;
                }
            }
        }
        String str = "[";
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                str += a[i] + ",";
            } else {
                str += a[i];
            }
        }
        str += "]";
        System.out.print(str);
        System.out.println();
        return 1;
    }

    static int[] a1 = {41, 121, -12, 41, 81, -23, 81, -87, 87, 14};
    static Integer[] a2 = {133, 121, 81, 14, 534, -23, 87, -87, 81, 14};
    static char[] A = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
    static char[] B = {'B', 'D', 'C', 'A', 'B', 'A'};

    public static void main(String[] args) {

        //removeDuplicates02(a1);

        //System.out.println(removeDuplicates01(a1));

    }
}
