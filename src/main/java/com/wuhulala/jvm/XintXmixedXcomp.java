package com.wuhulala.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: com.wuhulala.jvm<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/4/25<br>
 */
public class XintXmixedXcomp {
    public static void main(String[] args) {
        //=========================单个==============================
         /*run in -Xint 强制执行所有字节码 Average time: 1613*/
        /*run in -Xcomp 第一次使用时把字节码转换成本地代码 Average time: 254*/
        /*run in -Xmixed JVM默认支持的模式 无需设置  Average time: 231*/
        //=========================多个==============================
         /*run in -Xint 强制执行所有字节码 Average time: 7223*/
        /*run in -Xcomp 第一次使用时把字节码转换成本地代码 Average time: 1411*/
        /*run in -Xmixed JVM默认支持的模式 无需设置  Average time: 1312*/
        long startTime = System.currentTimeMillis();
        putAll();
        putAll();
        putAll();
        putAll();
        putAll();
        System.out.println("Average time: " + (System.currentTimeMillis() - startTime));


    }

    private static void putAll() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            map.put(String.valueOf(i), i);
        }
    }
}
