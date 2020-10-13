package com.example.testunit.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Despriction: 自定义注解类
 * @Author: wangcheng
 * @Date: 2018/6/3 11:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyFieldAnnotation {
    //自定义注解的属性
    //属性支持的类型：八种基本数据类型、String、Class 及其一维数组
    String name() default "";
    String[] value();

}
