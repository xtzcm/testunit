package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.*;

/**
 * @Despriction: 基本数据类型操作流
 * @Author: wangcheng
 * @Date: 2018/4/7 15:25
 */
public class TestDataStream {

    @Test
    public void test1(){
        DataOutputStream dos = null;
        try {
            //DataOutputStream的参数为OutputStream类型的，所以创建一个FileOutputStream对象
            dos = new DataOutputStream(new FileOutputStream("G:"+File.separator+"test"+ File.separator+"test.txt"));
            dos.writeInt(45);
            dos.writeByte(0);
            dos.writeLong(469553L);
            dos.writeUTF("试一下啦");
            dos.writeChar('a');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭流
            if(dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
