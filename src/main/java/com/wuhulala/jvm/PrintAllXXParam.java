package com.wuhulala.jvm;

/**
 * 功能说明: com.wuhulala.jvm<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/4/25<br>
 */
public class PrintAllXXParam {
    // -XX:+PrintFlagsFinal and -XX:+PrintFlagsInitial

    //-XX:+PrintCommandLineFlags
    //这个参数让JVM打印出那些已经被用户或者JVM设置过的详细的XX参数的名称和值。
    public static void main(String[] args) {
        //表格的每一行包括五列，来表示一个XX参数。
        // 第一列表示参数的数据类型，
        // 第二列是名称，
        // 第四列为值，
        // 第五列是参数的类别。
        // 第三列”=”表示第四列是参数的默认值，而”:=” 表明了参数被用户或者JVM赋值了。
    }
}
