package com.wuhulala.jvm.bytecode;

/**
 * 功能说明: com.wuhulala.jvm.bytecode<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/2/21<br>
 */
public class ControlStructCode {

    /**
     * <p>
     * 0 iconst_0
     * 1 istore_1
     * 2 iload_1
     * 3 bipush 100
     * 5 if_icmpge 14 (+9)
     * 8 iinc 1 by 1
     * 11 goto 2 (-9)
     * 14 return
     * </p>
     */
    void whileInt() {
        int i = 0;
        while (i < 100) {
            i++;
        }
    }

    /**
     * <p>
     * 0 dconst_0
     * 1 dstore_1
     * 2 dload_1
     * 3 ldc2_w #2 <100.1>
     * 6 dcmpg
     * 7 ifge 17 (+10)
     * 10 dload_1
     * 11 dconst_1
     * 12 dadd
     * 13 dstore_1
     * 14 goto 2 (-12)
     * 17 return
     * </p>
     */
    void whileDoule() {
        double i = 0.0;
        while (i < 100.1) {
            i++;
        }
    }

    /**
     * <p>
     * 0 dload_1
     * 1 ldc2_w #4 <100.0>
     * 4 dcmpg
     * 5 ifge 10 (+5)
     * 8 iconst_1
     * 9 ireturn
     * 10 iconst_m1
     * 11 ireturn
     * </p>
     *
     * @param d
     * @return
     */
    int lessThan100(double d) {
        if (d < 100.0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * <p>
     * 0 dload_1
     * 1 ldc2_w #4 <100.0>
     * 4 dcmpl   ????
     * 5 ifle 10 (+5)
     * 8 iconst_1
     * 9 ireturn
     * 10 iconst_m1
     * 11 ireturn
     * </p>
     *
     * @param d
     * @return
     */
    int greaterThan100(double d) {
        if (d > 100.0) {
            return 1;
        } else {
            return -1;
        }
    }

}
