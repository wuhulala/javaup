package com.wuhulala.javase.concurrent.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * author： wuhulala
 * date： 2017/8/6
 * version: 1.0
 * description: 在静态方法添加不可重入锁
 */
public class StaticMethodSynchronized {
    public synchronized static void method1(){
        System.out.println(Thread.currentThread().getName() + ":::method1...start");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":::method1...end");
    }

    public static void method2(){
        System.out.println(Thread.currentThread().getName() + ":::method2...start");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":::method2...end");
    }
}
