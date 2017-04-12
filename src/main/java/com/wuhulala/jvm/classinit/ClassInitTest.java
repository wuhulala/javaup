package com.wuhulala.jvm.classinit;

/**
 * 类初始化加载顺序
 * 1、父类static块->字段->子类static块->字段 在类加载到jvm时执行
 * 2、父类块->构造方法->子类块->子类构造方法 在类初始化时完成
 * 3、如果static遇到了类初始化 会先执行初始化
 * 4、如果是final 修饰的static 在类准备的时候便会赋值（类准备阶段是为变量分配内存和设置类变量初始化阶段，不包括属性）,而其他的是在类加载的最后一步完成的时候赋值
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/12
 * @link https://github.com/wuhulala
 */
public class ClassInitTest {
    C t = new C();//3 此步骤初始化是由于第二步的t2实例初始化的时候

    static {
        System.out.println("-----this.static init---");//1
    }

    static ClassInitTest t2 = new ClassInitTest();//2

    static String str = "ff";//4

    public ClassInitTest() {
        System.out.println("-----init class...");
    }

    public static void main(String[] ages) {

//        -----this.static init---
//                ----bbbbb  static-block...
//        the i is1
//        ddddd static-block...
//        D constructor...
//        ----cccccc  static-block...
//        D constructor...
//        B block---
//                B constructor ----
//        C block init
//        C constructor ----
//                -----init class...
    }
}

class D{
    private final   static int  i = 1; //在此处就可以

    static {
        System.out.println("the i is" + i);
        System.out.println("ddddd static-block...");

    }

    //private final   static int  i = 1;//在此位置  static 可以赋值给i 但是不可以读取i

    public D(){
        System.out.println("D constructor...");
    }
}
class C extends B {
    private static D d = new D(); //类初始化 但是现在类还没有加载进jvm 那么会先进行加载类 执行 static 方法
    static {
        System.out.println("----cccccc  static-block...");
    }
    private final static  D d1 = new D(); //在加载的时候会顺序执行 final 标示的是常量
    // 常量在编译阶段会存入调用它的类的常量池中，本质上没有直接引用到定义该常量的类，因此不会触发定义常量的类的初始化
    // 查看FinalTest类

    {
        System.out.println("C block init");
    }

    //构造方法
    public C() {
        System.out.println(" C constructor ----");
    }

}

class B {
    //静态块
    static {
        System.out.println("----bbbbb  static-block...");
    }

    //构造方法
    public B() {
        System.out.println(" B constructor ----");
    }

    //普通块
    {
        System.out.println("B block---");
    }
}

