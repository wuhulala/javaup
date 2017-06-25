package com.wuhulala.javase.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * author： wuhulala
 * date： 2017/6/25
 * version: 1.0
 * description: 双端队列测试
 */

public class DequeTest {
    private static final Logger logger = LoggerFactory.getLogger(DequeTest.class);

    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();

        deque.add("aaa");
        deque.add("bbb");
        deque.add("ccc");

        //加入队首
        deque.addFirst("first");
        System.out.println(deque.peek());

        //加入队列尾部
        deque.addLast("end");
        System.out.println(deque.peekLast());

        //带返回参数的添加
        if(deque.offerFirst("asdasd")){
            System.out.println("添加成功" + deque.peek());
        }
    }
}
