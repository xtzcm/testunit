package com.example.testunit.demo.proxy;

/**
 * 真实对象
 */
public class RealObj implements Work{


    @Override
    public void sing() {
        System.out.println("真实对象，sing");
    }

    @Override
    public String dance(String danceName) {
        System.out.println("真实对象，dance，名称："+ danceName);
        return danceName + " good!";
    }
}
