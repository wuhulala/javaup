package com.wuhulala.javase.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * author： wuhulala
 * date： 2017/8/26
 * version: 1.0
 * description: ThreadFactory的作用就是在线程新建线程的策略,默认有demon等等
 */
public class SingleThreadPoolWithThreadFactoryTest {

    static class MyThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            System.out.println("使用了我自己新建线程的策略");
            return new Thread(r);
        }
    }

    public static void main(String[] args) {


        ExecutorService executor = Executors.newSingleThreadExecutor(new MyThreadFactory());
        try {
            for (int i = 0; i < 10; i++) {
                executor.submit(TaskUtils.newTask());
            }
        }finally {
            executor.shutdown();
        }
    }
}
