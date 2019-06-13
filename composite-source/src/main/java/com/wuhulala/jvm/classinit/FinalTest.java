package com.wuhulala.jvm.classinit;

/**
 * 虽然程序中引用了 const 类的常量 NAME，
 * 但是在编译阶段将此常量的值“我是常量”存储到了调用它的类 FinalTest 的常量池中，
 * 对常量 Const2.NAME 的引用实际上转化为了 FinalTest 类对自身常量池的引用。
 * 也就是说，实际上 FinalTest 的 Class 文件之中并没有 Const2 类的符号引用入口，
 * 这两个类在编译成 Class 文件后就不存在任何联系了
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/12
 * @link https://github.com/wuhulala
 */
class Const{
    public static final String NAME = "我是常量";
    static{
        System.out.println("初始化Const类");
    }
}

public class FinalTest{
    public static void main(String[] args){
        System.out.println(Const.NAME);
        //我是常量 只有一句
    }
}
