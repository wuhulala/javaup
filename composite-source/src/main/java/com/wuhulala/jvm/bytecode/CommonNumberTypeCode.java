package com.wuhulala.jvm.bytecode;

/**
 * 功能说明: com.wuhulala.jvm.bytecode<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/21<br>
 */
public class CommonNumberTypeCode {

    /**
     * <p>
     *    0 iconst_1 //1
     *    1 istore_1
     *    2 iconst_m1 //-1
     *    3 istore_2
     *    4 bipush 100 //100
     *    6 istore_3
     *    7 ldc #2 <1000000> //1000000
     *    9 istore 4
     *    11 lconst_1 //1
     *    12 lstore 5
     *    14 ldc2_w #3 <-1>  // 0xffffffff
     *    17 lstore 7
     *    19 ldc2_w #5 <2.2> // 2.2
     *    22 dstore 9
     *    24 return
     * </p>
     */
    public void useManyNumeric(){
        int x = 1;
        int y = -1;
        int i = 100;
        int j = 1000000;
        long l1 = 1L;
        long l2 = 0xffffffff;
        double d = 2.2;
    }
}
