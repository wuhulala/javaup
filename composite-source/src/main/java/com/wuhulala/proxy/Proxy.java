package com.wuhulala.proxy;

/**
 * Created by xueah20964 on 2017/6/5.
 */
public interface Proxy<T> {
    T doProxy(ProxyChain<T> chain);
}
