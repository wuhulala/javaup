package com.wuhulala.javase.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class ListCampare {
    public static void main(String[] args) {
        Random random = new Random();

        List arrayList = new ArrayList();
        List linkedList = new LinkedList();
        for (int i = 0; i < 10000000; i++) {
            arrayList.add(1);
        }
        for (int i = 0; i < 10000000; i++) {
            linkedList.add(1);

        }
        System.out.println("初始化ArrayList与LinkList成功");
        long begin1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            if(random.nextBoolean()) {
                arrayList.set(random.nextInt(10000000), 10);
            }else {
                arrayList.get(random.nextInt(10000000));
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("ArrayList: " + (end1 - begin1) + " ms");
        long begin2 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            if(random.nextBoolean()) {
                linkedList.set(random.nextInt(10000000), 10);
            }else {
                linkedList.get(random.nextInt(10000000));
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("LinkedList: " + (end2 - begin2) + " ms");
    }

}
