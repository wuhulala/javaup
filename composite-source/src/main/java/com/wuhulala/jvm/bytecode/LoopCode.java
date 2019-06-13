package com.wuhulala.jvm.bytecode;

/**
 * 功能说明: 循环体字节码<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/18<br>
 */
public class LoopCode {

    public static void main(String[] args) {
        for (int j = 0; j < 100; j++) {
            // do something
        }
    }

// 0 iconst_0  把0放入操作数栈中
// 1 istore_1 从操作数栈中弹出第一个值0，并保存在第一局部变量中，即i=0
// 2 iload_1 将i放入操作数栈
// 3 bipush 100 将100压入操作数栈中
// 5 if_icmpge 14 (+9) 如果i>=100，跳出
// 8 iinc 1 by 1 将i加上1
// 11 goto 2 (-9) 跳转到第二步，继续
// 14 return

// goto 保证了条件满足于if_icmpge的条件时跳出循环
}
