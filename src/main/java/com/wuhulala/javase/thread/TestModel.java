package com.wuhulala.javase.thread;

/**
 * volatile 能够确保被修饰的属性在后面（时间上的顺序）的线程每一次都可以拿到最新的值，因为他会刷新到主内存中
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/16
 * @link https://github.com/wuhulala
 */
public class TestModel extends Thread{
    //volatile
    private  boolean num = true;


    public void shutdown(boolean state) {
        this.num = state;
    }

    @Override
    public void run() {
        System.out.println("开始工作");
        while (this.num) {
            System.out.println("工作。。。");
            try {
                Thread.sleep(2000); //模拟工作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("工作结束");
    }



    public static void main(String[] args) throws InterruptedException {
        TestModel testModel = new TestModel();

        testModel.start();

        Thread.sleep(3000);

        testModel.shutdown(false);
        System.out.println("设置停止指令");
    }
}
