package com.example.testunit.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Despriction: Lambda表达式,Stream,函数式编程(函数管道)示例
 * @Author: wangcheng
 * @Date: 2018/2/2 14:07
 */
public class TestLambda {

    /**
     * 使用lambda表达式新建线程
     */
    @Test
    public void testOne() {
        // TODO 新加的property的作用域? 子线程设置,主线程先读为null,子线程先读,主线程再读有数据
        new Thread( () -> System.setProperty("key", "testKey") ).start();
        System.out.println(System.getProperty("key"));
        new Thread( () -> System.out.println(System.getProperty("key")) ).start();
        new Thread( () -> System.out.println(System.getProperty("key")) ).start();
    }

    /**
     * Map
     */
    @Test
    public void testTwo() {
        Map<String, String> map = new HashMap<>();
        map.putIfAbsent("a","1");
        map.putIfAbsent("b","2");
        map.putIfAbsent("c","3");
        map.forEach((key, value) -> System.out.println("key = " + key + ", value = " +value));
    }


    @Test
    public void testOne1() {
        Map<String, Integer> pageVisits = new HashMap<>();
        String page = "https://agiledeveloper.com";
        incrementPageVisit(pageVisits, page);
        incrementPageVisit(pageVisits, page);
        System.out.println(pageVisits.get(page));
    }
    public static void incrementPageVisit(Map<String, Integer> pageVisits, String page) {
        pageVisits.merge(page, 1, (oldValue, value) -> oldValue + value);
    }


}
