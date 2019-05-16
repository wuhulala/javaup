package com.wuhulala.javase.classloader;

import com.wuhulala.javase.classloader.test.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * .java 文件动态编译工具类
 *
 * @author wuhulala<br>
 * @date 2019/5/16<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class JavaCompileUtils {

    private static final Logger logger = LoggerFactory.getLogger(JavaCompileUtils.class);

    public static void compile(String destPath, String sourcePath) {
        try {
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null, null, null, "-d",destPath, sourcePath);
            if (status != 0) {
                logger.error(">>>>> {} compile failed！", sourcePath);
            }else {
                logger.debug(">>>>>> {} => {} compile success", sourcePath, destPath);
            }
        } catch (Exception e) {
            logger.error(">>>>> {} compile failed！", sourcePath, e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
       // Thread.sleep(19000);
        compile("D:\\study\\javaup\\target\\classes", "D:\\study\\javaup\\src\\main\\java\\com\\wuhulala\\javase\\classloader\\test\\Calculator.java");
        // 这个时候才会去加载类，但是
        Calculator c = new Calculator();
        System.out.println(c.add(1, 3));


        //Thread.sleep(19000);
        compile("D:\\study\\javaup\\target\\classes", "D:\\study\\javaup\\src\\main\\java\\com\\wuhulala\\javase\\classloader\\test\\Calculator.java");
        // 这个时候不会去加载类，所以不会发生变化，只能通过破坏双亲委派模型判断是否需要重新加载。
        Calculator c1 = new Calculator();
        System.out.println(c1.add(1, 3));
    }
}
