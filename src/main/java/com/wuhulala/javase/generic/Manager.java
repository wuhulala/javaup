package com.wuhulala.javase.generic;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/13
 */
public class Manager extends Employee{

    public Manager(String name, int age) {
        super(name, age);
        this.type = "default";
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
