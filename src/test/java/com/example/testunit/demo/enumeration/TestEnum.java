package com.example.testunit.demo.enumeration;

import org.junit.Test;

/**
 * @Despriction: 测枚举
 * @Author: wangcheng
 * @Date: 2018/4/7 18:50
 */
public class TestEnum {

    @Test
    public void testMehtod(){
        System.out.println(EnumClassName.A.getValue());
        System.out.println(EnumClassName.A.localValue());
        System.out.println("获取枚举的名称：" + EnumClassName.A.name());
        System.out.println("获取枚举的索引位置：" + EnumClassName.A.ordinal());
    }

    /**
     * 将String转为枚举类型，String内容只能是枚举类中已有类型对应的name
     */
    @Test
    public void testValueOf(){
        EnumClassName d1 = EnumClassName.valueOf("A");
        System.out.println(d1);
        EnumClassName d2 = EnumClassName.valueOf("D");
        System.out.println(d2);
    }

    @Test
    public void testValues(){
        EnumClassName[] values = EnumClassName.values();
        for (EnumClassName e: values) {
            System.out.println(e);
        }
    }
}
