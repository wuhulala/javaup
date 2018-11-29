package com.wuhulala.javase.longbase;

/**
 * Integer 源码阅读
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/3/19
 * @link https://github.com/wuhulala
 */
public class IntegerTest {
    public static void main(String[] args) {
        //-128-127被Integer缓存起来了


        Integer i = 1000;
        //i = new Integer(1);
        //System.out.println(i.shortValue());

        //格式化n进制数为整数
        System.out.println(Integer.parseInt("101010",2));
        System.setProperty("aaaa","123");
        //返回具有指定名称的系统属性的整数值
        System.out.println(Integer.getInteger("aaaa")); //123

        //>>>无符号右移 以0补最高位
        System.out.println(i>>>1); //500
        i = -1000;
        System.out.println(i>>>1); //2147483148
        //获取最大的小于i的二进制值
        /**
         * public static int highestOneBit(int i) {
         // HD, Figure 3-1
         i |= (i >>  1);
         i |= (i >>  2);
         i |= (i >>  4);
         i |= (i >>  8);
         i |= (i >> 16);
         return i - (i >>> 1);
         }
         */
        //如 1000 0000 0000 0000 0000 0000 0000 0000
        //1. 1100 0000 0000 0000 0000 0000 0000 0000
        //2. 1111 0000 0000 0000 0000 0000 0000 0000
        //3. 1111 1111 0000 0000 0000 0000 0000 0000
        //4. 1111 1111 1111 1111 0000 0000 0000 0000
        //5. 1111 1111 1111 1111 1111 1111 1111 1111
        //然后 减去 0111 1111 1111 1111 1111 1111 1111 1111
        //6 剩了1000 0000 0000 0000 0000 0000 0000 0000 所以得到了最大的小于i的二进制值
        //经过5次移位遍历所有的位置 真的是厉害
        int x = Integer.highestOneBit(10);

        // i & -i
        // 在计算机中都是已补码的形式在的
        // 比如 10
        // 10  : 0000 0000 0000 0000 0000 0000 0000 1010
        // -10 : 1111 1111 1111 1111 1111 1111 1111 0110
        // 可以得出为2
        // 利用的就是负数的补码是反码+1 二加完1之后 刚好最小的那位为1
        int y = Integer.lowestOneBit(10);
        // 最高位+最低位
        System.out.println(x+y);

        //获取二进制的前导0 负数是没有前导0的
        System.out.println(Integer.numberOfLeadingZeros(-1));
        //获取二进制的后导0 负数是没有前导0的
        System.out.println(Integer.numberOfTrailingZeros(-1));

        System.out.println(Integer.rotateLeft(10,2));
        //0x55555555 0101 0101 0101 0101 0101 0101 0101 0101
        //0x33333333 0011 0011 0011 0011 0011 0011 0011 0011
        //0x0f0f0f0f 0000 1111 0000 1111 0000 1111 0000 1111

        System.out.println(0x7fffffff);
    }
}
