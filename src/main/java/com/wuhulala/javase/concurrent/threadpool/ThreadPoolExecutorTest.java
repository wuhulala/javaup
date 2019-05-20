package com.wuhulala.javase.concurrent.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/24<br>
 */
public class ThreadPoolExecutorTest {


    ///////////////////////////// 方法区 ////////////////////////////////////
    public static void main(String[] args) {
        int coreSize = 10;
        int maxSize = 0x7FFFFFFF;
        System.out.println(maxSize);
        int queue = 10;
        int alive = 0;
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(coreSize,
                maxSize, alive, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queue),
                new NamedThreadFactory("wuhulala"));


        for (int i = 0; i < 100; i++) {
            tpe.execute(new NameRunnable("thread:::" + i));
        }


    }

    public static class NameRunnable implements Runnable {

        private String name;

        public NameRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "\tin\t" + Thread.currentThread().getName() + "\tstarting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "\tin\t" + Thread.currentThread().getName() + "\tended...");
        }
    }

    public static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        private final String mPrefix;

        private final boolean mDaemo;

        private final ThreadGroup mGroup;

        public NamedThreadFactory() {
            this("pool-" + POOL_SEQ.getAndIncrement(), false);
        }

        public NamedThreadFactory(String prefix) {
            this(prefix, false);
        }

        public NamedThreadFactory(String prefix, boolean daemo) {
            mPrefix = prefix + "-thread-";
            mDaemo = daemo;
            SecurityManager s = System.getSecurityManager();
            mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
        }

        public Thread newThread(Runnable runnable) {
            String name = mPrefix + mThreadNum.getAndIncrement();
            Thread ret = new Thread(mGroup, runnable, name, 0);
            ret.setDaemon(mDaemo);
            return ret;
        }

        public ThreadGroup getThreadGroup() {
            return mGroup;
        }
    }
}
