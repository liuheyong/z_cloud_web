package com.cloud.web.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: LiuHeYong
 * @create: 2020-04-02
 * @description:
 */
public class algorithmPractice {

    /**
     * @Date: 2020-04-02
     * @Description: 数组去重(打乱原来数组的大小顺序)
     * 实现思想--只要找到重复的就把找到的重复的数据放到最后一位，然后n-1
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
     * @Date: 2020-04-02
     * @Description: 数组去重(保留原来数组的大小顺序)
     * 实现思想--只要找到重复的就把找到的重复的数据之后的数据从后往前移一位，然后n-1
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

    /**
     * @Date: 2020-04-02
     * @Description: 数组去重(保留原来数组的大小顺序)
     * 实现思想--首先找到一个基数，只要找到不重复的就把找到的不重复的数据放到基数后一位
     */
    public static int removeDuplicates03(int nums[]) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int back = 0;
        for (int front = 1; front < nums.length; front++) {
            if (nums[back] != nums[front]) {
                back++;
                nums[back] = nums[front];
            }
        }
        //System.out.println(back + 1);
        //System.out.println(Arrays.toString(nums));
        return back + 1;
    }

    /**
     * @Date: 2020-04-02
     * @Description: 旋转数组(给定一个数组 ， 将数组中的元素向右移动 k 个位置 ， 其中 k 是非负数 。)
     * 实现思想--首先确定移动几位(一定是小于数组长度)，然后循环调用移动一位的方法(k次)
     */
    public static void rotate01(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空");
            return;
        }
        int n = nums.length;
        int mod = k % n;
        if (mod == 0) {
            System.out.println(Arrays.toString(nums));
            return;
        }
        k = mod;
        for (int i = 0; i < k; i++) {
            rotateOneTime(nums, n);
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void rotateOneTime(int[] a, int n) {
        int tem = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            a[i + 1] = a[i];
        }
        a[0] = tem;
    }

    /**
     * @Date: 2020-04-02
     * @Description: 旋转数组(给定一个数组 ， 将数组中的元素向右移动 k 个位置 ， 其中 k 是非负数 。)
     * 实现思想--移动的距离是k，当移动完成一轮后，进行下一轮移动
     */
    public static void rotate02(int[] nums, int k) {
        int temp1, temp2, index, count = 0;//count为计数变量，记录移动成功的次数，temp1和temp2是辅助存储变量，为交换元素值时用
        int len = nums.length;
        k %= len;//对k值的前期处理，处理原因是如果k值比len大，那么只有大于len的那部分才是有效移动
        if (k == 0) return;//如果k等于0或者k原本是等于len的，那么就相当于没有移动嘛，直接返回
        for (int i = 0; i <= k; i++) //移动的轮数最多k次，当然计数变量count=len的时候会跳出循环
        {
            if (count >= len)
                break;//对计数变量的控制，当所有位置全部移动完了就可以结束了
            index = i;//每轮移动的初始下标
            temp1 = nums[i];
            while ((index + k) % len != i) //一个while循环移动一次
            {
                temp2 = nums[(index + k) % len];
                nums[(index + k) % len] = temp1;
                index = (index + k) % len;
                temp1 = temp2;
                count++;
            }
            nums[i] = temp1;
            count++;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * @Date: 2020-04-02
     * @Description: 存在重复元素(给定一个数组 ， 如果任意一值在数组中出现至少两次 ， 函数返回 true 。 如果数组中每个元素都不相同 ， 则返回 false 。)
     * 实现思想--双重遍历，一次比较
     */
    public static boolean containsDuplicate01(int[] a) {
        if (a == null || a.length == 0) {
            return false;
        }
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (a[i] == a[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Date: 2020-04-02
     * @Description: 存在重复元素(给定一个数组 ， 如果任意一值在数组中出现至少两次 ， 函数返回 true 。 如果数组中每个元素都不相同 ， 则返回 false 。)
     * 实现思想--双重遍历，一次比较
     */
    public static boolean containsDuplicate02(int[] a) {
        if (a == null || a.length == 0) {
            return false;
        }
        int n = a.length;
        int tem;
        for (int i = 0; i < n; i++) {
            tem = a[i];
            int j = i + 1;
            while (j < n) {
                if (tem == a[j]) {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    //// 偶数下标的所有元素➖奇数下标的所有元素
    //int num = 0;
    //for (int i = 0; i < nums.length; i++) {
    //// 偶数下标位置 num += nums[i]，奇数下标位置 num -= nums[i]
    //num = i % 2 == 0 ? num + nums[i] : num - nums[i];
    //}

    /**
     * @Date: 2020-04-02
     * @Description: 找出基数数量的元素(给定一个非空整数数组 ， 除了某个元素只出现一次以外 ， 其余每个元素均出现两次 。 找出那个只出现了一次的元素 。)
     * 实现思想--奇数个异或是本身，偶数个是0；0^a = a；异或有交换律
     */
    public static int singleNumber(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }

    //给数组做一组标志位数组
    public static int[] intersect1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        boolean[] bl = new boolean[len2];
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (nums1[i] == nums2[j] && bl[j] == false) {
                    al.add(nums1[i]);
                    bl[j] = true;
                    break;
                }
            }
        }
        int[] in = new int[al.size()];
        int e = 0;
        for (int i : al) in[e++] = i;
        return in;
    }

    //使用Map，数组作为键值key，计数器作为value
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums1.length; i++) {
            Integer val = map.get(nums1[i]);
            map.put(nums1[i], (val == null) ? 1 : ++val);
        }
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0, val; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && (val = map.get(nums2[i])) > 0) {
                al.add(nums2[i]);
                map.put(nums2[i], --val);
            }
        }
        int[] in = new int[al.size()];
        int e = 0;
        for (int i : al) in[e++] = i;
        return in;
    }

    //排好序后比较移动指针
    public static int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0, j = 0; i < len1 && j < len2; ) {
            if (nums1[i] == nums2[j]) {
                al.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        int[] in = new int[al.size()];
        int e = 0;
        for (int i : al) in[e++] = i;
        return in;
    }

    /**
     * @Date: 2020-04-02
     * @Description: 加一(给定一个由整数组成的非空数组所表示的非负整数 ， 在该数的基础上加一 。)
     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            //非9加1
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            //逢9置0
            digits[i] = 0;
        }
        //全部为9，则需要数组扩充1位
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    /**
     * @Date: 2020-04-02
     * @Description: 移动零(给定一个数组 nums, 编写一个函数将所有 0 移动到它的末尾 ， 同时保持非零元素的相对顺序 。)
     * 实现思想--从前往后遍历，只要发现有0，0之后的所有数据往前移一位
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int j = nums.length;
        for (int i = 0; i < j; ) {
            if (nums[i] == 0) {
                //(i,j)之间的数字前移
                for (int k = i + 1; k < j; k++) {
                    nums[k - 1] = nums[k];
                }
                //移至末尾
                nums[j - 1] = 0;
                j--;
                continue;
            }
            i++;
        }
    }

    public static void moveZeroes02(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        //定义j表示后面非0的索引
        int j = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; ) {
            if (nums[i] == 0) {
                //需要换位置
                if (i < j) {
                    for (int k = i + 1; k <= j; k++) {
                        nums[k - 1] = nums[k];
                    }
                    nums[j] = 0;
                }
                j--;
            }
            i--;
        }
    }

    public void moveZeroes03(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        //记录非o元素开始位置
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        while (k < nums.length) {
            nums[k] = 0;
            k++;
        }
    }

    static int[] a = {1, 1, 0, 3, 2, 0};
    static int[] a1 = {41, 121, 2, 41, 81, 6, 81, -87, 87, 14};
    static Integer[] a2 = {133, 121, 81, 14, 534, -23, 87, -87, 81, 14};
    static char[] A = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
    static char[] B = {'B', 'D', 'C', 'A', 'B', 'A'};

    public static void main(String[] args) {

        moveZeroes02(a);
        System.out.println(Arrays.toString(a));

        //System.out.println(Arrays.toString(plusOne(a)));

        //System.out.println(Arrays.toString(intersect3(a, a1)));

        //System.out.println(Arrays.toString(intersect2(a, a1)));

        //System.out.println(Arrays.toString(intersect1(a, a1)));

        //System.out.println(singleNumber(a));

        //System.out.println(containsDuplicate02(a));

        //System.out.println(containsDuplicate01(a));

        //rotate02(a, 55);

        //rotate01(a, 55);

        //removeDuplicates03(a);

        //removeDuplicates02(a1);

        //removeDuplicates01(a1);

    }
}
