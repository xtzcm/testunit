package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @Despriction: 测试编码格式
 * @Author: wangcheng
 * @Date: 2018/4/14 13:39
 */
public class TestCharSet {

    /**
     * 测试GBK和UTF-8下中英文文字和标点的字节大小
     */
    @Test
    public void testEncoding1(){
        String strEnglish = "a";
        String strChinese = "哈";
        String punctuationEnglish = ",";
        String punctuationChinese = "，";
        try {
            System.out.println("英文字符，GBK编码大小：" + strEnglish.getBytes("GBK").length);
            System.out.println("英文标点，GBK编码大小：" + punctuationEnglish.getBytes("GBK").length);
            System.out.println("中文字符，GBK编码大小：" + strChinese.getBytes("GBK").length);
            System.out.println("中文标点，GBK编码大小：" + punctuationChinese.getBytes("GBK").length);
            System.out.println("英文字符，UTF-8编码大小：" + strEnglish.getBytes("UTF-8").length);
            System.out.println("英文标点，UTF-8编码大小：" + punctuationEnglish.getBytes("UTF-8").length);
            System.out.println("中文字符，UTF-8编码大小：" + strChinese.getBytes("UTF-8").length);
            System.out.println("中文标点，UTF-8编码大小：" + punctuationChinese.getBytes("UTF-8").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试编码和解码
     * 编码和解码要用同一种编码表
     * 用不同编码表解码会出现乱码，此时，用解码时的编码表重新编码后，再用正确的编码表即可解码
     */
    @Test
    public void testEncoding2(){
        try {
            byte[] bytes = "测试".getBytes("UTF-8");//UTF-8编码
            System.out.println("测试str UTF-8 = " + new String(bytes, "UTF-8"));
            System.out.println("测试str UTF-8 byte[] = " + Arrays.toString(bytes));
            System.out.println("=======================================");

            String gbkStr = new String(bytes, "GBK");//GBK解码
            System.out.println("测试str UTF-8 转GBK = " + gbkStr);
            System.out.println("测试str UTF-8 byte[] 转GBK byte[] = " + Arrays.toString(gbkStr.getBytes()));
            System.out.println("=======================================");

            byte[] utfBytes = gbkStr.getBytes("GBK");//重新使用GBK编码
            String utfStr = new String(utfBytes, "UTF-8");//使用UTF-8解码
            System.out.println("测试str GBK 转回UTF-8 = " + utfStr);
            System.out.println("测试str GBK byte[] 转回UTF-8 byte[] = " + Arrays.toString(utfStr.getBytes()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试特殊情况，“联通”在文本文件中保存后再次打开为乱码
     * 原因：联通的GBK编码前缀刚好符号UTF-8的编码规则
     * 转成二进制后为：
     * 11000001
     * 10101010
     * 11001101
     * 10101000
     * 前缀110... 10...刚好符号UTF-8两位组成一个字符的规则
     */
    @Test
    public void test联通(){
        String str = "联通";
        try {
            byte[] gbks = str.getBytes("GBK");
            for (byte b: gbks) {
                System.out.println(Integer.toBinaryString(b & 255));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试转换流的编码格式
     */
    @Test
    public void test1(){
        try {
            //writeTest(new File("G:\\test\\test.txt"), "测试数据", "GBK");//写数据
            String result = readTest(new File("G:\\test\\test.txt"), "GBK");//读取数据
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用转换流写出数据
     */
    public static void writeTest(File file, String msg, String encodingForm){
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(file),encodingForm);
            osw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != osw)
                    osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用转换流读取字符串数据
     */
    public static String readTest(File file, String encodingForm){
        InputStreamReader isr = null;
        try {
            char[] chars = new char[1024];
            isr = new InputStreamReader(new FileInputStream(file), encodingForm);
            int len = isr.read(chars);
            return new String(chars, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (null != isr)
                    isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
