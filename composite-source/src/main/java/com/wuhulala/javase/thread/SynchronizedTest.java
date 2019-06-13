package com.wuhulala.javase.thread;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/16
 * @link https://github.com/wuhulala
 */
public class SynchronizedTest {
    private int num;

    // 1 synchronized 加在方法上 只能保证同一个实例不会重复进入这个方法两次，如果是两个对象实例 对于对象来说 这是徒劳的
    // ，如果想实现两个对象实例不能进入同一个类的方法
    // （1） 方法设置为static方法，因为这个static方法在整个jvm中只有一个实例
    // （2) synchronized (this.getClass()) 锁住这个类 ，因为类在jvm中也是只有一个实例
    // （3) synchronized ("a") 锁住这个字符串 ，因为这个字符串也是在jvm只要一个实例
    public void printNumber(String type) throws InterruptedException {
        synchronized (this.getClass()) {
            if ("a".equals(type)) {
                System.out.println("进入a----设置方法");
                Thread.sleep(1000);
                this.num = 100;
                System.out.println("a--设置 num[" + num + "]");
            } else if ("b".equals(type)) {
                System.out.println("进入b----设置方法");
                Thread.sleep(1000);
                this.num = 200;
                System.out.println("b--设置 num[" + num + "]");
            }
        }
    }

    //两个对象两把锁，运行结果还是异步的

    public static void main(String[] args) {
        final SynchronizedTest s1 = new SynchronizedTest();
        final SynchronizedTest s2 = new SynchronizedTest();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s1.printNumber("a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s2.printNumber("b");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
