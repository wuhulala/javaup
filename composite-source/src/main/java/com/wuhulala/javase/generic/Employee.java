package com.wuhulala.javase.generic;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/11
 */
public class Employee {
    private String name;
    private int age;

    public Employee() {
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
