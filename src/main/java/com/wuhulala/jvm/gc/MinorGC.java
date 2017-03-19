package com.wuhulala.jvm.gc;

/**
 * 新生代GC
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/19
 * @link https://github.com/wuhulala
 */
public class MinorGC {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB * 2];
        allocation2 = new byte[_1MB * 2];
        allocation3 = new byte[_1MB * 2];
        allocation4 = new byte[_1MB * 5]; //出现一次MinorGC

    }

    /**
     *  Heap java8
     *PSYoungGen      total 9216K, used 7789K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     *eden space 8192K, 95% used [0x00000000ff600000,0x00000000ffd9b7d8,0x00000000ffe00000)
     *from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     *to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     *ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     *object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
     *Metaspace       used 3215K, capacity 4494K, committed 4864K, reserved 1056768K
     *class space    used 355K, capacity 386K, committed 512K, reserved 1048576K
     */

    //从上面结果来看 java8 默认使用的新生代垃圾收集器是PSYoungGen(Parallel Scavenge) 它是并行收集器
    //注重于吞吐量的指标，吞吐量可以简单理解为 CPU执行用户程序的时间。
    //如果没有太多的操作 ，那么GC次数肯定也会很少，所以就不用过于关注GC的时候的停顿时间。
    //在这种应用场景之下，我们就可以关注一下 吞吐量了(吞吐量=运行用户代码时间/（运行用户代码时间+GC消耗的时间）)

    //在注释掉**allocation4 = new byte[_1MB * 4]; **此语句之后 我发现GC打印日志如下
    //Heap
    //PSYoungGen      total 9216K, used 7789K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
    //eden space 8192K, 95% used [0x00000000ff600000,0x00000000ffd9b7d8,0x00000000ffe00000)
    //from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
    //to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
    //ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
    //object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
    //Metaspace       used 3216K, capacity 4494K, committed 4864K, reserved 1056768K
    //class space    used 355K, capacity 386K, committed 512K, reserved 1048576K
    //可见老年代没有任何对象
    //在对allocation4分配内存之后
    //新生代内存没有发生变化 老年代的内存刚好4M内存 与allocation4的内存大小对应
    //那么老年代的估计就是allocation4了
    //但是为了解决这个疑惑
    //那么我为allocation4分配5M内存，查看日志
    // ParOldGen       total 10240K, used 5120K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
    //那就可以有了如下解释
    //PS收集器为了保证吞吐量 并且在新生代没有足够的内存的情况下 直接把新的大对象分配到了老年代



    /**
     * Heap java7
     *def new generation   total 9216K, used 4779K [0x331b0000, 0x33bb0000, 0x33bb0000)
     *eden space 8192K,  53% used [0x331b0000, 0x335ed7f0, 0x339b0000)
     *from space 1024K,  42% used [0x33ab0000, 0x33b1d660, 0x33bb0000)
     *to   space 1024K,   0% used [0x339b0000, 0x339b0000, 0x33ab0000)
     *tenured generation   total 10240K, used 6144K [0x33bb0000, 0x345b0000, 0x345b0000)
     *the space 10240K,  60% used [0x33bb0000, 0x341b0030, 0x341b0200, 0x345b0000)
     *compacting perm gen  total 12288K, used 203K [0x345b0000, 0x351b0000, 0x385b0000)
     *the space 12288K,   1% used [0x345b0000, 0x345e2fd8, 0x345e3000, 0x351b0000)
     *ro space 10240K,  45% used [0x385b0000, 0x38a37290, 0x38a37400, 0x38fb0000)
     *rw space 12288K,  54% used [0x38fb0000, 0x3963ace8, 0x3963ae00, 0x39bb0000)
     */
    // java7 默认使用的是Serial 收集器，它是最原始的收集器。
    // 由于survivor区没有足够的你内存存放，
    // 所以allocation1 allocation2 allocation3 直接进入老年代
    // 在老年代的是allocation1 、allocation2、 allocation3 内存占6M
    // 在新生代的时候是新的allocation4 内存占4M


}
