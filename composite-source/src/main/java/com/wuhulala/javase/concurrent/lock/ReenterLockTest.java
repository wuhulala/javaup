package com.wuhulala.javase.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * author： wuhulala
 * date： 2017/8/22
 * version: 1.0
 * description: 最简单的重入锁
 */
public class ReenterLockTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockTest threadLock = new ReenterLockTest();
        Thread t1 = new Thread(threadLock);
        Thread t2 = new Thread(threadLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
