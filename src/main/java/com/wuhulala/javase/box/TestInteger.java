package com.wuhulala.javase.box;

/**
 * author： wuhulala
 * date： 2017/9/12
 * version: 1.0
 * description: 作甚的
 */
public class TestInteger {
    public static void main(String[] args) {
        int i = 0 ;
        Integer j = new Integer(0);
        Integer k = new Integer(0);

        System.out.println(i == j); // true
        //因为i会被自动装箱
        System.out.println(j.equals(i)); // true

        System.out.println(j.equals(k)); // true

        int ii = 1000 ;
        Integer jj = new Integer(1000);
        Integer kk = new Integer(1000);
        System.out.println(ii == jj); // true
        //因为i会被自动装箱
        System.out.println(jj.equals(ii)); // true

        System.out.println(jj.equals(kk)); // true

        System.out.println(Integer.valueOf(10) == Integer.valueOf(10)); //true
        System.out.println(Integer.valueOf(1000) == Integer.valueOf(1000)); //false

    }
}
