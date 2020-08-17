package com.example.testunit.demo.io.bean;

import java.io.Serializable;

/**
 * @Despriction: 测试序列化的对象，需实现Serializable接口
 * @Author: wangcheng
 * @Date: 2018/4/7 14:47
 */
public class CanSerializable implements Serializable{
    private static final long serialVersionUID = 496875492781153765L;

    public CanSerializable(){}

    public CanSerializable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CanSerializable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
