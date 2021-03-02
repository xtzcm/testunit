package com.example.testunit.demo.Introspector;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Despriction: 测试内省
 * @Author: wangcheng
 * @Date: 2018/10/21 17:46
 */
public class TestIntrospector {

    /**
     * 获取所有的属性，包含父类，属性根据getter来确定
     * @throws IntrospectionException
     */
    @Test
    public void testIntrospector() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(TestBean.class);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            System.out.println(descriptor.getName());
        }
    }

    /**
     * 获取所有的属性，排除指定的类，属性根据getter来确定
     * @throws IntrospectionException
     */
    @Test
    public void testIntrospector2() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(TestBean.class, Object.class);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            System.out.println(descriptor.getName());
        }
    }

    /**
     * 调用setter和getter
     * @throws Exception
     */
    @Test
    public void testIntrospector3() throws Exception {
        TestBean bean = new TestBean();
        PropertyDescriptor descriptor = new PropertyDescriptor("name", TestBean.class);
        Method writeMethod = descriptor.getWriteMethod();
        writeMethod.invoke(bean, "张三");
        Method readMethod = descriptor.getReadMethod();
        String result = (String) readMethod.invoke(bean, null);
        System.out.println(result);
    }
}

class TestBean {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAb(){
        return null;
    }

    public void setAb(String ab){
    }
}
