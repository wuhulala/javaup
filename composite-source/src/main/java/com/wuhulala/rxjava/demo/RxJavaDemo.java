package com.wuhulala.rxjava.demo;

import io.reactivex.Observable;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/11/29<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class RxJavaDemo {

    public static void main(String[] args) {
        Observable.just("Hello world").subscribe(System.out::println);
    }
}
