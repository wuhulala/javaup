package com.wuhulala.javase.generic;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/11
 */
public class PairTest {
    public static void main(String[] args) {
        Employee e1 = new Employee("a", 2);
        Employee e2 = new Employee("b", 3);
        Pair<Employee> buddies = new Pair<>(e1, e2);

        // 以下语句 经过反编译之后可以发现变为了以下语句，并且有了强制类型的转换
        // System.out.println(((Employee)buddies.getFirst()).getName());

        System.out.println(buddies.getFirst().getName());

    }
}
