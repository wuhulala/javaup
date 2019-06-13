package com.wuhulala.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * 一般来说是不可能的，只有项目启动方法区内存很小或者项目中的静态变量极其多时才会发生
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class ConstantPoolOutOfMemory {
    static String base = "string";

    //-XX:MetaspaceSize=8M -XX:MaxMetaspaceSize=8M
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

}
