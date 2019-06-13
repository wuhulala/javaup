package com.wuhulala.javase.thread;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/16
 * @link https://github.com/wuhulala
 */
public class SynchronizeByteTest {
    private String name;

    public SynchronizeByteTest(String name) {
        this.name = name;
    }

    public void testLockString() throws InterruptedException {
        //synchronized一个字符串 再多个实例下是也是可以锁住的
        synchronized ("a请问"){
            System.out.println("------------------------start: "+Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("my name is xueaohui");
            System.out.println("------------------------end: "+Thread.currentThread().getName());

        }

    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            final SynchronizeByteTest s1 = new SynchronizeByteTest("aaaa");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        s1.testLockString();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        Thread.sleep(2000);
        String a = "a请问";
        synchronized (a) {
            System.out.println(a);
        }
    }
}
