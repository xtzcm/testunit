package com.example.testunit.demo.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Despriction: 基础：测试反射获取注解
 * @Author: wangcheng
 * @Date: 2018/6/3 11:30
 */
@MyClassAnnotation(name = "MyClassAnnotation", value = {})
public class TestAnnotation {

    @MyFieldAnnotation(name = "MyFieldAnnotation", value = {})
    private String myName;

    @MyConstructorAnnotation(name = "MyConstructorAnnotation", value = {})
    public TestAnnotation(String myName) {
        this.myName = myName;
    }

    public String getMyName() {
        return myName;
    }

    @MyMethodAnnotation(name = "MyMethodAnnotation", value = {"张三"})
    public void setMyName(String myName) {
        this.myName = myName;
    }

    /**
     * 反射读取注解
     * @param args
     */
    public static void main(String[] args) {
        Class<TestAnnotation> clazz = TestAnnotation.class;

        //读取类注解
        MyClassAnnotation classAnnotation = clazz.getAnnotation(MyClassAnnotation.class);
        System.out.println("classAnnotationName: " + classAnnotation.name().toString());
        String[] classAnnotationValue = classAnnotation.value();

        //读取构造方法注解
        try {
            Constructor<TestAnnotation> constructor = clazz.getConstructor(new Class[]{String.class});
            MyConstructorAnnotation constructorAnnotation = constructor.getAnnotation(MyConstructorAnnotation.class);
            System.out.println("constructorAnnotationName: " + constructorAnnotation.name().toString());
            String[] constructorAnnotationValue = constructorAnnotation.value();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //读取方法注解
        try {
            Method methodSetMyName = clazz.getMethod("setMyName", new Class[]{String.class});
            MyMethodAnnotation methodAnnotation = methodSetMyName.getAnnotation(MyMethodAnnotation.class);
            System.out.println("methodAnnotationName: " + methodAnnotation.name().toString());
            String[] methodAnnotationValue = methodAnnotation.value();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //读取字段注解
        try {
            Field fieldMyName = clazz.getDeclaredField("myName");
            MyFieldAnnotation fieldAnnotation = fieldMyName.getAnnotation(MyFieldAnnotation.class);
            System.out.println("fieldAnnotationName: " + fieldAnnotation.name().toString());
            String[] fieldAnnotationValue = fieldAnnotation.value();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
