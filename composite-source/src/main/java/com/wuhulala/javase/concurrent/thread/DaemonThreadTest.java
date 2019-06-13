package com.wuhulala.javase.concurrent.thread;

/**
 * author： wuhulala
 * date： 2017/8/21
 * version: 1.0
 * description: 守护线程
 */
public class DaemonThreadTest {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true) {
                System.out.println("i am alive！！！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();
        //如果当前是守护线程，那么它两秒后会推出！！！
        Thread.sleep(2000);
    }
}
