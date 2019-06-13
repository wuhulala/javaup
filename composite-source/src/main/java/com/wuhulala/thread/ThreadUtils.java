package com.wuhulala.thread;

import java.text.MessageFormat;

/**
 * 功能说明: com.wuhulala.thread<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/17<br>
 */
public class ThreadUtils {

    public static void printCurThreadInfo(){
        synchronized (ThreadUtils.class){
            System.out.println("=================================");
            System.out.println(MessageFormat.format("====  [{0}]   ====", Thread.currentThread().getName()));
            System.out.println("=================================");
        }
    }

}
