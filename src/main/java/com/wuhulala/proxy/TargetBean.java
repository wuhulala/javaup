package com.wuhulala.proxy;

/**
 * Created by xueah20964 on 2017/6/5.
 */
public class TargetBean implements ProxyBean<Integer>{
    @Override
    public Integer doSomething() {
        return 1;
    }
}
