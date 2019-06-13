package com.wuhulala.javase.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 32位jvm 会内存溢出
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/26
 * @link https://github.com/wuhulala
 */
public class ListCompare {

    public static final int N = 10000000;

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            arrayList.add(1);
        }
        for (int i = 0; i < N; i++) {
            linkedList.add(1);

        }
        System.out.println("初始化ArrayList与LinkList成功");
        long begin1 = System.currentTimeMillis();

        randomGetAndSet(random, arrayList);

        long end1 = System.currentTimeMillis();
        System.out.println("ArrayList: " + (end1 - begin1) + " ms");
        long begin2 = System.currentTimeMillis();

        randomGetAndSet(random, linkedList);

        long end2 = System.currentTimeMillis();
        System.out.println("LinkedList: " + (end2 - begin2) + " ms");
    }

    private static void randomGetAndSet(Random random, List<Integer> list) {
        for (int i = 0; i < N; i++) {
            if (i % 10000 == 0) {
                System.out.println("--------------我还活着-----------------");
            }
            if (random.nextBoolean()) {
                list.set(random.nextInt(N), 10);
            } else {
                list.get(random.nextInt(N));
            }
        }
    }

}
