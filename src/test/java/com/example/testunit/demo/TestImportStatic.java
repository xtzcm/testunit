package com.example.testunit.demo;

import org.junit.Test;

import java.io.PrintStream;

import static java.lang.System.out;
/**
 * @Despriction: 测试静态导入
 * @Author: wangcheng
 * @Date: 2018/5/27 15:34
 */
public class TestImportStatic {

    /**
     * 测试静态导入,导入后，调用静态方法或获取静态成员变量时不用再加"类名."
     * import 导入的是类
     * import static 导入的是类下的静态成员
     */
    @Test
    public void testImportStatic(){
        PrintStream myOut = out;
        myOut.print("静态导入System类下的静态方法out：import static java.lang.System.out;");
    }
}
