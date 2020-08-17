package com.example.testunit.demo.io;

import com.example.testunit.demo.io.bean.CanSerializable;
import org.junit.Test;

import java.io.*;

/**
 * @Despriction: 测试序列化
 * @Author: wangcheng
 * @Date: 2018/4/7 14:41
 */
public class TestSerialization {

    /**
     * 序列化对象
     */
    @Test
    public void testSerialize(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("G:"+ File.separator+"test"+File.separator+"serializedObj.txt"));
            oos.writeObject(new CanSerializable("zhangsan", 31));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反序列化
     */
    @Test
    public void testDeserialize() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("G:"+ File.separator+"test"+File.separator+"serializedObj.txt"));
            CanSerializable obj = (CanSerializable)ois.readObject();
            System.out.println("Deserialize success, object: " + obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
