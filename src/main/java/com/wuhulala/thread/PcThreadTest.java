package com.wuhulala.thread;

/**
 * 功能说明: 子线程不退出，父线程也会返回。<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/17<br>
 */
public class PcThreadTest {

    public static void main(String[] args) {
        Thread parent = new Thread(new ParentThread("1"));
        //Thread child = new Thread(new ChildThread("1"));
        parent.start();

        String result = createChildThread();
        System.out.println(result);

        ThreadUtils.printCurThreadInfo();
    }

    private static String createChildThread() {
        System.out.println("create starting");
        new Thread(new ChildThread("1")).start();
        System.out.println("create ending");
        return "success";
    }

}
