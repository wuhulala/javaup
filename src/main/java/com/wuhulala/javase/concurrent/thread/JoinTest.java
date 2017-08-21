package com.wuhulala.javase.concurrent.thread;

/**
 * author： wuhulala
 * date： 2017/8/21
 * version: 1.0
 * description: 测试join
 */
public class JoinTest {
    private volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 1000000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join(); //不添加会输出0或者比100000小的数字，因为如下所示，会一直等待
        //===================join 源码====================
        //        if (millis == 0) {
        //            while (isAlive()) {
        //                wait(0);
        //            }
        //        }
        //===================join 源码====================

        System.out.println(i);
    }
}
