package com.wuhulala.javase.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author： wuhulala
 * date： 2017/8/26
 * version: 1.0
 * description: 自适应的线程池 [0,2^32-1] 如果线程不够，会新建线程，线程如果不使用，线程会在60s后释放掉
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 100; i++) {
                executor.submit(TaskUtils.newTask());
            }
        }finally {
            executor.shutdown();
        }
    }
}
