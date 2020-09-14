package com.example.testunit.demo;

import com.example.testunit.exception.AppException;
import com.example.testunit.exception.ErrCodeEnum;
import org.junit.Test;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/5 21:23
 */
public class TestError {

    @Test
    public void testGetError(){
        try {
            getError();
        } catch (AppException e){
            System.out.println("使用父类构造函数的错误信息：" + e.getMessage());
            System.out.println("使用自定义构造函数的错误码：" + e.getCode());
            System.out.println("使用自定义构造函数的错误信息：" + e.getMsg());
            System.out.println("使用自定义构造函数的arg息：" + e.getArg());
            System.out.println("使用自定义构造函数的Rootcause：" + e.getRootCause());
            System.out.println("使用自定义构造函数的args：" + e.getArgs());
        }
    }

    public void getError() throws AppException {
        int x = 0;
        try {
            int a = 1/x;
        } catch (Exception e) {
            throw new AppException(ErrCodeEnum.param_err, e.getMessage());
        }
    }
}
