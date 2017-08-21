package com.wuhulala.javase.concurrent.thread;

/**
 * author： wuhulala
 * date： 2017/8/21
 * version: 1.0
 * description: 最简单的runnable
 */
public class RunnableTest implements Runnable {

    public static void main(String[] args) {
        new Thread(new RunnableTest()).start();
    }
    @Override
    public void run() {
        System.out.println("I am a runnable , 我就是比 Thread 好！！！");
    }
}
