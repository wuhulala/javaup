package com.wuhulala.javase.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/24
 */
public class NetClassLoader extends ClassLoader {

    private String classPath;
    private String packageName = "com.wuhulala";

    public NetClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 查找class 可以在这个方法之中修改逻辑，从而优先加载自己的类，
     * 当然java.lang.String包里面并不可以，因为它在jvm启动的时候已经加载过了
     *
     * @param name 类名
     *
     * @return class or null
     *
     * @throws ClassNotFoundException 类未寻找到异常
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if (aClass != null) {
            return aClass;
        }

        if (name.startsWith(packageName)) {
            byte[] classData = getData(name);
            if (classData == null) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classData, 0, classData.length);
            }
        }else{
            return super.loadClass(name);
        }
    }

    private byte[] getData(String name) {
        String path = classPath + File.separator + name.replace(".", File.separator)+ ".class";
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            URL url = new URL(path);
            InputStream is = url.openStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while((num = is.read(buffer)) != -1){
                result.write(buffer, 0 , num);
            }
            return result.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
