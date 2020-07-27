package com.cloud.web.concurrentprogrammer.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author: liuheyong
 * @create: 2019-09-15
 * @description:
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        testCountDownLatch();
    }

    public static void testCountDownLatch() {
        int threadCount = 10;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                System.out.println("线程===" + Thread.currentThread().getId() + "===开始执行");
                try {
                    Thread.sleep(1000);
                    System.out.println("线程===" + Thread.currentThread().getId() + "===执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("10个线程已经执行完毕，开始执行后续任务。");
        System.out.println("10个线程已经执行完毕，开始执行后续任务。");
        //try {
        //    latch.await();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        System.out.println("10个线程已经执行完毕，开始执行后续任务。");
    }
}
