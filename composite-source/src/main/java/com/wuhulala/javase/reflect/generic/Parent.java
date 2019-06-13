package com.wuhulala.javase.reflect.generic;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/14
 */
public class Parent {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public<T> void testGenericMethod(T a) {

    }
}
