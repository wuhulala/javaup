package com.wuhulala.javase.collection;

/**
 * 位运算
 * <pre>
 *      +-------------------+-------------------------------------+
 *      | bit operation     |                description          |
 *      +-------------------+-------------------------------------+
 *      |  <<               | 左移运算符，num << 1,相当于num乘以2     |
 *      +-------------------+-------------------------------------+
 *      |  >>               | 右移运算符，num >> 1,相当于num除以2     |
 *      +-------------------+-------------------------------------+
 *      | >>>               | 无符号右移，忽略符号位，空位都以0补齐     |
 *      +-------------------+-------------------------------------+
 * </pre>
 *
 * @author wuhulala
 * @version 1.0
 * @date 2017/12/3
 */
public class BitOperationTest {
    public static void main(String[] args) {
        int x = 11;
        System.out.println(x + "的二进制：" + Integer.toBinaryString(x));

        System.out.println("x << 1: " +  (x << 1) + "=======" + Integer.toBinaryString(x << 1));
        System.out.println("x >> 1: " + (x >> 1) + "=======" + Integer.toBinaryString(x >> 1));
        System.out.println("x >>> 1: " + (x >>> 1) + "=======" + Integer.toBinaryString(x >>> 1));

        //溢出测试, 左移超过界限的时候会溢出变为负数 因为第一位是符号位
        System.out.println("x << 28: " +  (x << 28) + "=======" + Integer.toBinaryString(x << 28));

        System.out.println("x >> 20: " + (x >> 20) + "=======" + Integer.toBinaryString(x >> 20));

        System.out.println();
        System.out.println("+=======================================================================+");
        System.out.println("+=======================================================================+");
        System.out.println("+=======================================================================+");
        System.out.println();

        x = -11;
        System.out.println(x + "的二进制(内存存取 即补码)：" + Integer.toBinaryString(x));

        System.out.println("x << 1: " +  (x << 1) + "=======" + Integer.toBinaryString(x << 1));
        System.out.println("x >> 1: " + (x >> 1) + "=======" + Integer.toBinaryString(x >> 1));
        System.out.println("x >>> 1: " + (x >>> 1) + "=======" + Integer.toBinaryString(x >>> 1));

        System.out.println("x << 27: " +  (x << 27) + "=======" + Integer.toBinaryString(x << 27));
        System.out.println("x << 28: " +  (x << 28) + "=======" + Integer.toBinaryString(x << 28));

        System.out.println("x >> 20: " + (x >> 20) + "=======" + Integer.toBinaryString(x >> 20));

        System.out.println(Integer.MAX_VALUE);
        System.out.println((1 << 31) - 1 == Integer.MAX_VALUE);
        //位运算优先级 小于 加减乘除
        System.out.println(1 << 31 - 1);
    }
}
