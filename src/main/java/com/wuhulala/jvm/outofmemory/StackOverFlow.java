package com.wuhulala.jvm.outofmemory;

/**
 * 栈溢出 一般指函数自我调用次数过多
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class StackOverFlow {
    public static void main(String[] args) {
        new StackOverFlow().resume(1);
    }

    public void resume(int i ){
        System.out.println("===========================>:"+i);
        resume(i + 1);
    }
    //Exception in thread "main" java.lang.StackOverflowError
}
