package com.wuhulala.javase.concurrent.threadpool;

/**
 * author： wuhulala
 * date： 2017/8/26
 * version: 1.0
 * description: 作甚的
 */
public class TaskUtils {
    public static Runnable newTask(){
        return ()->{
            System.out.println(Thread.currentThread().getName() + ": 执行此任务: now is " + System.currentTimeMillis());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
