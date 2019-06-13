package com.wuhulala.javase.concurrent.threadlocal;

import java.util.function.Supplier;

/**
 * 线程上下文
 *
 * @author wuhulala<br>
 * @date 2019/5/23<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class ThreadLocalContext {


    private String threadName;

    private long start;

    private long end;

    private final static ThreadLocal<ThreadLocalContext> contextThreadLocal = ThreadLocal.withInitial(new Supplier<ThreadLocalContext>() {
        @Override
        public ThreadLocalContext get() {
            return new ThreadLocalContext();
        }
    });

    public ThreadLocalContext() {
        this.threadName = Thread.currentThread().getName();
        this.start = System.currentTimeMillis();
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getInterval() {
        return this.end - this.start;
    }

    public static ThreadLocalContext getInstance() {
        return contextThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalContext context = new ThreadLocalContext();

        Thread thread1 = new Thread(() -> {
            System.out.println(ThreadLocalContext.getInstance().threadName);
            try {
                Thread.sleep(2343);
                ThreadLocalContext.getInstance().setEnd(System.currentTimeMillis());
                System.out.println(ThreadLocalContext.getInstance().getInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "thread1");

        Thread thread2 = new Thread(() -> {
            System.out.println(ThreadLocalContext.getInstance().threadName);
            try {
                Thread.sleep(4343);
                ThreadLocalContext.getInstance().setEnd(System.currentTimeMillis());
                System.out.println(ThreadLocalContext.getInstance().getInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "thread2");

        thread1.start();
        thread2.start();
    }





}
