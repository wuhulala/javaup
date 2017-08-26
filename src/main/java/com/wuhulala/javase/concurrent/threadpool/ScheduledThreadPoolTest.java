package com.wuhulala.javase.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * author： wuhulala
 * date： 2017/8/26
 * version: 1.0
 * description: 具有计划任务的线程池
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(10);

        //2s 执行一次
        // 如果任务执行时间变为10s 那么真正的周期变为了10s Why????
        // 因为2s 执行一次询问，那么在任务为1s的时候，上个任务已经完成了，所以直接开始任务，时间是2s
        // 但是10s的时候 2s执行一次询问，任务还在执行，所以就等待任务完成，
        scheduledThreadPoolExecutor.scheduleAtFixedRate(TaskUtils.newTask(), 0 , 3 , TimeUnit.SECONDS);

    }
}
