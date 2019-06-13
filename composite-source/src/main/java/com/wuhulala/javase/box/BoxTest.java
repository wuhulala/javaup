package com.wuhulala.javase.box;

/**
 * 自动拆箱与装箱  慎用
 * 1、 ==在不遇到算数运算情况下 不会自动拆箱
 * 2、 equals() 方法是 instanceof 判断类型是否相同 不会转型 有时候会直接gg了
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/12
 * @link https://github.com/wuhulala
 */
public class BoxTest {
    public static void main(String[] args) {
        Integer a = 1; //自动装箱 Integer.valueOf()
        Integer b = 2; //自动装箱 Integer.valueOf()
        Integer c = 3; //自动装箱 Integer.valueOf()
        Integer d = 3; //自动装箱 Integer.valueOf()
        Integer e = 321; //自动装箱 Integer.valueOf()
        Integer f = 321; //自动装箱 Integer.valueOf()
        Long g = 3L; //自动装箱 Long.valueOf()

        //Integer.valueOf()
        System.out.println(c == d); //  true 两个 因为-127-128是缓存在Integer里面的  所以地址相同
        System.out.println(e == f); //  false 生成了两个数字对象 并且没有在缓存中 相当于两个新的地址 故错误
        System.out.println(c == (a + b)); // true 自动拆箱
        System.out.println(c.equals(a + b));//true 自动拆箱
        System.out.println(g == a + b);//true  自动拆箱
        System.out.println(g.equals(a + b));// 因为 因为a+b=3 所以就装箱为Integer 不是Long类型的 返回false

    }
    //装箱方法
//    //public static Integer valueOf(int i) {
//        if (i >= Integer.IntegerCache.low && i <= Integer.IntegerCache.high)
//            return Integer.IntegerCache.cache[i + (-Integer.IntegerCache.low)];
//        return new Integer(i);
//}
}
