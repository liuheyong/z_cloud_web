package com.cloud.web.algorithm.muke;

import java.util.*;

/**
 * @author: LiuHeYong
 * @create: 2020-04-13
 * @description: 双基准快速排序算法
 */
public class DualPivotQuickSortAlgorithm {

    /**
    * @Date:  2020-04-13
    * @Description: 双基准快速排序--选取 A[left], A[right] 两个数为主元
    */
    public static void dualPivotQuickSort(int[] A, int left, int right) {
        if (right - left >= 1) {
            int p = minTwoNum(A[left], A[right]);
            int q = maxTwoNum(A[left], A[right]);
            int ℓ = left + 1;
            int g = right - 1;
            int k = ℓ;
            while (k <= g) {
                if (A[k] < p) {
                    A[k] = A[k] ^ A[ℓ];
                    A[ℓ] = A[k] ^ A[ℓ];
                    A[k] = A[k] ^ A[ℓ];
                    ℓ = ℓ + 1;
                } else if (A[k] >= q) {
                    while (A[g] > q && k < g) {
                        g = g - 1;
                    }
                    A[k] = A[k] ^ A[g];
                    A[g] = A[k] ^ A[g];
                    A[k] = A[k] ^ A[g];
                    g--;
                    if (A[k] < p) {
                        A[k] = A[k] ^ A[ℓ];
                        A[ℓ] = A[k] ^ A[ℓ];
                        A[k] = A[k] ^ A[ℓ];
                        ℓ = ℓ + 1;
                    }
                }
                k++;
            }
            ℓ--;
            g++;
            A[left] = A[ℓ];
            A[ℓ] = p;
            A[right] = A[g];
            A[g] = q;
            dualPivotQuickSort(A, left, ℓ - 1);
            dualPivotQuickSort(A, ℓ + 1, g - 1);
            dualPivotQuickSort(A, g + 1, right);
        }
    }

    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    private static int maxTwoNum(int a, int b) {
        return a > b ? a : b;
    }

    private static int minTwoNum(int a, int b) {
        return a < b ? a : b;
    }

    static int[] a = {133, 121, -12, 14, 534, -23, 87, -87, 81, 41};

    public static void main(String[] args) {

        dualPivotQuickSort(a, 0, 9);
        System.out.println(Arrays.toString(a));

    }

}
