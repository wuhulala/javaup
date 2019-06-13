package com.wuhulala.javase.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * author： wuhulala
 * date： 2017/8/24
 * version: 1.0
 * description: 线程阻塞工具类
 */
public class LockSupportTest {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread();
    static ChangeObjectThread t2 = new ChangeObjectThread();

    private static class ChangeObjectThread extends Thread{
        @Override
        public void run() {
            synchronized (u){
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
