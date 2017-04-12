package com.wuhulala.jvm.classinit;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/12
 * @link https://github.com/wuhulala
 */
public class ClassInitTest2 {

}

class AA{
    private static CC cc1 = new CC("1");
    private final static BB bb = new BB();
    private static CC cc2 = new CC("2");

    public static void main(String[] args) {
//        cc constructor----1
//        bb static block
//        bb constructor
//        cc constructor----2
    }
}

class BB{
    static {
        System.out.println("bb static block");
    }
    public BB(){
        System.out.println("bb constructor");
    }
}

class CC{

    public CC(String name){
        System.out.println("cc constructor----"+name);
    }
}
