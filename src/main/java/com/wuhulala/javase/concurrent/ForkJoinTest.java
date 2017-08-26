package com.wuhulala.javase.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * author： wuhulala
 * date： 2017/8/26
 * version: 1.0
 * description: fork/join框架
 */
public class ForkJoinTest extends RecursiveTask<Long> {
    public static final int THRESHOLD = 10000;

    private long start;
    private long end;


    public ForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean isNeedAdd = end < start + THRESHOLD;
        //System.out.println(isNeedAdd);
        if (isNeedAdd) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //2分
            long mid = (start + end) >> 1;
            //System.out.println(start + "------" +mid + "------" + end);
            ForkJoinTest sumTask1 = new ForkJoinTest(start, mid);
            ForkJoinTest sumTask2 = new ForkJoinTest(mid + 1, end);
            sumTask1.fork();
            sumTask2.fork();
            sum = sumTask1.join() + sumTask2.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        // n 越大优势才明显，之前由于线程的切换什么并没有优势
        // n = 10000000000L 已经有两倍的优势了
        long n = 10000000000L;
        long sum = 0;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= n; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(" sum = " + sum);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest test = new ForkJoinTest(0, n);
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(test);
        start = System.currentTimeMillis();
        try {
            long result = forkJoinTask.get();
            end = System.currentTimeMillis();
            System.out.println(" sum = " + result);

            System.out.println(end - start);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
