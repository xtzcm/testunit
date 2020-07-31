package com.example.testunit.demo.proxy;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

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
     * 测试JDK动态代理
     */
    @Test
    public void testJdkDynamicProxy(){
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

    /**
     * 测试cglib动态代理
     */
    @Test
    public void testCglibDynamicProxy(){
        // 创建真实对象
        Work work = new RealObj();
        // 创建增强器，用于动态的创建并增强代理对象
        Enhancer enhancer = new Enhancer();
        // 设置继承（设置创建的类为真实对象的接口的子类）
        enhancer.setSuperclass(Work.class);
        // 设置方法回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("...调用日志...");
                    return proxy.invoke(work, args);
                }
                return method.invoke(work, args);
            }
        });
        // 创建代理对象
        Work proxyObj = (Work)enhancer.create();
        // 使用代理对象执行方法
        proxyObj.sing();
        System.out.println("=======================");
        System.out.println(proxyObj.dance("藏舞"));
    }

}
