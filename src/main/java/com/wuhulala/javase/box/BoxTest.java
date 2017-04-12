package com.wuhulala.javase.box;

/**
 * 自动拆箱与装箱
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/12
 * @link https://github.com/wuhulala
 */
public class BoxTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d); //  true 两个自动装箱 Integer.valueOf() 因为-127-128是缓存在Integer里面的  所以地址相同
        System.out.println(e == f); //  false 两个自动装箱 Integer.valueOf() 生成了两个对象 并且没有在缓存中 故错误
        System.out.println(c == (a + b)); // true 自动拆箱
        System.out.println(c.equals(a + b));//true 自动拆箱
        System.out.println(g == a + b);//true  自动拆箱
        System.out.println(g.equals(a + b));// 因为不是Long类型的

    }
}
