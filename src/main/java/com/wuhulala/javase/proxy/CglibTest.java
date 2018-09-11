package com.wuhulala.javase.proxy;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/13
 * @link https://github.com/wuhulala
 */
class SampleClass {
    public String test(String input) {
        return "Hello world!";
    }
}
public class CglibTest {
    public static void main(String[] args) throws Exception {
        //生成类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib");
       /* while(true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(SampleClass.class);
            enhancer.setCallback(new MethodInterceptor(){
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    //System.out.println("before");
                    Object result = methodProxy.invokeSuper(o, objects);
                    //System.out.println(result);
                    //System.out.println("after");
                    return result;
                }


            });
            SampleClass proxy = (SampleClass) enhancer.create();
            proxy.test(null);
        }*/

       //5M 836个类
        //-XX:MetaspaceSize=5M -XX:MaxMetaspaceSize=7M jdk1.8
        //-XX:PermSize=5M -XX:MaxPermSize=7M  jdk1.7
        //20M 3034 在加上原装的类 有3111个
        System.out.println("Let us do it now.....");
        //for(int i=0;i<100000;i++){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(SampleClass.class);
            //enhancer.setUseCache(false);// 关闭CGLib缓存，否则总是生成同一个类

            enhancer.setCallback(new MethodInterceptor(){
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    System.out.println("before");
                    Object result = methodProxy.invokeSuper(o, objects);
                    System.out.println(result);
                    System.out.println("after");
                    return result;
                }


            });


            SampleClass clazz = (SampleClass) enhancer.create();

            clazz.test("asd");

            //System.out.println("Time:" + System.currentTimeMillis()+"--------------------------第"+i+"个类");
        //}
    }


//    反编译Test
//    public final String test(String paramString)
//    {
//        MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
//        if (tmp4_1 == null)
//        {
//            tmp4_1;
//            CGLIB$BIND_CALLBACKS(this);
//        }
//        MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
//        if (tmp17_14 != null)
//            return (String)tmp17_14.intercept(this, CGLIB$test$0$Method, new Object[] { paramString }, CGLIB$test$0$com.wuhulala.proxy.Proxy);
//        return super.test(paramString);
//    }

    @Test
    public void testBeanGenerator() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "Hello cglib!");
        Method getter = myBean.getClass().getMethod("getValue");
        assertEquals("Hello cglib!", getter.invoke(myBean));
    }
}
