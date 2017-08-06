package com.wuhulala.javase.concurrent.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * author： wuhulala
 * date： 2017/8/6
 * version: 1.0
 * description: 在普通方法添加不可重入锁
 *  synchronized 的本质就是一个不可重入锁，加锁的粒度和本质大致有
 *   1. 有对象锁 synchronized(this),或者非静态方法上加synchronized修饰
 *   2. 类锁 synchronized(XX.class),或者静态方法上加synchronized修饰
 *
 *   所以会有一些情况，是并发的，比如method1和method2的开始时间是同时的，因为method1的锁对应的是类锁，而method对应的是当前对象的锁，而method3和method2是有严格的顺序关系
 */
public class CommonMethodSynchronized {
    public synchronized static void method1(){
        System.out.println(Thread.currentThread().getName() + ":::method1...start");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":::method1...end");
    }

    public synchronized void method2(){
        System.out.println(Thread.currentThread().getName() + ":::method2...start");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":::method2...end");
    }

    public synchronized void method3(){
        System.out.println(Thread.currentThread().getName() + ":::method3...start");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":::method3...end");
    }
}
