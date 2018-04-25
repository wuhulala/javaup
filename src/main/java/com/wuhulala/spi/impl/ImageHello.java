package com.wuhulala.spi.impl;

import com.wuhulala.spi.HelloInterface;

/**
 * 功能说明: com.wuhulala.spi.impl<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/12/15<br>
 */
public class ImageHello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("image hello!!!");
    }
}
