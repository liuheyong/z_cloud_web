package com.cloud.web.algorithm.leecode;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 数学问题
 */
public class algorithmPracticeMathematic {

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: Fizz Buzz（写一个程序，输出从 1 到 n 数字的字符串表示）
     */
    //class Solution {
    //    public:
    //    vector<string> fizzBuzz(int n) {
    //        vector<string> res;
    //        for (int i = 1; i <= n; ++i) {
    //            if (i % 15 == 0) res.push_back("FizzBuzz");
    //            else if (i % 3 == 0) res.push_back("Fizz");
    //            else if (i % 5 == 0) res.push_back("Buzz");
    //            else res.push_back(to_string(i));
    //        }
    //        return res;
    //    }
    //};

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 计数质数（统计所有小于非负整数 n 的质数的数量。100以内）
     */
    public static int countPrimes(int n) {
        if (n == 1) return 0;
        if (n == 2) return 0;
        if (n == 3) return 1;
        if (n == 4) return 2;
        if (n == 5) return 2;
        if (n == 6) return 3;
        if (n == 7) return 3;
        if (8 <= n && n <= 11) return 4;
        if (n > 11) {
            int count = 0;
            for (int i = 11; i < n; i++) {
                //if里面的判断条件是一个数是质数的必要不充分条件
                if (i % 2 != 0 && i % 3 != 0 && i % 5 != 0 && i % 7 != 0) {
                    count++;
                    System.out.println(i);
                }
            }
            return count + 4;
        }
        return 0;
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 2的幂（给定一个整数，写一个函数来判断它是否是 2 的幂次方）
     */
    public static boolean isPowerOfTwo(int n) {
        if ((n & n - 1) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Date: 2020-04-19
     * @Param:
     * @return:
     * @Description: 3的幂（给定一个整数，写一个函数来判断它是否是 3 的幂次方）
     */
    public static boolean isPowerOfThree(int n) {
        double tem = Math.log10(n) / Math.log10(3);
        return (tem - (int) (tem)) == 0 ? true : false;
    }


    public static void main(String[] args) throws Exception {

        System.out.println(isPowerOfThree(81));

        //System.out.println(isPowerOfTwo(200));

        //System.out.println(countPrimes(200));

    }

}
