package com.wuhulala.javase.concurrent.synchronize;

/**
 * author： wuhulala
 * date： 2017/8/6
 * version: 1.0
 * description: 测试单个
 */
public class SynchronizedStaticTest{
    public static void main(String[] args) {
        CommonMethodSynchronized test = new CommonMethodSynchronized();
        CommonMethodTest1 thread1 = new CommonMethodTest1(test);
        CommonMethodTest2 thread2 = new CommonMethodTest2(test);
        CommonMethodTest3 thread3 = new CommonMethodTest3(test);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class CommonMethodTest1 extends Thread{
    private CommonMethodSynchronized commonMethodSynchronized;

    public CommonMethodTest1(CommonMethodSynchronized commonMethodSynchronized) {
        this.commonMethodSynchronized = commonMethodSynchronized;
    }

    @Override
    public void run() {
        CommonMethodSynchronized.method1();
    }
}

class CommonMethodTest2 extends Thread{
    private CommonMethodSynchronized commonMethodSynchronized;

    public CommonMethodTest2(CommonMethodSynchronized commonMethodSynchronized) {
        this.commonMethodSynchronized = commonMethodSynchronized;
    }

    @Override
    public void run() {
        commonMethodSynchronized.method2();
    }
}

class CommonMethodTest3 extends Thread{
    private CommonMethodSynchronized commonMethodSynchronized;

    public CommonMethodTest3(CommonMethodSynchronized commonMethodSynchronized) {
        this.commonMethodSynchronized = commonMethodSynchronized;
    }

    @Override
    public void run() {
        commonMethodSynchronized.method3();
    }
}