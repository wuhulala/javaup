package com.wuhulala.javase.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * author： wuhulala
 * date： 2017/8/24
 * version: 1.0
 * description: <p>循坏栅栏，或许可以认为是CountDownLatch的循环版，
 *  会在信号量为零的时候，调用这个runnable，可以认为这是一个回调函数，java中回调函数就是这么实现的</p>
 */
public class CyclicBarrierTest {
    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() throws InterruptedException {
            System.out.println(soldier + ": 开始进行任务");
            Thread.sleep(1000);
            System.out.println(soldier + ": 任务完成");
        }
    }

    public static class BarrierRun implements Runnable{
        Boolean flag;
        int N;

        public BarrierRun(Boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if(flag){
                System.out.println("司令：士兵[" + N + "]个完成了各自的工作");
            }else{
                System.out.println("司令：士兵[" + N + "]个集合完毕，一起干活吧");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSolider = new Thread[N];
        boolean flag = false;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));

        System.out.println("嘟嘟嘟！！！集合队伍！！！ 五分钟集合要完毕！");
        for (int i  = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道！！！");
            allSolider[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            allSolider[i].start();
        }
    }

}
