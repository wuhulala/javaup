package com.wuhulala.javase.generic;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/13
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee first = new Employee("first", 20);
        Employee second = new Employee("second", 22);
        Pair<Employee> buddies = new Pair<>(first, second);
        printBuddies(buddies);

        Manager manager1 = new Manager("firstManager", 20);
        Manager manager2 = new Manager("secondManager", 22);
        Pair<Manager> managerBuddies = new Pair<>(manager1, manager2);
        printBuddies( managerBuddies);
    }

    private static void printBuddies(Pair<? extends Employee> p){
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies");
    }
}
