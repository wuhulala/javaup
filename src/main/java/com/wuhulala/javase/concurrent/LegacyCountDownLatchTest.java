package com.wuhulala.javase.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author： wuhulala
 * date： 2017/8/24
 * version: 1.0
 * description: 自定义的CountDownLatch的实现
 * <p>可以在await的时候判断当前是否还需等待其它变量，如需等待的话就阻塞住，不然的话就运行，当其它被等待的变量自己表示完成的时候，就可以通知信号量，。</p>
 * <p>CountDownLatch的场景可以用于 一件事情依赖于多个事情的时候。比如一个请求依赖于上几个请求的结果。</p>
 *
 *
 */
public class LegacyCountDownLatchTest implements Runnable {
    private static final LockCountDownLatch end = new LockCountDownLatch(10);
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


    /**
     * 使用锁和Condition 模拟 CountDownLatch
     */
    public static class LockCountDownLatch {

        private int count;

        private ReentrantLock lock = new ReentrantLock();

        /**
         * Condition 模拟 Object  ({@link Object#wait() wait}, {@link Object#notify notify} and {@link Object#notifyAll notifyAll})
         *
         */
        private final Condition condition = lock.newCondition();

        public LockCountDownLatch(int count) {
            this.count = count;
        }

        public void countDown() throws InterruptedException {
            if (Thread.interrupted()){
                throw new InterruptedException();
            }
            try {
                lock.lock();
                this.count--;
                if (this.count == 0) {
                    condition.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }

        public void await() throws InterruptedException {
            if (Thread.interrupted()){
                throw new InterruptedException();
            }
            try {
                lock.lock();
                if (this.count > 0) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }

    }
}
