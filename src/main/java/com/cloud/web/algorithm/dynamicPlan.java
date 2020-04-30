package com.cloud.web.algorithm;

import java.util.Arrays;

/**
 * @author: LiuHeYong
 * @create: 2020-03-30
 * @description: 动态规划篇
 */
public class dynamicPlan {

    /**
     * @Date: 2020-03-30
     * @Description: 最长公共子序列问题
     */
    public static void longestCommonSubSequence(char[] A, char[] B) {
        int n = A.length;
        int m = B.length;
        int[][] C = new int[n + 1][m + 1];
        int[][] Rec = new int[n + 1][m + 1];
        //初始化
        for (int i = 0; i <= n; i++) {
            C[i][0] = 0;
            Rec[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            C[0][i] = 0;
            Rec[0][i] = 0;
        }
        //字底向上开始计算，封装C数组和Rec数组 Rec数组0代表左上 1代表上 -1代表左
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    C[i][j] = C[i - 1][j - 1] + 1;
                    Rec[i][j] = 0;
                } else if (C[i - 1][j] > C[i][j - 1]) {
                    C[i][j] = C[i - 1][j];
                    Rec[i][j] = 1;
                } else {
                    C[i][j] = C[i][j - 1];
                    Rec[i][j] = -1;
                }
            }
        }
        Arrays.stream(C).forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
        System.out.println();
        Arrays.stream(Rec).forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
        printRec(Rec, A, n, m);
    }

    public static void printRec(int[][] Rec, char[] A, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (Rec[i][j] == 0) {
            printRec(Rec, A, i - 1, j - 1);
            System.out.println(A[i - 1]);
        }
        if (Rec[i][j] == 1) {
            printRec(Rec, A, i - 1, j);
        }
        if (Rec[i][j] == -1) {
            printRec(Rec, A, i, j - 1);
        }
    }

    /**
     * @Date: 2020-03-30
     * @Description: 最长公共子串问题
     */
    public static void longestCommonSubstring(char[] A, char[] B) {
        int n = A.length;
        int m = B.length;
        int[][] C = new int[n + 1][m + 1];
        int lMax = 0, pMax = 0;
        //初始化
        for (int i = 0; i <= n; i++) {
            C[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            C[0][i] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (A[i] != B[j]) {
                    C[i][j] = 0;
                } else {
                    C[i][j] = C[i - 1][j - 1] + 1;
                    if (C[i][j] > lMax) {
                        lMax = C[i][j];
                        pMax = i;
                    }
                }
            }
        }
        printSubstringRec(A, lMax, pMax);
    }

    public static void printSubstringRec(char[] A, int lMax, int pMax) {
        if (lMax == 0) {
            return;
        }
        for (int i = pMax - lMax + 1; i <= pMax; i++) {
            System.out.println(A[i]);
        }
    }

    static int[] a = {133, 121, -12, 14, 534, -23, 87, -87, 81, 41};
    static Integer[] a1 = {133, 121, -12, 14, 534, -23, 87, -87, 81, 41};
    static int[] a2 = {1, -2, 4, 5, -2, 8, 3, -2, 6, 3, 7, -1};
    static int[] a3 = {13, 8, 10, 6, 15, 18, 12, 20, 9, 14, 17, 19};
    static char[] A = {'A', 'B', 'C', 'A', 'D', 'A', 'A'};
    static char[] B = {'B', 'D', 'C', 'D', 'A', 'A'};

    public static void main(String[] args) {

        //longestCommonSubstring(A, B);

        //longestCommonSubSequence(A, B);

        //int[][] arr = {{22, 15, 32, 20, 18}, {12, 21, 25, 19, 33}, {14, 58, 34, 24, 66},};
        //Arrays.stream(arr).forEach(item->{
        //    System.out.println(Arrays.toString(item));
        //});

    }
}
