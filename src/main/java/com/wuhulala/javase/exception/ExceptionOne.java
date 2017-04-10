package com.wuhulala.javase.exception;

/**
 * @author xueah20964
 * @date 2017/4/10
 */
class Exp2 {
    public int f(int a, int b) {
        int i = 1;
        try {
            i = a / b;
        } catch (Exception e) {
            System.out.printf("Exception occurs!!\n");
            System.out.println(e.getMessage());  //print the root cause
            System.out.printf("===========================\n");
            e.printStackTrace(); //print the info of function stuck.
        }finally {
            i = 3;
        }

        return i;
    }
}

public class ExceptionOne {

    public static void main(String[] args) {
        Exp2 ex = new Exp2();
        int i = ex.f(8, 0); //call f()
        System.out.printf("i is %d\n", i);  //successfully executed

        //try catch处理后并不会终止程序, 令程序即使出现了错误,  也可以对错误进行一定的处理后继续执行. 这就是java异常机制比c语言安全的地方.

//        Exception occurs!!
//                / by zero
//                ===========================
//        java.lang.ArithmeticException: / by zero
//        at com.wuhulala.javase.exception.Exp2.f(ExceptionOne.java:11)
//        at com.wuhulala.javase.exception.ExceptionOne.main(ExceptionOne.java:27)
//        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
//        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//        at java.lang.reflect.Method.invoke(Method.java:498)
//        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
//        i is 1
    }
}

