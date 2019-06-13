package com.wuhulala.javase.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.PerfCounter;
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

    private static final Logger logger = LoggerFactory.getLogger(ClassReloader.class);

    private String classPath;

    private String packageName = "com.wuhulala";

    public ClassReloader(String classPath) {
        this.classPath = classPath;
    }

    public Class<?> loadClass(String name, boolean resolve, String path) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                // 自定一查找
                c = this.findClass(name, path);

                // this is the defining class loader; record the stats
                PerfCounter.getFindClasses().increment();

                // 如果自定义找不到，双亲委托去找
                if (c == null) {
                    if (logger.isDebugEnabled())
                        logger.debug("  Delegating to parent classloader at end: " + getParent());
                    try {
                        c = Class.forName(name, false, getParent());
                        if (c != null) {
                            if (logger.isDebugEnabled())
                                logger.debug("  Loading class from parent");
                            if (resolve)
                                resolveClass(c);
                            return (c);
                        }
                    } catch (ClassNotFoundException e) {
                        // Ignore
                        e.printStackTrace();
                    }
                }

            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name, null);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    protected Class<?> findClass(String name, String path) throws ClassNotFoundException {
        byte[] classData = getData(name, path);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String name, String path) {
        if (path == null || path.isEmpty()) {
            path = classPath + File.separator + name.replace(".", File.separator) + ".class";
        } else {
            path = classPath + File.separator + path;
        }

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
