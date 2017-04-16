package com.wuhulala.javase.thread;

/**
 * 查看synchronize的字节码 //monitorenter - monitorexit
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/16
 * @link https://github.com/wuhulala
 */
public class SyncByteTest {

    public static void main(String[] args) throws InterruptedException {
        synchronized ("a") {
            System.out.println("asd");
        }
    }

//    0 ldc #2 <a>
//    2 dup
//    3 astore_1
//    4 monitorenter  //【-----进入 这一段这里原子性的----】
//    5 getstatic #3 <java/lang/System.out>
//    8 ldc #4 <asd>
//    10 invokevirtual #5 <java/io/PrintStream.println>
//    13 aload_1
//    14 monitorexit  //【-----退出----】
//    15 goto 23 (+8)
//    18 astore_2
//    19 aload_1
//    20 monitorexit
//    21 aload_2
//    22 athrow
//    23 return

}
