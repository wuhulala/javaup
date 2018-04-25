package com.wuhulala.jvm.bytecode;

/**
 * 功能说明: 算术指令<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/20<br>
 */
public class CalcCode {

    /**
     * <p>
     *  0 iload_1
     *  1 iload_2
     *  2 iadd
     *  3 ireturn
     * </p>
     * @param x
     * @param y
     * @return
     */
    public int add(int x, int y){
        return x + y;
    }

    /**
     * invokevirtual &#x7528;&#x4e8e;&#x8c03;&#x7528;&#x5bf9;&#x8c61;&#x7684;&#x5b9e;&#x4f8b;&#x65b9;&#x6cd5;
     * invokestatic &#x8c03;&#x7528;&#x7c7b;&#x4e2d;&#x7684;static&#x65b9;&#x6cd5;
     *
     * <p>
     *  0 aload_1
     *  1 invokevirtual #2 <java/lang/Integer.intValue>
     *  4 aload_2
     *  5 invokevirtual #2 <java/lang/Integer.intValue>
     *  8 iadd
     *  9 invokestatic #3 <java/lang/Integer.valueOf>
     *  12 areturn
     * </p>
     * @param x
     * @param y
     * @return
     */
    public Integer addR(Integer x, Integer y){
        return x + y;
    }


    /**
     * <p>
     *    0 iload_1 将局部变量_1放入操作数栈中
     *    1 iload_2 将局部变量_2放入操作数栈中
     *    2 if_icmpge 9 (+7)  比较栈顶两int型数值大小,当x>=y跳转到9
     *    5 iload_1 将局部变量_1放入操作数栈中
     *    6 goto 10 (+4) 跳转到10返回
     *    9 iload_2 将局部变量_2放入操作数栈中
     *    10 ireturn
     * </p>
     * @param x
     * @param y
     * @return
     */
    public int min(int x, int y){
        return x < y ? x : y;
    }

    /**
     * <p>
     *    0 iload_1
     *    1 iload_2
     *    2 if_icmple 9 (+7)  比较栈顶两int型数值大小,当x<=y跳转到9
     *    5 iload_1
     *    6 goto 10 (+4)
     *    9 iload_2
     *    10 ireturn
     * </p>
     * @param x
     * @param y
     * @return
     */
    public int max(int x, int y){
        return x > y ? x : y;
    }

    /**
     * <p>
     *    0 iload_1
     *    1 iload_2
     *    2 isub  将栈顶的两个int值，用第一个值减第二个值
     *    3 ireturn 返回int类型的值
     * </p>
     * @param x
     * @param y
     * @return
     */
    public int sub(int x, int y){
        return x - y;
    }

    /**
     * <p>
     *    0 iload_1
     *    1 iload_2
     *    2 idiv
     *    3 ireturn
     * </p>
     * @param x
     * @param y
     * @return
     */
    public int div(int x, int y){
        return x / y;
    }

    /**
     * <p>
     *   0 iload_1
     *   1 iload_2
     *   2 imul
     *   3 ireturn
     * </p>
     * @param x
     * @param y
     * @return
     */
    public int mul(int x, int y){
        return x * y;
    }

    /**
     * <p>
     *    0 getstatic #4 <java/lang/System.out>
     *    3 ldc #5 <void method>
     *    5 invokevirtual #6 <java/io/PrintStream.println>
     *    8 return 无返回值的
     * </p>
     */
    public void aVoid(){
        System.out.println("void method");
    }

    /**
     * <p>
     *    0 getstatic #4 <java/lang/System.out>
     *    3 ldc #7 <static void method>
     *    5 invokevirtual #6 <java/io/PrintStream.println>
     *    8 return
     * </p>
     *
     * static方法局部变量表中没有this的指向，而普通方法有，这也能解释为什么可以直接普通方法中直接调用
     */
    public static void sV(){
        System.out.println("static void method");
    }
}
