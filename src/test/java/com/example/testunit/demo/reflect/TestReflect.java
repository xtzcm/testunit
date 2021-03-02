package com.example.testunit.demo.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Despriction: 测试反射
 * @Author: wangcheng
 * @Date: 2018/10/21 18:36
 */
public class TestReflect {

    /**
     * 反射创建对象
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Class<?> clazz = Class.forName("com.example.testunit.demo.reflect.BeanForReflect");
        //方法1 通过原始类的无参构造函数来创建反射对象
        //BeanForReflect instance = (BeanForReflect)clazz.newInstance();

        //方法2 通过带参构造函数来创建反射对象
        //Constructor<?> constructor = clazz.getConstructor();//获取公共构造函数
        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, String.class);
        constructor.setAccessible(true);
        BeanForReflect instance = (BeanForReflect)constructor.newInstance("张三", "1");

        instance.setAge("1");
        System.out.println(instance.getAge());
    }

    /**
     * 反射获取方法
     */
    @Test
    public void test2() throws Exception {
        Class<?> clazz = Class.forName("com.example.testunit.demo.reflect.BeanForReflect");
        Method method = clazz.getMethod("getName", null);
        String result = (String) method.invoke(clazz.newInstance(), null);
        System.out.println(result);
    }

    /**
     * 反射获取字段
     */
    @Test
    public void test3() throws Exception {
        Class<?> clazz = Class.forName("com.example.testunit.demo.reflect.BeanForReflect");
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        BeanForReflect instance = (BeanForReflect)clazz.newInstance();
        field.set(instance, "张三");
        System.out.println(field.get(instance));
    }
}
