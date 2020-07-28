package com.cloud.web.concurrentprogrammer.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: wenyixicodedog
 * @create: 2020-07-28
 * @description:
 */
public class TestSemaPhore {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int index = 0; index < 10; index++) {
            Runnable run = () -> {
                try {
                    // 获取许可证
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "===获取到许可证...");
                    Long sleepTime = (long) (new Random().nextInt((3000 - 1000) + 1) + 1000) / 1000;
                    Thread.sleep(sleepTime);
                    // 释放许可证
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "===释放了许可证，耗时..." + sleepTime + "秒");
                    //availablePermits()指的是还有多少个资源可以被使用
                    System.out.println("--------可用许可证数量---------" + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.execute(run);
        }
        service.shutdown();
    }

}
