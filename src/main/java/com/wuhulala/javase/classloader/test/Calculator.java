package com.wuhulala.javase.classloader.test;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/5/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class Calculator {

    public int add(int a, int b) {
        print();
        System.out.println(String.format("%d + %d = %d ----- v2", a, b, a + b));
        return a + b;
    }

    public void print(){
        System.out.println("print");
    }

    public int minus(int a, int b) {
        return a - b;
    }


}
