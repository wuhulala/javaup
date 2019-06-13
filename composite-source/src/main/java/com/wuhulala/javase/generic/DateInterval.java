package com.wuhulala.javase.generic;

import java.time.LocalDate;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/11
 */
public class DateInterval extends Pair<LocalDate> {

    @Override
    public void setFirst(LocalDate first) {
        super.setFirst(first);
        System.out.println("date interval set it");
    }
//    编译器会报错 both methods have same erasure, yet neither overrides the other
//     两种方法都有相同的擦除，但是都不会覆盖另一种方法
//    public void setFirst(LocalDate first) {
//        super.setFirst(first);
//        System.out.println("date interval set it");
//    }
}
