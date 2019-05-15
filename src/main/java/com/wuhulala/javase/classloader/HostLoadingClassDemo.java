package com.wuhulala.javase.classloader;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类的热加载
 *
 * @author wuhulala<br>
 * @date 2019/5/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class HostLoadingClassDemo {

    // 0. 一个动态的计算器
    // 1. 检测类的变化，动态编译
    // 2. 动态加载,卸载类

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        ClassReloader r1 = new ClassReloader("file:D:\\study\\javaup\\hotload");
//        Class<?> c1 = r1.loadClass("com.wuhulala.javase.classloader.test.Calculator", true, "Calculator_v1.class");
//        Object instance1 = c1.newInstance();
//        int result = (int) c1.getMethod("add", int.class, int.class).invoke(instance1, 1, 2);
//        System.out.println(result);
//
//        ClassReloader r2 = new ClassReloader("file:D:\\study\\javaup\\hotload");
//        Class<?> c2 = r2.loadClass("com.wuhulala.javase.classloader.test.Calculator", true, "Calculator_v2.class");
//        Object instance2 = c2.newInstance();
//        int result2 = (int) c2.getMethod("add", int.class, int.class).invoke(instance2, 1, 2);
//        System.out.println(result2);

        startAdder();
    }

    public static void startAdder() {
        ThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1);
        ((ScheduledThreadPoolExecutor) poolExecutor).scheduleAtFixedRate(new ScheduledAdder(), 0, 3, TimeUnit.SECONDS);
    }

    public static class ScheduledAdder implements Runnable {

        private int batchNum = 1;
        private Random random = new Random();

        @Override
        public void run() {
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            try {

                System.err.println("batchNum" + batchNum++ + " : " + String.format("(%d + %d = %d)", a, b, add(a, b)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int add(int a, int b) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object instance2 = instance();
        return (int) instance2.getClass().getMethod("add", int.class, int.class).invoke(instance2, 1, 2);
    }

    public static Object instance() {
        try {
            ClassReloader r2 = new ClassReloader("file:D:\\study\\javaup\\target\\classes\\com\\wuhulala\\javase\\classloader\\test");
            Class<?> c2 = r2.loadClass("com.wuhulala.javase.classloader.test.Calculator", true, "Calculator.class");
            return c2.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }


}
