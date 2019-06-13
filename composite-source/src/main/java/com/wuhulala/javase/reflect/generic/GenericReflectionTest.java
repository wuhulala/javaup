package com.wuhulala.javase.reflect.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作甚的
 *
 * @author wuhulala
 * @version 1.0
 * @since 2017/12/14
 */
public class GenericReflectionTest {
    public static void main(String[] args) throws NoSuchMethodException {

        // 测试获取父类有泛型的泛型类型
        //testParentGeneric();

        // 测试方法的泛型泛型类型获取
        Parent.class.getDeclaredMethods();
        Method method = Parent.class.getDeclaredMethod("testGenericMethod", Object.class);
        method.getTypeParameters();

    }

    private static void testParentGeneric() {
        Type parentType = ParentTemplate.class.getGenericSuperclass();

        System.out.println(parentType.getTypeName());

        Type[] types = ((ParameterizedType) parentType).getActualTypeArguments();
        for (Type type : types) {
            System.out.println(type.getTypeName());
        }
    }




}
