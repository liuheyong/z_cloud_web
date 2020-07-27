package com.cloud.web.concurrentprogrammer.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: wenyixicodedog
 * @create: 2020-07-27
 * @description:
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(4);
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    try {
                        int a = new Random().nextInt((3000 - 1000) + 1) + 1000;
                        Thread.sleep(a);
                        System.out.println(Thread.currentThread().getName() + "通过了第" + j + "个障碍物, 耗时 " + ((double) a / 1000) + "s");
                        if (j == 0) {
                            barrier.reset();
                        }
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("===============main 方法执行完毕===============");
    }

}
