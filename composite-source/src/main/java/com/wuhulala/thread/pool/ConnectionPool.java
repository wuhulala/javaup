package com.wuhulala.thread.pool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectionPool {

    private List<String> pool= new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));

    // 如果这个时候把线程给睡眠，估计cpu就不会爆炸，不然会一致重试。链接池的原理？？？？
    public synchronized String getConnection() {
        if (!pool.isEmpty()) {
            String s = pool.get(0);
            pool.remove(0);
            return s;
        }
        return null;
    }

    public synchronized void release(String s) {
        pool.add(s);
    }

    public static void main(String[] args) {
        int n = 200;
        ConnectionPool pool = new ConnectionPool();

        for (int i = 0; i < n; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String poolName = null;
                    try {
                        while ((poolName = pool.getConnection()) == null) {
                            System.out.println(Thread.currentThread().getName() + "can not getObtain Pool");
                        }
                        System.out.println(Thread.currentThread().getName() + "obtain Pool [" + poolName + "]");
                        // 模拟慢sql
                        Thread.sleep(200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        pool.release(poolName);
                    }
                }
            }).start();
        }
    }
}
