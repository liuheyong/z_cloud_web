package com.cloud.web.algorithm;

import java.util.Arrays;

/**
 * @author: LiuHeYong
 * @create: 2020-03-23
 * @description:
 */
public class SortMain {

    public SortMain() {
    }

    static int[] b = {11, 21, 32, 4, 534, 23, 87, 73, 111, 421, 21, 32, 4, 534, 23, 87, 73, 111, 421
                        , 21, 32, 4, 534, 23, 87, 73, 111, 421, 21, 32, 4, 534, 23, 87, 73, 111, 421
    };

    /**
     * @Date: 2020-03-23
     * @Description: 冒泡排序算法
     */
    public static void maoPaoSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    a[j] = a[j] + a[j + 1];
                    a[j + 1] = a[j] - a[j + 1];
                    a[j] = a[j] - a[j + 1];
                }
            }
        }
    }

    /**
     * @Date: 2020-03-23
     * @Description: 插入排序算法
     */
    public static void insertSort(int[] arr) {
        int i, j;
        int n = arr.length;
        int target;
        for (i = 1; i < n; i++) {
            j = i;
            target = arr[i];
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = target;
        }
    }

    /**
    * @Date: 2020-03-24
    * @Description: 选择排序算法
    */
    public static void selectSort(int[] a) {
        int i, j;
        int n = a.length;
        for (i = 0; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                if (a[j] <= a[i]) {
                    a[i] = a[i] + a[j];
                    a[j] = a[i] - a[j];
                    a[i] = a[i] - a[j];
                }
            }
        }
        //int i, j;
        //int length = a.length;
        //int temp;
        //for (int j = 0; j < length - 1; j++) {
        //    for (int i = j; i < length - 1; i++) {
        //        if (a[j] > a[i + 1]) {
        //            // change
        //            temp = a[j];
        //            a[j] = a[i + 1];
        //            a[i + 1] = temp;
        //        }
        //    }
        //}
    }

    /**
     * @Date: 2020-03-24
     * @Description: 归并排序算法
     */
    public static int[] mergeSort(int a[], int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
        return a;
    }
    public static void merge(int a[], int low, int mid, int high) {
        int[] tem = new int[high - low + 1];
        int k = 0;
        int i = low, j = mid + 1;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                tem[k++] = a[i++];
            } else {
                tem[k++] = a[j++];
            }
        }
        while (i <= mid) {
            tem[k++] = a[i++];
        }
        while (j <= high) {
            tem[k++] = a[j++];
        }
        for (int l = 0; l < tem.length; l++) {
            a[low + l] = tem[l];
        }
    }

    /**
     * @Date: 2020-03-24
     * @Description: 快速排序算法
     */
    public static void quickSort(int a[], int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int base = a[low];
        while (i != j) {
            while (a[j] >= base && i < j) {
                j--;
            }
            while (a[i] <= base && i < j) {
                i++;
            }
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[low] = a[i];
        a[i] = base;
        quickSort(a, low, i-1);
        quickSort(a, i+1, high);
    }

    static int[] a = {11, 21, 32, 4, 534, 23, 87, 87, 111, 41};

    public static void main(String[] args) {

        //quickSort(a, 0, 9);
        //System.out.println(Arrays.toString(a));

        //mergeSort(a, 0, 9);
        //System.out.println(Arrays.toString(a));

        //selectSort(a);
        //System.out.println(Arrays.toString(a));

        //Long startTime = System.currentTimeMillis();
        //insertSort(a);
        //Long endTime = System.currentTimeMillis();
        //System.out.println(endTime - startTime);
        //System.out.println(Arrays.toString(a));

        //maoPaoSort(a);
        //System.out.println(Arrays.toString(a));

        //Long startTime = System.currentTimeMillis();
        Arrays.parallelSort(b);
        //Long endTime = System.currentTimeMillis();
        //System.out.println(endTime - startTime);
        //System.out.println(Arrays.toString(b));

    }

}
