package com.wuhulala.javase.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * author： wuhulala
 * date： 2017/6/25
 * version: 1.0
 * description: 树集 ： 有顺序的集合
 */

public class TreeSetTest {
    private static final Logger logger = LoggerFactory.getLogger(TreeSetTest.class);

    public static void main(String[] args) {

        // HashSet的底层是HashMap
        Set<String> hashSet = new HashSet<>();

        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("3");
        hashSet.add("22");
        hashSet.add("23");

        printSet(hashSet);


        //无序的
//        21:11:46,509 DEBUG com.wuhulala.javase.collection.TreeSetTest (47) - ======= print start ==========
//        22
//        1
//        23
//        2
//        3
//        21:11:46,513 DEBUG com.wuhulala.javase.collection.TreeSetTest (53) - ======= print end ==========

        //TreeSet的底层是TreeMap（底层是红黑树）
        Set<String> treeSet = new TreeSet<>(String::compareTo);

        treeSet.add("1");
        treeSet.add("2");
        treeSet.add("3");
        treeSet.add("22");
        treeSet.add("23");

        printSet(treeSet);
// 字典序
//        21:11:46,592 DEBUG com.wuhulala.javase.collection.TreeSetTest (47) - ======= print start ==========
//        1
//        2
//        22
//        23
//        3
//        21:11:46,592 DEBUG com.wuhulala.javase.collection.TreeSetTest (53) - ======= print end ==========
    }

    private static void printSet(Set set) {

        logger.debug("======= print start ==========");
        Iterator it = set.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
        logger.debug("======= print end ==========");

    }
}
