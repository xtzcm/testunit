package com.example.testunit.demo;

import org.junit.Test;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/6 21:48
 */
public class TestString {

    @Test
    public void testCompareString(){

        String s1 = "string";//被编译器放入常量池
        String s2 = "string";//是已知字面量，被编译器放入常量池，但常量池已经有相同的字符串，s2直接指向该字符串
        String s3 = "str" + "ing";//是已知字面量，编译器自动优化，拼接成“string”放入常量池,常量池已经有相同的字符串，s3直接指向该字符串
        String s4 = "str" + new String("ing");//不是已知字面量，编译器不会自动优化，运行时才会在堆内存中创建新的字符串对象，并让s4指向该地址
        String s5 = new String("string");//不是已知字面量，编译器不会自动优化，运行时才会在堆内存中创建新的字符串对象，并让s5指向该地址
        String s6 = s4.intern();//intern方法试图将s4的值放入常量池中，但常量池中已经有相同的字符串了，直接返回该字符串的地址,，并让s6指向该地址
        String s7 = "str";//被编译器放入常量池
        String s8 = "ing";//被编译器放入常量池
        String s9 = s7 + s8;//不是已知字面量，编译器不会自动优化，运行时才会在堆内存中创建新的字符串对象，并让s4指向该地址

        System.out.println("s1 == s2 : " + (s1 == s2));
        System.out.println("s1 == s3 : " + (s1 == s3));
        System.out.println("s1 == s4 : " + (s1 == s4));
        System.out.println("s1 == s9 : " + (s1 == s9));
        System.out.println("s4 == s5 : " + (s4 == s5));
        System.out.println("s1 == s6 : " + (s1 == s6));
//        s1 == s2 : true  在编译期间，s1的"string"被直接放入class文件的常量池中，从而实现复用，载入运行时常量池后，s1、s2指向的是同一个内存地址，所以相等。
//        s1 == s3 : true  在编译期间，s3这种已知字面量的拼接会被优化，编译器直接将"str" + "ing"拼接成"string"，所以s1 == s3成立。
//        s1 == s4 : false 与s1 == s3不同的是，s4拼接的new String("ing")，不是已知字面量，要到运行时才可以确定结果，编译期时编译器不会对s4做优化，结合字符串不变定理，运行时s4最终指向一个新的内存地址，所以不相等。
//        s1 == s9 : false 与s1 == s4相似，s9 = s7 + s8，s7和s8作为两个变量，都是不可预料的，编译时编译器不会对s9做优化，运行时s9最终指向一个新的内存地址，所以不相等。
//        s4 == s5 : false s4和s5是堆内存中的两个不同对象，不相等。
//        s1 == s6 : true  s6 = s4.intern()，intern方法试图将s4的值放入常量池中，但常量池中已经有相同的字符串了，直接返回该字符串的地址，所以相同。
    }
}
