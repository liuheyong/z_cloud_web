package com.cloud.web.algorithm.test;

/**
 * @author: wenyixicodedog
 * @create: 2020-06-30
 * @description:
 */
public class MyCodeTest {

    public MyCodeTest() {
    }

    public static int getTotal(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n < 3) {
            return n;
        }
        if (n % 3 == 0) {
            return n + getTotal(n / 3);
        }
        if (n % 3 != 0) {
            return n + getTotal(n / 3);
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getTotal(13));
    }

}
