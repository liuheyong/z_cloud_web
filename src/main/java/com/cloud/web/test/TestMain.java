package com.cloud.web.test;

import java.lang.reflect.Method;

/**
 * @author: HeYongLiu
 * @create: 07-17-2019
 * @description:
 **/
public class TestMain {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        Method method = null;
        if (Object.class.equals(TestMain.class)) {
            try {
                System.out.println("1");
            } catch (Throwable t) {
            }
        }

        /*TestMain test = new TestMain();
        test.stackLeakByThread();*/

        /*ArrayList<TestMain> arrayList = new ArrayList<TestMain>();
        while (true) {
            arrayList.add(new TestMain());
        }*/

        /* Student student = new Student("liu", 99);
        System.out.println(student);*/

       /*String str2 = new String("str") + new String("01");
        String str1 = "str01";
        str2.intern();
        System.out.println(str2.equals(str1));
        System.out.println(str2 == str1);*/
    }

}
