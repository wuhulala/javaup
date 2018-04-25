package com.wuhulala.thread;

import java.text.MessageFormat;

/**
 * 功能说明: com.wuhulala<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/17<br>
 */
public class ParentThread implements Runnable{

    private String name;

    public ParentThread(String name) {
        this.name = "parent:::" + name;
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
