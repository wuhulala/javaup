package com.wuhulala.javase.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author： wuhulala
 * date： 2017/8/24
 * version: 1.0
 * description: 倒记数器  原理实现AbstractOwnableSynchronizer
 */
public class CountDownLatchTest implements Runnable{
    private static final CountDownLatch end = new CountDownLatch(10);
    private static final CountDownLatchTest test = new CountDownLatchTest();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "::: check complete");
            // 信号量减一
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        System.out.println("准备发射火箭::::");

        for (int i = 0; i < 10; i++) {
            exec.submit(test);
        }

        //等待为0
        end.await();

        //发射火箭
        System.out.println("发射！！！ 发射！！！");
        exec.shutdown();
    }
}
