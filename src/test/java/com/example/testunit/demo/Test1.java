package com.example.testunit.demo;

import com.example.testunit.model.InfoData;
import com.example.testunit.model.RuleData;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/1/29 9:15
 */
public class Test1 {
    char c;
    boolean b;

    /**
     * 常规遍历计算 和 函数管道惰性计算
     */
    @Test
    public void test1() {

        List<Integer> numbers = Arrays.asList(2, 5, 8, 15, 12, 19, 50, 23);
        // 常规遍历计算
        Integer result = null;
        for (int e : numbers) {
            if (e > 10 && e % 2 == 0) {
                result = e * 2;
                break;
            }
        }
        if (result != null)
            System.out.println("The value is " + result);
        else
            System.out.println("No value found");

        // 函数管道惰性计算
        System.out.println(
                numbers.stream()
                        .filter(e -> e > 10)
                        .filter(e -> e % 2 == 0)
                        .map(e -> e * 2)
                        .findFirst()
                        .map(e -> "The value is " + e)
                        .orElse("No value found"));

        System.out.println(
                numbers.stream()
                        .peek(e -> System.out.println("processing " + e))
                        .filter(e -> e > 10)
                        .filter(e -> e % 2 == 0)
                        .map(e -> e * 2)
                        .findFirst()
                        .map(e -> "The value is " + e)
                        .orElse("No value found"));

        List<Integer> numbers2 = Arrays.asList(1, 2, 3);
        int[] factor = new int[]{2};
        Stream<Integer> stream = numbers2.stream().map(e -> e * factor[0]);
        factor[0] = 0;
        stream.forEach(System.out::println);
    }

    /**
     * 测试反射调用getter和setter,替换指定内容的值
     */
    @Test
    public void test2() {
        RuleData rd = new RuleData();
        InfoData result = new InfoData();
        result.setField2("网银未提供叔叔啊");
        result.setField3("网银未提供数据");
        result.setField4("asdasdasdasdasdasd");
        result.setField5("111111111111");
        BeanInfo resultz = null;
        try {
            resultz = Introspector.getBeanInfo(result.getClass(), Object.class);
            PropertyDescriptor[] pds = resultz.getPropertyDescriptors();
            System.out.println(pds.length);
            int count = 0;
            for (int i = 0; i < pds.length; i++) {
                String invoke = (String) pds[i].getReadMethod().invoke(result, null);
                if (invoke != null) {
                    if (invoke.startsWith("网银未提供")) {
                        pds[i].getWriteMethod().invoke(result, new Object[]{null});
                        count++;
                    }
                }
            }
            System.out.println(result.getField3());
            System.out.println(result.getField3());
            System.out.println(result.getField5());
            BeanUtils.copyProperties(result, rd);
            System.out.println(rd.getField3());
            System.out.println(rd.getField3());
            System.out.println(rd.getField5());
        } catch (Exception e) {
            System.out.println("12312");
//            BeanUtils.copyProperties(result, rd);
        }
    }

    @Test
    public void test3() {
        IntBinaryOperator est = (int even, int odd) -> even + odd;
    }

    @Test
    public void test4(){
        System.out.println(3<<2);
    }

}
