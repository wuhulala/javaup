package com.wuhulala.javase.generic;

/**
 * pair类，可以通过查看字节码看到
 *   <pre>
 *         private Ljava/lang/Object; first
 *
 *        private Ljava/lang/Object; second
 *   </pre>
 *   可以看到真正的在java虚拟机中表现形式是Object，那么如果我们 T extends  Comparable
 *   <pre>
 *         private Ljava/lang/Comparable; first
 *
 *         private Ljava/lang/Comparable; se
 *         cond
 *   </pre>
 *   以上是因为泛型擦除的原因导致的。
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/11
 */
public class Pair<T> {

    private T first;
    private T second;

    public Pair() {
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
