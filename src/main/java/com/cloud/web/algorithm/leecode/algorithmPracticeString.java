package com.cloud.web.algorithm.leecode;

/**
 * @author: LiuHeYong
 * @create: 2020-04-09
 * @description: 字符串部分
 */
public class algorithmPracticeString {

    /**
    * @Date:  2020-04-09
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

    static char[] s = {'H', '2', 'n', 'e', 'a', 'h'};

    public static void main(String[] args) {
        reverseString(s);
    }

}
