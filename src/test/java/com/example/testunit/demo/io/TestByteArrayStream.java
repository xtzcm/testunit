package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.*;

/**
 * @Despriction: 字节数组操作流
 * @Author: wangcheng
 * @Date: 2018/4/7 15:26
 */
public class TestByteArrayStream {

    @Test
    public void test(){
        ByteArrayInputStream bis = new ByteArrayInputStream("abefasfda".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }
        System.out.println("bos.size" + bos.size());
        System.out.println("bos.toString" + bos.toString());
        try {
            bos.writeTo(new FileOutputStream("G:"+ File.separator+"test"+File.separator+"testBos.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
