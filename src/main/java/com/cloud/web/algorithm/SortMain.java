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
    static Integer[] aa = {133, 121, -12, 14, 534, -23, 87, -87, 81, 41};

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

    /**
     * 最大子数组问题算法（蛮力法 O(n^3)）
     */
    private static void getSumOfSubArray01(int array[]) {
        int n = array.length;
        int thisSum, maxSum = Integer.MIN_VALUE, k, i, j;
        for (i = 0; i < n; i++) {
            for (j = i; j < n; j++) {
                thisSum = 0;
                System.out.print("子数组有：");
                for (k = i; k <= j; k++) { //k j分别为子数组的起止位置
                    thisSum = thisSum + array[k];
                    System.out.print(array[k] + " ");//输出所有的子数组是哪些
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        System.out.println("最大子数组之和为：" + maxSum);
    }

    /**
     * 最大子数组问题算法（优化的蛮力法）（重复利用已经计算的子数组和，相比较方法一,时间复杂度为：O（n^2））
     */
    private static void getSumOfSubArray02(int array[]) {
        int n = array.length;
        int thisSum, maxSum = Integer.MIN_VALUE, i, j;
        for (i = 0; i < n; i++) {
            for (j = i; j < n; j++) {
                thisSum = 0;
                thisSum = thisSum + array[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        System.out.println("最大子数组之和为：" + maxSum);
    }

    /**
     * 利用动态规划解题：该方法时间复杂度为：o(n),但是额外使用了两个数组空间，其空间复杂度为：o(n)
     * 解题思路：对于一个数组，求最大子数组之和，我们可以分为三部分：
     * 我们以最后一个元素array[n-1]为例子：
     * 1：array[n-1]自己构成最大的子数组
     * 2：包含array[n-1]的最大子数组，即以array[n-1]结尾，我们用End[n-1]表示
     * 3：不包含array[n-1]的最大子数组，那么求array[0]...array[n-1]的子数组，可以转化为求
     *    array[0]...array[n-2]的最大子数组
     *
     * 由以上可知：All[n-1]=max{array[n-1],End[n-1],All[n-1]}
     *    All[n-1]表示为：array[0]...array[n-1]的最大子数组之和
     */
    private static int max(int a,int b){
        return a > b ? a : b;
    }
    private static int maxInThreeNum(int S_Left,int S_Right,int S_Cross_Left_Right){
        return (S_Left > S_Right ? (S_Left > S_Cross_Left_Right ? S_Left : S_Cross_Left_Right) : (S_Right > S_Cross_Left_Right ? S_Right : S_Cross_Left_Right));
    }
    private static void getSumOfSubArray03(int array[]) {
        int n = array.length;
        int End[] = new int[n];
        int All[] = new int[n];
        //初始化:当数组中只有一个元素时
        End[0] = All[0] = array[0];
        End[n - 1] = All[n - 1] = array[n - 1];

        for (int i = 1; i < n; i++) {
            //Ent[i]是前i+1个数之和和array[i]比较中的较大者
            End[i] = max(End[i - 1] + array[i], array[i]);
            //All[i]是前i+1个数最大子数组之和
            All[i] = max(End[i], All[i - 1]);
        }
        System.out.println("方法三：最大子数组之和为：" + All[n - 1]);
    }

    /**
     *  优化的动态规划
     *  为了进一步降低空间复杂度，我们可以定义两个变量用来保存方法三中的
     *  End[i-1]和All[i-1]
     */
    private static void getSumOfSubArray04(int array[]) {
        int n = array.length;
        int nEnd = array[0]; //前i个元素的最大子数组之和
        int nAll = array[0];//包含组后一个元素的子数组之和(前i+1个元素的最大子数组之和)
        for (int i = 1; i < n ; i++) {
            nEnd = max(nEnd + array[i], array[i]);
            nAll = max(nEnd, nAll);
        }
        System.out.println("方法四：最大子数组之和为：" + nAll);
    }

    /**
     * @Date: 2020-03-24
     * @Description: 最大子数组问题算法(分治法)
     */
    private static Integer[] findMaxSubArr(Integer[] arr, Integer low, Integer high) {
        if (low == high) {
            Integer[] _arr = {low, high, arr[low]};
            return _arr;
        }
        Integer mid = (low + high) >> 1;
        Integer left[];
        Integer right[];
        Integer crossing[];
        left = findMaxSubArr(arr, low, mid);
        right = findMaxSubArr(arr, mid + 1, high);
        crossing = findMaxCrossingSubArr(arr, low, mid, high);
        if (left[2] >= right[2] && left[2] >= crossing[2]) {
            return left;
        }
        if (right[2] >= left[2] && right[2] >= crossing[2]) {
            return right;
        }
        return crossing;
    }
    private static Integer[] findMaxCrossingSubArr(Integer[] arr,Integer low,Integer mid,Integer high) {
        int left_sum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;
        for (int i = mid; i >= low; i--) {
            sum = sum + arr[i];
            if (sum > left_sum) {
                left_sum = sum;
                maxLeft = i;
            }
        }
        int right_sum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid + 1;
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + arr[j];
            if (sum > right_sum) {
                right_sum = sum;
                maxRight = j;
            }
        }
        Integer[] res= {maxLeft,maxRight,left_sum+right_sum};
        return res;
    }

    /**
     * @Date: 2020-03-24
     * @Description: 最大子数组问题算法(分治法)
     */
    public static int maxSubArraySort(int a[], int low, int high) {
        if (low > high) {
            return 0;
        }
        if (low == high) {
            return a[0];
        }
        int S_Left = 0, S_Right = 0, S_Cross_Left_Right = 0;
        //while (low < high) {
        int i = low;
        int j = high;
        int mid = (i + j) / 2;
        S_Left = maxSubArraySort(a, i, mid);
        S_Right = maxSubArraySort(a, mid + 1, j);
        S_Cross_Left_Right = CrossLeftAndRight(a, i, mid, j);
        //}
        return maxInThreeNum(S_Left, S_Right, S_Cross_Left_Right);
    }
    public static int CrossLeftAndRight(int a[], int low, int mid, int high) {
        int Sub_Left_Sum = Integer.MIN_VALUE;
        int Sub_Left_Sum_Tem = 0;
        for (int i = mid; i >= low; i--) {
            Sub_Left_Sum_Tem += a[i];
            if (Sub_Left_Sum_Tem > Sub_Left_Sum) {
                Sub_Left_Sum = Sub_Left_Sum_Tem;
            }
        }
        int Sub_Right_Sum = Integer.MIN_VALUE;
        int Sub_Right_Sum_Tem = 0;
        for (int i = mid + 1; i <= high; i++) {
            Sub_Right_Sum_Tem += a[i];
            if (Sub_Right_Sum_Tem > Sub_Right_Sum) {
                Sub_Right_Sum = Sub_Right_Sum_Tem;
            }
        }
        return Sub_Left_Sum + Sub_Right_Sum;
    }

    static int[] a = {133, 121, -12, 14, 534, -23, 87, -87, 81, 41};

    public static void main(String[] args) {

        //int sum = maxSubArraySort(a, 0, 9);
        //System.out.println(sum);

        //Integer [] arr = findMaxSubArr(aa, 0, 9);
        //System.out.println(Arrays.toString(arr));

        //getSumOfSubArray04(a);

        //getSumOfSubArray03(a);

        //getSumOfSubArray02(a);

        //getSumOfSubArray01(a);

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
        //Arrays.parallelSort(b);
        //Long endTime = System.currentTimeMillis();
        //System.out.println(endTime - startTime);
        //System.out.println(Arrays.toString(b));

    }

}
