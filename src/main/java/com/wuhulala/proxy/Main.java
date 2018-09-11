package com.wuhulala.proxy;

/**
 * Created by xueah20964 on 2017/6/5.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println(System.getProperty("file.encoding"));

        SimpleProxy proxy = new SimpleProxy("proxy1");
        SimpleProxy proxy1 = new SimpleProxy("proxy2");

        Proxy[] proxies = new Proxy[2];
        proxies[0] = proxy;
        proxies[1] = proxy1;

        TargetBean bean = new TargetBean();
        ProxyChain<Integer> proxyChain = new SimpleProxyChain(proxies,bean);

        Integer result = proxyChain.doChain();
        System.out.println(result);
    }
}
