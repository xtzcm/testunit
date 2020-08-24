package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.*;

/**
 * @Despriction: 测试转换流(属于字符流)
 * @Author: wangcheng
 * @Date: 2018/4/14 12:56
 */
public class TestConvertStream {
    /**
     * 测试转换流InputStreamReader，将键盘输入转换为大写输出，遇到stop时停止
     */
    @Test
    public void testInputStreamReader(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = null;
            while ((line =br.readLine()) != null ) {
                if ("stop".equals(line)) {
                    break;
                }
                System.out.println(line.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试转换流OutputStreamWriter，将文件内容输出到控制台
     */
    @Test
    public void testInputStreamReaderAndOutputStreamWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("G:"+File.separator+"test"+File.separator+"test.txt")));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
            String line = null;
            while ((line =br.readLine()) != null ) {
                if ("stop".equals(line)) {
                    break;
                }
                bw.write(line.toUpperCase());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
