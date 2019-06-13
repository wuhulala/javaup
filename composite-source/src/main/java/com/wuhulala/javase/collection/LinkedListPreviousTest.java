package com.wuhulala.javase.collection;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/20
 */
public class LinkedListPreviousTest {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        linkedList.add("g");
        linkedList.add("h");

        ListIterator lit = linkedList.listIterator();
        // 光标先反向
        while (lit.hasNext()){
            System.out.println(lit.next());
        }

        // 光标再正向
        while (lit.hasPrevious()){
            System.out.println(lit.previous());
        }

    }
}
