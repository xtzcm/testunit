package com.example.testunit.demo.proxy;

/**
 * 静态代理对象
 */
public class StaticProxyObj implements Work{

    private Work work;

    public StaticProxyObj() {
        this.work = new RealObj();
    }

    @Override
    public void sing() {
        System.out.println("代理对象，调用真实对象的sing方法");
        work.sing();
    }

    @Override
    public String dance(String danceName) {
        System.out.println("代理对象，调用真实对象的dance方法");
        return work.dance(danceName);
    }
}
