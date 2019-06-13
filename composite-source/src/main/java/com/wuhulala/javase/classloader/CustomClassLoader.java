package com.wuhulala.javase.classloader;


import org.apache.tools.ant.taskdefs.Classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.ProtectionDomain;

/**
 * 重写ClassLoader， findClass 和 loadClass
 *
 * <p>因为只有重写loadClass才可以破坏双亲委托模型，不然会直接会根绝双亲委托模型依次查询的。</p>
 *
 * @author xueah20964 2019/5/2 Create 1.0  <br>
 * @version 1.0
 */
public class CustomClassLoader extends ClassLoader {

    public static void main(String[] args)  {
        CustomClassLoader cl = new CustomClassLoader();
        try {
            cl.loadClass("java.lang.Void", true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findClass(name);
        if (resolve) {
            super.resolveClass(c);
        }
        return c;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }


    public byte[] getData(String name) {

        String path = "file:D:\\code\\javase\\java_up\\target\\classes" + File.separator + name.replace(".", File.separator) + ".class";
        System.out.println(path);
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            URL url = new URL(path);
            InputStream is = url.openStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                result.write(buffer, 0, num);
            }
            return result.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
