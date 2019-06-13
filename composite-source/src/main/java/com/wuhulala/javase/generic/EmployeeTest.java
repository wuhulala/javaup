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

    public static void swap(Pair<?> p){
//        ERROR  用通配符是不可以获取这个的类型的，可以新建一个辅助方法，
//        如下，你瞅瞅，问号获取不到是不是就不行了？ 不是的 使用泛型就好了
//        ? t = p.getFirst();
//        p.setFirst(p.getSecond());
//        p.setSecond(t);
        swapHelper(p);
    }

    public static<T> void swapHelper(Pair<T> p){
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}
