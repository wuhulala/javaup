package com.wuhulala.javase.concurrent.synchronize;

/**
 * 功能说明: synchronize 的可重入测试<br>
 * 注意事项: 经测试 是是可重入锁<br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/6/16<br>
 */
public class SynchronizeTest {


    ///////////////////////////// 方法区 ////////////////////////////////////
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                SynchronizeTest test = new SynchronizeTest();
                test.m1();
            }).start();
        }


    }

    public void m1(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "：：：：：：第一次进入");
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + "：：：：：：第二次进入");
            }
        }
    }
}
