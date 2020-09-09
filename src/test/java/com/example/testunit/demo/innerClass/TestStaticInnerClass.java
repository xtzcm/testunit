package com.example.testunit.demo.innerClass;

/**
 * @Despriction: 测试静态内部类
 * @Author: wangcheng
 * @Date: 2018/4/30 21:35
 */
public class TestStaticInnerClass {
    public static void main(String[] args) {
        new Body1.Heart1().Beat();
    }
}

class Body1{
    static int count = 3;
    static class Heart1{
        void Beat(){
            System.out.println("Heart Beats,count = " + count);
        }
    }
}