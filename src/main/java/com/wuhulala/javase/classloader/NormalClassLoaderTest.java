package com.wuhulala.javase.classloader;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/21
 */
public class NormalClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));

        ClassLoader stringClassLoader = String.class.getClassLoader();
        printParentClassLoader(stringClassLoader);
    }

    public static void printParentClassLoader(ClassLoader cl){
        while (cl != null){
            System.out.println(cl.getClass().getCanonicalName());
            cl = cl.getParent();
        }
    }
}
