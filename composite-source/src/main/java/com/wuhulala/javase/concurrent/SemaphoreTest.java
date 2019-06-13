package com.wuhulala.javase.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * author： wuhulala
 * date： 2017/8/23
 * version: 1.0
 * description: 测试信号量
 */
public class SemaphoreTest implements Runnable{
    private static Semaphore sema = new Semaphore(5);

    @Override
    public void run() {
        try {
            sema.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ": done!!!");
            sema.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        //妈的 还迷茫了一下 这是同一个信号量吗？ o o o  确实是，zz了 最好还是别这样玩 大兄弟 还是加个static吧
        SemaphoreTest test = new SemaphoreTest();

        try {
            for (int i = 0; i < 20; i++) {
//                SemaphoreTest test = new SemaphoreTest();

                exec.submit(test);
            }
        }finally {
            exec.shutdown();
        }
    }
}
