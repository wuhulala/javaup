package com.wuhulala.proxy;

/**
 * Created by xueah20964 on 2017/6/5.
 */
public interface ProxyBean<T> {
    //默认执行完代理链后执行此方法
    T doSomething();
}
