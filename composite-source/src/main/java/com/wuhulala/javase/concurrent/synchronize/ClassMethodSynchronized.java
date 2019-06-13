package com.wuhulala.javase.concurrent.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * author： wuhulala
 * date： 2017/8/6
 * version: 1.0
 * description: 测试synchronized代码块
 */
public class ClassMethodSynchronized {
    public static void main(String[] args) {
        ClassMethodSynchronized test = new ClassMethodSynchronized();
        ClassTest1 thread1 = new ClassTest1(test);
        ClassTest2 thread2 = new ClassTest2(test);
        ClassTest3 thread3 = new ClassTest3(test);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public void method1(){
        synchronized (this.getClass()){
            System.out.println(Thread.currentThread().getName() + ":::method1...start");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":::method1...end");
        }
    }

    public void method2(){
        synchronized (this.getClass()){
            System.out.println(Thread.currentThread().getName() + ":::method2...start");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":::method2...end");
        }
    }

    public void method3(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + ":::method3...start");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":::method3...end");
        }
    }
}

class ClassTest1 extends Thread{
    private ClassMethodSynchronized classMethodSynchronized;

    public ClassTest1(ClassMethodSynchronized classMethodSynchronized) {
        this.classMethodSynchronized = classMethodSynchronized;
    }

    @Override
    public void run() {
        classMethodSynchronized.method1();
    }
}

class ClassTest2 extends Thread{
    private ClassMethodSynchronized classMethodSynchronized;

    public ClassTest2(ClassMethodSynchronized classMethodSynchronized) {
        this.classMethodSynchronized = classMethodSynchronized;
    }

    @Override
    public void run() {
        classMethodSynchronized.method2();
    }
}

class ClassTest3 extends Thread{
    private ClassMethodSynchronized classMethodSynchronized;

    public ClassTest3(ClassMethodSynchronized classMethodSynchronized) {
        this.classMethodSynchronized = classMethodSynchronized;
    }

    @Override
    public void run() {
        classMethodSynchronized.method3();
    }
}