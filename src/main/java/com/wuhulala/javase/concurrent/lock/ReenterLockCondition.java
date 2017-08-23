package com.wuhulala.javase.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author： wuhulala
 * date： 2017/8/23
 * version: 1.0
 * description: ReenterLock和Condition
 */
public class ReenterLockCondition implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            System.out.println("enter run method");
            lock.lock();
            System.out.println("run method get lock success");
            condition.await();  //相当于Object.wait(),会释放这把锁，等待别人唤醒之后会重新获得锁
            System.out.println("Thread is going on");
            System.out.println("out run method");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition tlc = new ReenterLockCondition();
        Thread t1 = new Thread(tlc);
        t1.start();
        System.out.println(Thread.currentThread().getName() + " thread sleep two seconds start");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " thread sleep two seconds end");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " thread get lock success");

        condition.signal();  ////相当于Object.notify()
        System.out.println(Thread.currentThread().getName() + " notify thread");

        lock.unlock();
    }
}
