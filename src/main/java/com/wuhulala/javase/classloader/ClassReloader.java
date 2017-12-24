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
public class ClassReloader extends ClassLoader {
    private String classPath;

    private String packageName = "com.wuhulala";

    public ClassReloader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if(classData == null){
            throw new ClassNotFoundException();
        }else{
            return defineClass(name, classData, 0, classData.length);
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
