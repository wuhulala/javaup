package com.wuhulala.jvm.outofmemory;

/**
 * 一般来说是不可能的，只有项目启动方法区内存很小或者项目中的静态变量极其多时才会发生
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class ConstantPoolOutOfMemory {
    public static void main(String[] args) {
        for (int i = 0 ; i < 10000000; i++){
            String.valueOf(i).intern();
        }
    }
}
