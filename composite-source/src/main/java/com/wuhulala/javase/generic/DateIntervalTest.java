package com.wuhulala.javase.generic;

import java.time.LocalDate;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/11
 */
public class DateIntervalTest {
    public static void main(String[] args) {

        LocalDate now = LocalDate.now();
        DateInterval interval = new DateInterval();
        interval.setFirst(now);
    }
}
