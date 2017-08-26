package com.wuhulala.javase.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author： wuhulala
 * date： 2017/8/26
 * version: 1.0
 * description: 固定数量的线程池
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) {


        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 10; i++) {
                executor.submit(TaskUtils.newTask());
            }
        }finally {
            executor.shutdown();
        }
    }
}
