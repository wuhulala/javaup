package com.wuhulala.javase.guava.chap1_basic;

import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.List;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/12/16<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class OptionalTest {

    /**
     * Optional.of(T)	创建指定引用的Optional实例，若引用为null则快速失败
     * Optional.absent()	创建引用缺失的Optional实例
     * Optional.fromNullable(T)	创建指定引用的Optional实例，若引用为null则表示缺失
     * @param args
     */
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();

        List<String> list = null;
        //Optional.absent();

        if (Optional.fromNullable(list).isPresent()) {
            System.out.println("list is not null");
        } else {
            System.out.println("list is null");
        }
        List<String> list2 = new ArrayList<>();
        // 如果 list 为空 的话，那么向 list2 添加 hello world
        Optional.fromNullable(list).or(list2).add("hello world");
        System.out.println("list: " + list);
        System.out.println("list2: " + list2);
    }


}
