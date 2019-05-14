package com.wuhulala.javase.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author： wuhulala
 * date： 2017/8/24
 * version: 1.0
 * description: 倒记数器  原理实现AbstractOwnableSynchronizer
 */
public class LegacyCountDownLatchTest implements Runnable {
    private static final LegacyCountDownLatch end = new LegacyCountDownLatch(10);
    private static final LegacyCountDownLatchTest test = new LegacyCountDownLatchTest();

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

    /**
     * 自定义实现的CountDownLatch
     */
    public static class LegacyCountDownLatch {

        private int count;

        public LegacyCountDownLatch(int count) {
            if (count < 0) throw new IllegalArgumentException("count < 0");
            this.count = count;
        }

        public void await() throws InterruptedException {

            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            // wait 必须依赖于 synchronized
            synchronized (this) {
                if (this.count > 0) {
                    wait();
                }
            }

        }


        public void countDown() throws InterruptedException {

            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            synchronized (this) {
                if (this.count < 1) {
                    return;
                }
                this.count--;
                if (this.count == 0) {
                    notifyAll();
                }
            }
        }


    }
}
