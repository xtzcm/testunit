package com.example.testunit.demo.innerClass;

/**
 * @Despriction: 测试普通内部类
 * @Author: wangcheng
 * @Date: 2018/4/30 21:25
 */
public class TestInnerClass {
    public static void main(String[] args) {
        System.out.print("测试内部类：");
        new Body().new Heart().beat();
    }
}

/**
 * Body类
 */
class Body{
    /**
     * body的内部类，心脏
     */
    class Heart{
        int count = 3;
        /**
         * 心脏的跳动方法
         */
        void beat(){
            System.out.println("Heart beats,count = " + count);
        }
    }
}