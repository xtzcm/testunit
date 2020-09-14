package com.example.testunit.exception;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/5 20:33
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Error {
    String code() default "";
    String msg() default "";
}
