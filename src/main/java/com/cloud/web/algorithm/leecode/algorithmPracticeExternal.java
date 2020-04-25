package com.cloud.web.algorithm.leecode;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 数学问题
 */
public class algorithmPracticeExternal {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 位1的个数（编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量））
     */
    public static int hammingWeight(int n) {
        int k = 0;
        while (n != 0) {
            n = n & (n - 1);
            k++;
        }
        return k;
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 汉明距离（两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目）
     */
    //class Solution {
    //    public:
    //    int hammingDistance(int x, int y) {
    //        int res = 0;
    //        for (int i = 0; i < 32; ++i) {
    //            if ((x & (1 << i)) ^ (y & (1 << i))) {
    //                ++res;
    //            }
    //        }
    //        return res;
    //    }
    //};

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 汉明距离（我们可以对上面的代码进行优化，我们可以一开始直接将两个数字异或起来，然后我们遍历异或结果的每一位，统计为1的个数，也能达到同样的效果）
     */
    //class Solution {
    //    public:
    //    int hammingDistance(int x, int y) {
    //        int res = 0, exc = x ^ y;
    //        for (int i = 0; i < 32; ++i) {
    //            res += (exc >> i) & 1;
    //        }
    //        return res;
    //    }
    //};

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 汉明距离（假如数为num, num & (num - 1)可以快速地移除最右边的bit 1， 一直循环到num为0, 总的循环数就是num中bit 1的个数）
     */
    //class Solution {
    //    public:
    //    int hammingDistance(int x, int y) {
    //        int res = 0, exc = x ^ y;
    //        while (exc) {
    //            ++res;
    //            exc &= (exc - 1);
    //        }
    //        return res;
    //    }
    //};

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 汉明距离（我们再来看一种递归的写法，非常的简洁，递归终止的条件是当两个数异或为0时，表明此时两个数完全相同，我们返回0，否则我们返回异或和对2取余加上对x/2和y/2调用递归的结果。异或和对2
     * 取余相当于检查最低位是否相同，而对x/2和y/2调用递归相当于将x和y分别向右移动一位，这样每一位都可以比较到，也能得到正确结果）
     */
    //class Solution {
    //    public:
    //    int hammingDistance(int x, int y) {
    //        if ((x ^ y) == 0) return 0;
    //        return (x ^ y) % 2 + hammingDistance(x / 2, y / 2);
    //    }
    //};

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 缺失数字（给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数）
     */
    public int missingNumber(int[] nums) {
        int a = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            a = a ^ i ^ nums[i];
        }
        return a ^ i;
    }

    public static void main(String[] args) throws Exception {

        //System.out.println(isPowerOfThree(81));

        //System.out.println(isPowerOfTwo(200));

        //System.out.println(countPrimes(200));

    }

}
