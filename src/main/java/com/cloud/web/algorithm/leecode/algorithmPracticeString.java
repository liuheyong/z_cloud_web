package com.cloud.web.algorithm.leecode;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 字符串部分
 */
public class algorithmPracticeString {

    /**
     * @Date: 2020-04-09
     * @Description: 反转字符串
     */
    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            System.out.println("数组为空");
            return;
        }
        int n = s.length;
        String str = "";
        for (int i = n - 1; i >= 0; i--) {
            str += "\"" + s[i] + "\",";
        }
        System.out.println("[" + str.substring(0, str.lastIndexOf(",")) + "]");
    }

    /**
     * @Date: 2020-04-09
     * @Description: 反转带符号的Integer范围内的整数
     */
    public static int reverse(int x) {
        //存放结果
        int rev = 0;
        while (x != 0) {
            //每次取最后一位数
            int temp = x % 10;
            //每次取完最后一位后将其去掉
            x /= 10;
            //溢出零界点判断 范围[-2^31 , 2^31 -1] 即 [-2147483648，2147483647]
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && temp > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && temp < -8))
                return 0;
            rev = rev * 10 + temp;
        }
        return rev;
    }

    static char[] s = {'H', '2', 'n', 'e', 'a', 'h'};

    public static void main(String[] args) {
        reverseString(s);
    }

}
