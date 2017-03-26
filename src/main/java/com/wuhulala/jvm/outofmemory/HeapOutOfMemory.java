package com.wuhulala.jvm.outofmemory;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class HeapOutOfMemory {
    private static final int _1MB = 1024 * 1024;

    //-Xms20M -Xmx20M -Xmn10M
    public static void main(String[] args) {
        byte[] a1,a2;
        a1 = new byte[_1MB *10];
        a2 = new byte[_1MB * 10];
    }
    //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
}
