package com.wuhulala.jvm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看Jconsole
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/14
 * @link https://github.com/wuhulala
 */
public class MonitoringTest {
    private static class OOMObject{
        private byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(100);
            list.add(new OOMObject());
            System.out.println("------------------------"+i+"-----------------------");
        }
        //System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
