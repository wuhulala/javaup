package com.wuhulala.jvm.outofmemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 堆外内存溢出 一般与nio有关
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class DirectMemoryOutOfMemory {
    public static void main(String[] args) {
        List<ByteBuffer> buffers = new ArrayList<>();
        while(true){
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 1024);
            buffers.add(buffer);
        }

    }
    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     at java.nio.HeapByteBuffer.<init>(HeapByteBuffer.java:57)
     at java.nio.ByteBuffer.allocate(ByteBuffer.java:331)
     at com.wuhulala.jvm.outofmemory.DirectMemoryOutOfMemory.main
     */
}
