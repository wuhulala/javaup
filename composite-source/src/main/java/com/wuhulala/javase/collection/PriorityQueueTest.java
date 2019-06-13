package com.wuhulala.javase.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;

/**
 * author： wuhulala
 * date： 2017/6/25
 * version: 1.0
 * description: 优先队列测试
 */

public class PriorityQueueTest {
    private static final Logger logger = LoggerFactory.getLogger(PriorityQueueTest.class);

    public static void main(String[] args) {

        PriorityQueue<String> nameQueue = new PriorityQueue<>(String::compareTo);

        nameQueue.comparator();

        nameQueue.add("a");
        nameQueue.add("d");
        nameQueue.add("c");
        nameQueue.add("f");
        nameQueue.add("g");
        nameQueue.add("b");

        //返回队首 不删除
        String s = nameQueue.peek();
        //返回队首 并删除
        nameQueue.poll();
        System.out.println(s);
        s = nameQueue.poll();
        System.out.println(s);
        nameQueue.forEach((item) -> {
            System.out.println(item);
        });
    }
}
