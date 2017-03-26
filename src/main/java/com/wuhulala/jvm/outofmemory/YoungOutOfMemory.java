package com.wuhulala.jvm.outofmemory;

import com.wuhulala.jvm.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class YoungOutOfMemory {
    //-Xms20M -Xmx20M -Xmn10M
    public static void main(String[] args) {
        List<Young> youngs = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i ++){
            youngs.add(new Young());
        }
    }
}

/**
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 at com.wuhulala.jvm.outofmemory.Young.<init>(YoungOutOfMemory.java:24)
 at com.wuhulala.jvm.outofmemory.YoungOutOfMemory.main(YoungOutOfMemory.java:18)
 */

class Young{
    private byte[] size = new byte[Constants._2MB];
}
