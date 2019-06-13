package com.wuhulala.javase.concurrent.thread;

/**
 * author： wuhulala
 * date： 2017/8/21
 * version: 1.0
 * description: 线程中断测试
 */
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {

        // Thread.sleep()方法由于中断而抛出异常，
        // 此时，它会清除中断标记，
        // 如果不加处理，那么下一次循环开始时，就无法捕获这个中断
        // 所以在异常处理的时候需要再次设置当前线程的中断状态
        Thread t1 = new Thread(
                () -> {
                    int count = 0;
                    while (true) {
                        if (Thread.currentThread().isInterrupted()) {
                            System.out.println("Interrupted!!!");
                            break;
                        }
                        try {
                            Thread.sleep(100); //无论睡眠多久，都会在2s的时候被中断
                            System.out.println("运行了第"+ ++count +"次");
                        } catch (InterruptedException e) {
                            System.out.println("Interrupted When Sleep");
                            Thread.currentThread().interrupt();  //注释掉此条语句则永远不会被中断
                        }
                    }
                }
        );
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}