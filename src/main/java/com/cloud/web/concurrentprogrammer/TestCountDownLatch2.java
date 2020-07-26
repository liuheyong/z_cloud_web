package com.cloud.web.concurrentprogrammer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: liuheyong
 * @create: 2019-09-15
 * @description:
 */
public class TestCountDownLatch2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdlResult = new CountDownLatch(1);
        final CountDownLatch cdlPlayer = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println("运动员" + Thread.currentThread().getName() + "正在等待裁判发布口令");
                    cdlResult.await();
                    System.out.println("运动员" + Thread.currentThread().getName() + "已接受裁判口令");
                    Thread.sleep((long) (Math.random() * 3000));
                    System.out.println("运动员" + Thread.currentThread().getName() + "到达终点");
                    cdlPlayer.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 3000));
            System.out.println("裁判" + Thread.currentThread().getName() + "即将发布口令");
            cdlResult.countDown();
            System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，运动员正在赛跑当中...");
            cdlPlayer.await();
            System.out.println("所有选手都已赛跑完成");
            System.out.println("裁判" + Thread.currentThread().getName() + "计算成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
