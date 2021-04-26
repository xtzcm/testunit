package com.example.testunit.demo.multithread;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/11/25 10:47
 */
public class TestThreadLocal {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> strThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
        strThreadLocal.set(Thread.currentThread().getName());
    }

    public Long getLong() {
        return longThreadLocal.get();
    }
    public String getStr() {
        return strThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadLocal test = new TestThreadLocal();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getStr());

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getStr());
            });
            thread.start();
            thread.join();
        }

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getStr());

    }
}
