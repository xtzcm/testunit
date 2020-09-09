package com.example.testunit.demo.innerClass;

/**
 * @Despriction: 测试匿名内部类
 * @Author: wangcheng
 * @Date: 2018/4/30 21:16
 */
public class TestAnonymousInnerClass {
    public static void main(String[] args) {
        System.out.print("测试匿名内部类：");
        Body3.beat().beat();
    }
}

/**
 * 提供给匿名内部类实现的接口,心脏，动物、人都可以继承
 */
interface Hearts{
    /**
     * 心脏的跳动方法
     */
    void beat();
}

/**
 * 实现匿名内部类,身体（人）
 */
class Body3{
    static int count = 3;

    /**
     * 人的心脏跳动方法
     * @return 匿名内部类,实现心脏接口，重写跳动方法，作为人的心脏的跳动方法
     */
    public static Hearts beat(){
        return new Hearts() {
            @Override
            public void beat() {
                System.out.println("Body heart beats,count = " + count);
            }
        };
    }
}