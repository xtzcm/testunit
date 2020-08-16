package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestIO {

    /**
     * 测试输出字符流
     */
    @Test
    public void testWriter(){
        FileWriter fw = null;
        try {
            fw = new FileWriter("G:"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"properties.txt");
            fw.write(System.currentTimeMillis() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试输入字符流,一次读取一个字符
     */
    @Test
    public void testReaderOneByOne(){
        FileReader fr = null;
        try {
            fr = new FileReader("G:"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"test.txt");
            int ch = 0;
            while ((ch = fr.read()) != -1) {
                System.out.println((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试输入字符流，读到数组中,数组装满了还未读完，则会先打印后再继续读
     */
    @Test
    public void testReader2(){
        FileReader fr = null;
        try {
            fr = new FileReader("G:"+System.getProperty("file.separator")+"test"+System.getProperty("file.separator")+"test.txt");
            char[] buf = new char[1024];
            int num = 0;
            while ((num = fr.read(buf)) != -1) {
                System.out.println(new String(buf, 0, num));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
