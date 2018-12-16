package com.wuhulala.javase.guava.chap1_basic;

import com.google.common.collect.ComparisonChain;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/12/16<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class ObjectsTest {

    class Person implements Comparable<Person> {
        private String lastName;
        private String firstName;
        private int zipCode;

        @Override
        public int compareTo(Person other) {
            int cmp = lastName.compareTo(other.lastName);
            if (cmp != 0) {
                return cmp;
            }
            cmp = firstName.compareTo(other.firstName);
            if (cmp != 0) {
                return cmp;
            }
            return Integer.compare(zipCode, other.zipCode);
        }
    }

    /**
     * 具有fluent风格的Comparable
     */
    class Person1 implements Comparable<Person1> {
        private String lastName;
        private String firstName;
        private int zipCode;

        @Override
        public int compareTo(Person1 other) {
            return ComparisonChain.start()
                    .compare(this.firstName, other.firstName)
                    .compare(this.lastName, other.lastName)
                    .compare(this.zipCode, other.zipCode)
                    .result();
        }
    }

    @org.junit.Test
    public void test(){

    }


}
