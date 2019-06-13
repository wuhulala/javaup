package com.wuhulala.javase.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 *   park()：<br/>
 *       如果许可可用，则使用该许可，并且该调用立即返回；<br/>
 *       否则，为线程调度禁用当前线程，并在发生以下三种情况之一以前，使其处于休眠状态：<br/>
 *       (1) 其它某个线程将当前线程作为目标调用 unpark<br/>
 *       (2) 其它某个线程中断当前线程<br/>
 *       (3) 该调用不合逻辑地（即毫无理由地）返回。<br/>
 *    unpark():  如果给定线程的许可尚不可用，则使其可用。<br/>
 *          如果线程在 park 上受阻塞，则它将解除其阻塞状态。<br/>
 *          否则，保证下一次调用 park 不会受阻塞。<br/>
 *          如果给定线程尚未启动，则无法保证此操作有任何效果。<br/>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/6/16<br>
 */
public class LockSuportTest {


    ///////////////////////////// 方法区 ////////////////////////////////////



    public static void main(String[] args) {
        //testUnParkNewStartedThread();
        testUnParkNotStatedThread();
    }

    /**
     * 先释放许可，这样线程第一次获取许可的时候可以直接获取到许可，即直接运行
     */
    public static void testUnParkStarted() {
        // 获取正在运行的线程
        Thread thread = Thread.currentThread();

        // 手动释放许可，不然需要由其它线程释放许可
        LockSupport.unpark(thread);

        System.out.println("a");
        // 获取许可
        LockSupport.park();
        System.out.println("b");
        // park 不可重入
        LockSupport.park();
        System.out.println("c");
    }

    /**
     * 测试释放新启动的线程的许可
     */
    public static void testUnParkNewStartedThread(){
        Thread thread = new Thread(()->{

            // 等待许可
            LockSupport.park();

            while (true){
                System.out.println("system date is " + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        System.out.println("我释放许可 你才可以用， 所以也可以在多线程的时候作为一种顺序控制的手段");

        LockSupport.unpark(thread);

    }

    public static void testUnParkNotStatedThread(){
        Thread thread = new Thread(()->{

            // 等待许可
            LockSupport.park();

            while (true){
                System.out.println("system date is " + System.currentTimeMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        System.out.println("你没开始，我就释放许可，试试你可不可以开始");

        LockSupport.unpark(thread);

        // 果然开始不了。。。。
        // 因为unpark方法的解释`This operation is not guaranteed to have any effect at all if the given thread has not been started.`
        // 即不保证在未开始的线程上有任何的影响
        thread.start();

    }
}
