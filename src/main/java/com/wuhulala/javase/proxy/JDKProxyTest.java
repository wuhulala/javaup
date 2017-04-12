package com.wuhulala.javase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理为什么只能代理接口呢
 * 1.首先java中单继承多实现
 * 2.由生成$Proxy0.class可以看出此类继承了Proxy类，所以只能代理接口
 * 接口怎么实现的动态代理呢
 * 1.会生成对应的方法 ，然后通过InvocationHandler接口（h就是InvocationHandler实例在我们Proxy.newnewProxyInstance传入的）调用代理方法
 *  <code>
 *      public final void sayHelloWorld() throws  {
 * try {
 * super.h.invoke(this, m3, (Object[])null);
 * } catch (RuntimeException | Error var2) {
 * throw var2;
 * } catch (Throwable var3) {
 * throw new UndeclaredThrowableException(var3);
 * }
 * }</code>
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/12
 * @link https://github.com/wuhulala
 */

interface A{
    void sayHelloWorld();
}

class B implements A{

    @Override
    public void sayHelloWorld() {
        System.out.println("sayHello...");
    }
}

/**
 * 代理类
 */
public class JDKProxyTest implements InvocationHandler{

    private Object origin;

    public Object instance(){
        return Proxy.newProxyInstance(origin.getClass().getClassLoader(),origin.getClass().getInterfaces(),this);
    }

    public JDKProxyTest(Object o){
        this.origin = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        return method.invoke(origin,args);
    }

    private void before() {
        System.out.println("before ...");
    }

    public static void main(String[] args) {
        //设置保留生成的动态代理类的Class文件 必须放在前面
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        A a = (A) new JDKProxyTest(new B()).instance();
        a.sayHelloWorld();


    }
}
