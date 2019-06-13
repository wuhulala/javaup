package com.wuhulala.javase.guava.chap1_basic;

import static com.google.common.base.Preconditions.*;

/**
 * 0_0 o^o
 *
 *
 *  方法声明（不包括额外参数）	描述	检查失败时抛出的异常
 *  checkArgument(boolean)	检查boolean是否为true，用来检查传递给方法的参数。	IllegalArgumentException
 *  checkNotNull(T)	检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。	NullPointerException
 *  checkState(boolean)	用来检查对象的某些状态。	IllegalStateException
 *  checkElementIndex(int index, int size)	检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *	IndexOutOfBoundsException
 *  checkPositionIndex(int index, int size)	检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *	IndexOutOfBoundsException
 *  checkPositionIndexes(int start, int end, int size)	检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*	IndexOutOfBoundsException
 *
 * @author wuhulala<br>
 * @date 2018/12/16<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class PreconditionsTest {

    public static void main(String[] args) {
        int i = -1;
        int j = 0;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        checkArgument(i < j, "Expected i < j, but %s > %s", i, j);

    }


}
