package com.wuhulala.javase.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * author： wuhulala
 * date： 2017/8/23
 * version: 1.0
 * description: 中断锁测试
 */
public class InterruptLockTest implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public InterruptLockTest(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            }else{
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() +":线程退出！！！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptLockTest ilt1 = new InterruptLockTest(1);
        InterruptLockTest ilt2 = new InterruptLockTest(2);

        Thread t1 = new Thread(ilt1);
        Thread t2 = new Thread(ilt2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
