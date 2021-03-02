package com.example.testunit.demo.reflect;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/10/21 18:37
 */
public class BeanForReflect {

    private String name = "default name";
    private String age = "0";
    public String pub_id = "001";

    public BeanForReflect() {
    }

    private BeanForReflect(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
    }
}
