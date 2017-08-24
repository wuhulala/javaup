package com.wuhulala.javase.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author： wuhulala
 * date： 2017/8/24
 * version: 1.0
 * description: 读写锁测试,读写锁性能适用于读操作远远大于写操作的场景
 */
public class ReadWriteLockTest {
    private static Lock lock = new ReentrantLock();
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "::::read..." + value);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "::::write..." + value);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockTest test = new ReadWriteLockTest();

        Runnable readRunnable = () -> {
            try {
                //test.handleRead(lock);
                test.handleRead(readLock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                //test.handleWrite(lock, new Random().nextInt());
                test.handleWrite(writeLock, new Random().nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }

        // ReadWriteLock 2s
        // ReenterLock 22s
    }
}
