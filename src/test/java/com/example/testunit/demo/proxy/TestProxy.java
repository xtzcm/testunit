package com.example.testunit.demo.proxy;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 测试类
 */
public class TestProxy {

    /**
     * 测试静态代理
     */
    @Test
    public void testStaticProxy(){
        Work work = new StaticProxyObj();
        work.sing();
        System.out.println("=======================");
        System.out.println(work.dance("藏舞"));
    }
    
    /**
     * 测试动态代理
     */
    @Test
    public void testDynamicProxy(){
        // 创建真实对象
        Work work = new RealObj();
        // 生成代理类的类加载器
        ClassLoader loader = ProxyFactory.class.getClassLoader();
        // 生成的代理类要去实现的接口,保持和原有类保存一致
        Class<?>[] interfaces = work.getClass().getInterfaces();
        // 要执行的方法
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //======= 扩展目标对象的功能 start
                String name = method.getName();
                if ("dance".equals(name)) {
                    // 如果执行的是save方法
                    System.out.println("加入我的日志,监控dance方法。");
                    return method.invoke(work, args);
                }
                //======= 扩展目标对象的功能 end
                return method.invoke(work, args);
            }
        };
        // 创建代理对象
        Work proxyObj = (Work) Proxy.newProxyInstance(loader, interfaces, handler);
        // 使用代理对象执行方法
        proxyObj.sing();
        System.out.println("=======================");
        System.out.println(proxyObj.dance("藏舞"));
    }
}
