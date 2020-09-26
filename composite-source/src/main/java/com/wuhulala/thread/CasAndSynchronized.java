package com.wuhulala.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * http://hollischuang.gitee.io/tobetopjavaer/
 *
 * @author wuhulala<br>
 * @date 2019/12/7<br>
 * @since v1.0<br>
 */
public class CasAndSynchronized {

    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int j = 0; j < 200; j++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k = 0; k < 100000000; k++) {

                        i.incrementAndGet();
                    }
                }
            });
            t1.start();
        }


        Thread.sleep(120000);
        System.out.println(i);

    }

}
