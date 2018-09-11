package com.wuhulala.proxy;

/**
 * Created by xueah20964 on 2017/6/5.
 */
public class SimpleProxyChain  implements ProxyChain<Integer>{
    private ProxyBean<Integer> bean;
    private int currentProxyIndex;
    private Proxy[] proxies;
    private int proxyNumber;
    //private com.wuhulala.proxy.ProxyChain<Integer> proxyChain;

    public SimpleProxyChain(Proxy[] proxies,ProxyBean<Integer> bean) {
        this.proxies = proxies;
        //this.proxyChain = this;
        this.currentProxyIndex = 0;
        this.proxyNumber = proxies.length;
        this.bean = bean;
    }

    @Override
    public Integer doChain() {
        if(currentProxyIndex == proxyNumber){
            System.out.println("出口到了。。。");
            return bean.doSomething();
        }else{
            Proxy<Integer> proxy = proxies[currentProxyIndex++];
            return proxy.doProxy(this);
        }
    }
}
