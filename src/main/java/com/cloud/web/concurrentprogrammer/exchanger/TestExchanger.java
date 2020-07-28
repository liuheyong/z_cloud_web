package com.cloud.web.concurrentprogrammer.exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: wenyixicodedog
 * @create: 2020-07-28
 * @description:
 */
public class TestExchanger {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        final Exchanger exchanger = new Exchanger();

        service.execute(() -> {
            String date1 = "hello";
            System.out.println(Thread.currentThread().getName() + "把数据" + date1 + "放入exchanger");
            try {
                Thread.sleep(new Random().nextInt((3000 - 1000) + 1) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String date2 = null;
            try {
                date2 = (String) exchanger.exchange(date1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "从exchanger获取到数据" + date2);
        });

        service.execute(() -> {
            String date1 = "bye";
            System.out.println(Thread.currentThread().getName() + "把数据" + date1 + "放入exchanger");
            try {
                Thread.sleep(new Random().nextInt((3000 - 1000) + 1) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String date2 = null;
            try {
                date2 = (String) exchanger.exchange(date1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "从exchanger获取到数据" + date2);
        });

        service.shutdown();
    }

}
