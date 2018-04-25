package com.wuhulala.spi;

import java.util.ServiceLoader;

/**
 * 功能说明: com.wuhulala.spi<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/12/15<br>
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<HelloInterface> loaders =
                ServiceLoader.load(HelloInterface.class);

        for (HelloInterface in : loaders) {
            in.sayHello();
        }
    }
}
