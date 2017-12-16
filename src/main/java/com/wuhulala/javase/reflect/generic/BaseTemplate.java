package com.wuhulala.javase.reflect.generic;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/14
 */
public class BaseTemplate<T> {
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

}
