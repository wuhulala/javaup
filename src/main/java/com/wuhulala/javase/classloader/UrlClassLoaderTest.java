package com.wuhulala.javase.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/21
 */
public class UrlClassLoaderTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String jarFilePath = "file:F:\\code\\javase\\java_up\\jar\\commons-beanutils-1.8.3.jar";
        URL[] urls = new URL[1];
        urls[0] = new URL(jarFilePath);
        URLClassLoader classLoader = URLClassLoader.newInstance(urls);
        Class<?> fastHashMapClazz = classLoader.loadClass("org.apache.commons.collections.FastHashMap");
        Object fastHashMap = fastHashMapClazz.newInstance();
        System.out.println(fastHashMap);
    }
}
