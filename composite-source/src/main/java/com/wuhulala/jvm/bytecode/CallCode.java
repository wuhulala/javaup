package com.wuhulala.jvm.bytecode;

/**
 * 功能说明: 方法相互调用<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/21<br>
 */
public class CallCode {
    /**
     * <p>
     *    0 aload_0
     *    1 iconst_1
     *    2 invokevirtual #2 <com/wuhulala/jvm/bytecode/CallCode.m2>
     *    5 return
     * </p>
     */
    public void m1(){
        m2(1);
    }

    /**
     * <p>
     *    0 iload_1
     *    1 invokestatic #3 <com/wuhulala/jvm/bytecode/CallCode.sm1>
     *    4 return
     * </p>
     */
    public void m2(int x){
        sm1(x);
    }

    /**
     * <p>
     *    0 getstatic #4 <java/lang/System.out>
     *    3 new #5 <java/lang/StringBuilder>
     *    6 dup
     *    7 invokespecial #6 <java/lang/StringBuilder.<init>>
     *    10 ldc #7 <sm1:::>
     *    12 invokevirtual #8 <java/lang/StringBuilder.append>
     *    15 iload_0  因为是static方法，所以局部变量表中没有this的指向，从0开始
     *    16 invokevirtual #9 <java/lang/StringBuilder.append>
     *    19 invokevirtual #10 <java/lang/StringBuilder.toString>
     *    22 invokevirtual #11 <java/io/PrintStream.println>
     *    25 return
     * </p>
     */
    public static void sm1(int x){
        System.out.println("sm1:::" + x);
    }
}
