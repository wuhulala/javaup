package com.wuhulala.javase.box;

/**
 * author： wuhulala
 * date： 2017/9/12
 * version: 1.0
 * description: <p>这个是可以正常运行的，如果把static去掉就会空指针异常

 1: null可以转化为任何类型

 2：static静态关键字 仅仅意味着可以不用实例化这个类

 通过类名.方法名就可以访问

 当然也可以通过实例化类的对象后 通过对象.方法名

 但是不能通过this关键字，因为this是指本实例中的方法被static声明的方法属于类的方法</p>
 */
public class TestNullClass {
    public static void testMethod(){
        System.out.println("test method...");
    }

    public static void main(String[] args) {
        ((TestNullClass)(null)).testMethod();
    }
}
