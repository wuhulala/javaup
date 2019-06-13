package com.wuhulala.jvm.gc;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/19
 * @link https://github.com/wuhulala
 */
public class TestTenuringThreshold {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testTenuringThreshold2();
    }

    /**
     * VM -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution<br>
     * -XX:MaxTenuringThreshold 设置进入老年对象的最大值<br>
     * 如果设置为0的话,则年轻代对象不经过Survivor区,直接进入年老代. <br>
     * 对于年老代比较多的应用,可以提高效率.<br>
     * 如果将此值设置为一个较大值,则年轻代对象会在Survivor区进行多次复制,<br>
     * 这样可以增加对象再年轻代的存活时间,增加在年轻代即被回收的概率该参数只有在串行GC时才有效.<br>
     */
    public static void testTenuringThreshold1(){
        byte[] allocation1,allocation2,allocation3;

        allocation1 = new byte[_1MB / 4]; //
        allocation2 = new byte[_1MB * 4];
        allocation3 = new byte[_1MB * 4]; // 第一次GC allocation2由于比survivor区容量大 进入老年代
        allocation3 = null;
        allocation3 = new byte[_1MB * 4]; // 第二次GC allocation1 age = 1 进入老年代 survivor区为空
    }
/*
    Desired survivor size 524288 bytes, new threshold 1 (max 1)
            - age   1:     706288 bytes,     706288 total
: 5392K->689K(9216K), 0.0030760 secs] 5392K->4785K(19456K), 0.0031020 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
            [GC [DefNew
    Desired survivor size 524288 bytes, new threshold 1 (max 1)
            - age   1:        136 bytes,        136 total
: 4949K->0K(9216K), 0.0015451 secs] 9045K->4783K(19456K), 0.0015688 secs] [Times: user=0.02 sys=0.02, real=0.02 secs]
    Heap
    def new generation   total 9216K, used 4260K [0x331b0000, 0x33bb0000, 0x33bb0000)
    eden space 8192K,  52% used [0x331b0000, 0x335d8fd8, 0x339b0000)
    from space 1024K,   0% used [0x339b0000, 0x339b0088, 0x33ab0000)
    to   space 1024K,   0% used [0x33ab0000, 0x33ab0000, 0x33bb0000)
    tenured generation   total 10240K, used 4783K [0x33bb0000, 0x345b0000, 0x345b0000)
    the space 10240K,  46% used [0x33bb0000, 0x3405bf60, 0x3405c000, 0x345b0000)
    compacting perm gen  total 12288K, used 185K [0x345b0000, 0x351b0000, 0x385b0000)
    the space 12288K,   1% used [0x345b0000, 0x345de6f0, 0x345de800, 0x351b0000)
    ro space 10240K,  45% used [0x385b0000, 0x38a37290, 0x38a37400, 0x38fb0000)
    rw space 12288K,  54% used [0x38fb0000, 0x3963ace8, 0x3963ae00, 0x39bb0000)*/

   //在进行第二次GC的时候 allocation1 和 allocation2 会进入老年代内存。



    /**
     * 在Survivor区中。相同年龄所有对象的大小总和必须大于Survivor区的一半。年龄大于等于该年龄的对象进入老年代
     *
     * VM -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold2(){
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[_1MB / 4]; //
        allocation2 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB * 4];
        allocation4 = new byte[_1MB * 4]; // 第一次GC
        allocation4 = null;
        allocation4 = new byte[_1MB * 4]; // 第二次GC
    }

    /**
     * [GC [DefNew
     Desired survivor size 524288 bytes, new threshold 1 (max 15)
     - age   1:     706464 bytes,     706464 total
     : 5648K->689K(9216K), 0.0037302 secs] 5648K->4785K(19456K), 0.0037594 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [GC [DefNew
     Desired survivor size 524288 bytes, new threshold 15 (max 15)
     - age   1:        136 bytes,        136 total
     : 4949K->0K(9216K), 0.0011757 secs] 9045K->4784K(19456K), 0.0011990 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     Heap
     def new generation   total 9216K, used 4344K [0x331b0000, 0x33bb0000, 0x33bb0000)
     eden space 8192K,  53% used [0x331b0000, 0x335ee120, 0x339b0000)
     from space 1024K,   0% used [0x339b0000, 0x339b0088, 0x33ab0000)
     to   space 1024K,   0% used [0x33ab0000, 0x33ab0000, 0x33bb0000)
     tenured generation   total 10240K, used 4784K [0x33bb0000, 0x345b0000, 0x345b0000)
     the space 10240K,  46% used [0x33bb0000, 0x3405c010, 0x3405c200, 0x345b0000)
     compacting perm gen  total 12288K, used 185K [0x345b0000, 0x351b0000, 0x385b0000)
     the space 12288K,   1% used [0x345b0000, 0x345de7d0, 0x345de800, 0x351b0000)
     ro space 10240K,  45% used [0x385b0000, 0x38a37290, 0x38a37400, 0x38fb0000)
     rw space 12288K,  54% used [0x38fb0000, 0x3963ace8, 0x3963ae00, 0x39bb0000)
     */

    //以上from to区为空。说明allocation1和allocation2进入了老年代之中
}
