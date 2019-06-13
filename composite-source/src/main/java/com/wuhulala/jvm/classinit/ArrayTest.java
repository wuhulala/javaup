package com.wuhulala.jvm.classinit;

/**但这段代码里触发了另一个名为“LLConst”的类的初始化，
 * 它是一个由虚拟机自动生成的、直接继承于java.lang.Object 的子类，
 * 创建动作由字节码指令 newarray 触发，
 * 很明显，这是一个对数组引用类型的初初始化，
 * 而该数组中的元素仅仅包含一个对 Const 类的引用，并没有对其进行初始化。
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/12
 * @link https://github.com/wuhulala
 */
class Const2 {
    static{
        System.out.println("初始化Const类");
    }
}

public class ArrayTest{
    public static void main(String[] args){
        Const2[] con = new Const2[5];
        //没有初始化
        for(Const2 a:con)
            a = new Const2();
        //初始化了
    }
}