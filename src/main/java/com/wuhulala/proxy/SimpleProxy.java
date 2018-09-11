package com.wuhulala.proxy;

/**
 * Created by xueah20964 on 2017/6/5.
 */
public class SimpleProxy implements Proxy<Integer> {
    private String name;

    public SimpleProxy(String name) {
        this.name = name;
    }

    @Override
    public Integer doProxy(ProxyChain<Integer> chain) {
        System.out.println("i am proxy " + this.name + " start");
        Integer result = chain.doChain();
        System.out.println("i am proxy " + this.name + " end");
        return result;
    }
}
