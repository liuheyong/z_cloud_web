package com.cloud.web.algorithm.muke;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: LiuHeYong
 * @create: 2020-03-23
 * @description: 分而治之篇
 */
public class SortAlgorithm {

    public SortAlgorithm() {
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
     * @Description: 快速排序算法(选取一个数为基数，比他大的总是在右边，小的总是在左边)
     * @Description: 时间复杂度好的情况下为O(n),坏的情况下为O(n^2)
     */
    public static void quickSort(int a[], int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(a, low, high);
        quickSort(a, low, p-1);
        quickSort(a, p+1, high);
    }
    public static int partition(int[] a, int low, int high) {
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
        //将a[low] a[i]的值进行交换，因为a[i]始终是比base小的数的最右边的一个数
        a[low] = a[i];
        a[i] = base;
        return i;
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
        int Rec[] = new int[n];
        //初始化:当数组中只有一个元素时
        End[0] = All[0] = array[0];
        End[n - 1] = All[n - 1] = array[n - 1];
        for (int i = 1; i < n; i++) {
            //Ent[i]是数组array[i]前i个数最大子数组之和
            End[i] = max(End[i - 1] + array[i], array[i]);
            if (End[i - 1] > 0) {
                Rec[i] = Rec[i - 1];
            } else if (End[i - 1] <= 0) {
                Rec[i] = i;
            }
            //All[i]
            All[i] = max(End[i], All[i - 1]);
        }
        System.out.println("方法三：最大子数组之和为：" + All[n - 1]);
        System.out.println("End[]数组为：" + Arrays.toString(End));
        System.out.println("All[]数组为：" + Arrays.toString(All));
        System.out.println("Rec[]数组为：" + Arrays.toString(Rec));
        getRec(Rec,End);
    }
    private static void getRec(int Rec[],int End[]) {
        int S = End[End.length - 1];
        int l = 0, r = 0;
        for (int i = End.length - 1; i > 0; i--) {
            if (S < End[i]) {
                S = End[i];
                r = i;
                l = Rec[i];
            }
        }
        System.out.println("左右下标为：" + l + "-" + r);
    }

    /**
     *  优化的动态规划(舍弃All数组)
     */
    private static void getSumOfSubArray05(int array[]) {
        int n = array.length;
        int End[] = new int[n];
        //初始化:当数组中只有一个元素时
        End[0] = array[0];
        End[n - 1] = array[n - 1];

        for (int i = 1; i < n; i++) {
            //Ent[i]是数组array[i]前i个数最大子数组之和
            End[i] = max(End[i - 1] + array[i], array[i]);
        }
        //从Ent[i]中找到最大值
        //for (int i = 1; i < n; i++) {
        //    End[i] = max(End[i - 1], End[i]);
        //}
        //System.out.println("方法三：最大子数组之和为：" + End[n - 1]);
        OptionalInt intOpt = Arrays.stream(End).max();
        System.out.println("方法三：最大子数组之和为：" + intOpt.getAsInt());
    }

    /**
     *  进一步优化的动态规划
     *  为了进一步降低空间复杂度，我们可以定义两个变量用来保存方法三中的
     *  End[i-1]和All[i-1]
     */
    private static void getSumOfSubArray04(int array[]) {
        int n = array.length;
        int nEnd = array[0]; //前i个元素的最大子数组之和
        int nAll = array[0];//包含最后一个元素的子数组之和(前i+1个元素的最大子数组之和)
        for (int i = 1; i < n ; i++) {
            nEnd = max(nEnd + array[i], array[i]);
            nAll = max(nEnd, nAll);
        }
        System.out.println("方法四：最大子数组之和为：" + nAll);
    }

    /**
     * @Date: 2020-03-24
     * @Description: 最大子数组问题算法(分治法、有左右下标)
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
     * @Description: 最大子数组问题算法(分治法、无左右下标)
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

    /**
    * @Date:  2020-03-27
    * @Description: 最大逆序对问题(枚举法 O(n^2))
    */
    public static int maxBackwardOrderNum(int a[], int low, int high) {
        if (low > high) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i <= high; i++) {
            for (int j = i + 1; j <= high; j++) {
                if (a[i] > a[j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * @Date: 2020-03-24
     * @Description: 归并排序算法求解逆序对数量问题 - 1
     */
    public static int backwardOrderCount(int a[], int low, int high) {
        int mid = (low + high) >> 1;
        int s1 = 0, s2 = 0, s3 = 0;
        if (low < high) {
            s1 = backwardOrderCount(a, low, mid);
            s2 = backwardOrderCount(a, mid + 1, high);
            s3 = mergeCount(a, low, mid, high);
        }
        return s1 + s2 + s3;
    }
    public static int mergeCount(int a[], int low, int mid, int high) {
        int[] tem = new int[high - low + 1];
        int k = 0;
        int i = low, j = mid + 1, s3 = 0;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                tem[k++] = a[i++];
            } else {
                tem[k++] = a[j++];
                s3 += mid - i + 1;
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
        return s3;
    }

    /**
     * @Date: 2020-03-24
     * @Description: 归并排序算法求解逆序对数量问题 - 2
     */
    static int sumCount =  0;
    static void mergerArray(int p, int mid, int q, int start, int end, int arr[], int T[])
    {
        int i = start;
        while (p <= mid && q <= end) {
            if (arr[p] <= arr[q])
                T[i++] = arr[p++];
            else {
                T[i++] = arr[q++];
                sumCount += mid - p + 1; //发生逆序，此时由于
                //arr[p..mid]是已经有序了，那么arr[i+1], arr[i+2], ... arr[mid]都是大于arr[q]的，
                //都可以和a[q]组成逆序对，因此sum += mid - p + 1
            }
        }
        while (p <= mid) {
            T[i++] = arr[p++];
        }
        while (q <= end) {
            T[i++] = arr[q++];
        }
        for (i = start; i <= end; i++) {
            arr[i] = T[i];
        }
    }
    static void mergerSortBackwardOrder(int arr[], int start, int end, int T[])
    {
        if(end > start){
            int mid = (end+start) / 2;
            int p = start;
            int q = mid + 1;
            mergerSortBackwardOrder(arr, start, mid , T);
            mergerSortBackwardOrder(arr, mid+1, end, T);
            mergerArray(p, mid, q, start, end, arr, T);
        }
    }

    /**
     * @Date: 2020-03-24
     * @Description: 快速排序算法(随机选取一个数为基数，比他大的总是在右边，小的总是在左边)
     * @Description: 时间复杂度为O(nlogn)
     */
    public static void quickSortRandom(int a[], int low, int high) {
        if (low >= high) {
            return;
        }
        int p = randomPartition(a, low, high);
        quickSortRandom(a, low, p-1);
        quickSortRandom(a, p+1, high);
    }
    public static int randomPartition(int[] a, int left, int right) {
        int r = new Random().nextInt(right - left + 1) + left; //生成一个随机数，即是主元所在位置
        swap(a, left, r); //将主元与序列最右边元素互换位置，这样就变成了之前快排的形式。
        return partitionRandom(a, left, right); //直接调用之前的代码
    }
    public static int partitionRandom(int[] a, int low, int high) {
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
        //将a[low] a[i]的值进行交换，因为a[i]始终是比base小的数的最右边的一个数
        a[low] = a[i];
        a[i] = base;
        return i;
    }
    //交换数组a中的a[i]和a[j]
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
    * @Date:  2020-03-28
    * @Description:  快速排序01 算法导论提供的方法 -- 固定主元
    */
    public static void QuickSortEx(int[] a, int left, int right) {
        if (left < right) {
            int p = partitionEx(a, left, right);
            QuickSortEx(a, left, p - 1);
            QuickSortEx(a, p + 1, right);
        }
    }
    //快速排序数组划分
    private static int partitionEx(int[] a, int left, int right) {
        int x = a[right];
        int p = left - 1;
        for (int i = left; i < right; i++) {
            if (a[i] <= x) {
                p++;
                swap(a, p, i);
            }
        }
        swap(a, p+1, right);
        return p+1;
    }

    /**
     * @Date:  2020-03-28
     * @Description:  快速排序02 算法导论提供的方法 -- 随机化版本，除了调用划分函数不同，和之前快排的代码结构一模一样
     */
    public static void RandomQuickSortEx(int[] a, int left, int right) {
        if (left < right) {
            int p = randomPartitionEx(a, left, right);
            RandomQuickSortEx(a, left, p - 1);
            RandomQuickSortEx(a, p + 1, right);
        }
    }
    //随机化划分
    public static int randomPartitionEx(int[] a, int left, int right) {
        int r = new Random().nextInt(right - left + 1) + left; //生成一个随机数，即是主元所在位置
        swap(a, right, r); //将主元与序列最右边元素互换位置，这样就变成了之前快排的形式。
        return partition(a, left, right); //直接调用之前的代码
    }

    /**
     * @Date: 2020-03-28
     * @Description: 次序选择问题 -- 找出第k小的元素（固定主元方式）
     */
    public static int select(int[] a, int p, int r, int k) {
        int element = 0;
        int q = partitionEx(a, p, r);
        if (k == q - p + 1) {
            return a[q];
        }
        if (k > q - p + 1) {
            element = select(a, q + 1, r, k - (q - p + 1));
        }
        if (k < q - p + 1) {
            element = select(a, p, q - 1, k);
        }
        return element;
    }

    /**
     * @Date: 2020-03-28
     * @Description: 次序选择问题 -- 找出第k小的元素（随机主元方式）
     */
    public static int selectRandom(int[] a, int p, int r, int k) {
        int element = 0;
        int q = randomPartitionEx(a, p, r);
        if (k == q - p + 1) {
            return a[q];
        }
        if (k > q - p + 1) {
            element = select(a, q + 1, r, k - (q - p + 1));
        }
        if (k < q - p + 1) {
            element = select(a, p, q - 1, k);
        }
        return element;
    }

    static int[] a = {133, 121, -12, 14, 534, -23, 87, -87, 81, 41};
    static int[] aaa = {13, 8, 10, 6, 15, 18, 12, 20, 9, 14, 17, 19};
    static int[] a2 = {1, -2, 4, 5, -2, 8, 3, -2, 6, 3, 7, -1};

    public static void main(String[] args) {

        //System.out.println(selectRandom(a, 0, 9, 2));

        //System.out.println(select(aaa, 0, 9, 11));

        //RandomQuickSortEx(a, 0, 9);
        //System.out.println(Arrays.toString(a));

        //QuickSortEx(a, 0, 9);
        //System.out.println(Arrays.toString(a));

        //Random random = new Random(Calendar.getInstance().getTimeInMillis());
        //int[] aq = new int[10000000];
        //for (int i = 0; i < aq.length; i++) {
        //    aq[i] = random.nextInt(Integer.MAX_VALUE);
        //}
        //Long startTime = System.currentTimeMillis();
        //quickSortRandom(aq, 0, aq.length - 1);
        //Long endTime = System.currentTimeMillis();
        //System.out.println("quickSortRandom Finished. Cost :" + (endTime - startTime));

        //mergerSortBackwardOrder(aaa, 0, 9, new int[10]);
        //System.out.println(sumCount);

        //int sum = backwardOrderCount(a, 0, 9);
        //System.out.println(sum);

        //int sum = maxBackwardOrderNum(a, 0, 9);
        //System.out.println(sum);

        //int sum = maxSubArraySort(a, 0, 9);
        //System.out.println(sum);

        //Integer [] arr = findMaxSubArr(aa, 0, 9);
        //System.out.println(Arrays.toString(arr));

        //getSumOfSubArray05(a);

        //getSumOfSubArray04(a);

        //getSumOfSubArray03(a2);

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
