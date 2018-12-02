package com.wuhulala.javase.classloader;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/24
 */
public class ClassDefinitionTest {
    /**
     * 测试自定义的ClassLoader
     */
    //@Test
    public void testNetClassLoader() throws ClassNotFoundException {
        String classPath = "file:D:\\classloader\\classes";
        String className = "com.wuhulala.future.FutureExample";
        NetClassLoader cl = new NetClassLoader(classPath);
        cl.loadClass(className);
    }

    /**
     * 测试类的热部署---不同的类加载器
     */
   // @Test
    public void testClassReloader() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classPath = "file:D:\\classloader\\classes";
        String className = "com.wuhulala.future.FutureExample";
        ClassReloader c1 = new ClassReloader(classPath);
        Class<?> clazz1 = c1.loadClass(className);
        System.out.println(clazz1.newInstance());
        ClassReloader c2 = new ClassReloader(classPath);
        Class<?> clazz2 = c2.loadClass(className);
        System.out.println(clazz2.newInstance());

    }

    /**
     * 测试类的热部署--相同的类加载器
     */
    //@Test(expected = LinkageError.class)
    public void testClassReloader2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classPath = "file:D:\\classloader\\classes";
        String className = "com.wuhulala.future.FutureExample";
        ClassReloader c1 = new ClassReloader(classPath);
        Class<?> clazz1 = c1.findClass(className);
        System.out.println(clazz1.newInstance());
        //ClassReloader c2 = new ClassReloader(classPath);
        // 在这里因为由同一个类被define了两次
        Class<?> clazz2 = c1.findClass(className);
        System.out.println(clazz2.newInstance());

    }

   // @Test
    public void testExtAndAppClassLoaderPath(){
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }


}
