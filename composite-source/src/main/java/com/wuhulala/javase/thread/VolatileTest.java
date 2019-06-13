package com.wuhulala.javase.thread;

/**
 * @author wuhulala
 * @version 1.0
 * @date 2017/10/28
 * @description 作甚的
 */
public class VolatileTest {
    private static volatile boolean flag = true;

    public static void main(String[] args) {
         int i = 0;
         int j = 1;
         flag = false;
         i = 2;
         j = 3;
    }
}
