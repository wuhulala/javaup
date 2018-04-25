package com.wuhulala.thread;

import java.text.MessageFormat;

/**
 * 功能说明: com.wuhulala.thread<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/17<br>
 */
public class ChildThread implements Runnable{

    private String name;

    public ChildThread(String name) {
        this.name = "child:::" + name;
    }

    @Override
    public void run() {

        ThreadUtils.printCurThreadInfo();

        try {

            Thread.sleep(10000);

        } catch (InterruptedException e) {

            //e.printStackTrace();
        }

    }
}
