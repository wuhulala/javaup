package com.wuhulala.javase.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author： wuhulala
 * date： 2017/8/26
 * version: 1.0
 * description: 一个线程的线程池
 */
public class SingleThreadPoolTest {

    public static void main(String[] args) {


        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 10; i++) {
                executor.submit(TaskUtils.newTask());
            }
        }finally {
            executor.shutdown();
        }
    }
}
