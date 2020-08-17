package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @Despriction: 测试合并流
 * @Author: wangcheng
 * @Date: 2018/4/7 15:20
 */
public class TestSequenceStream {

    /**
     * 合并流
     */
    @Test
    public void testSequenceStream(){
        SequenceInputStream sis = null;
        FileOutputStream fos = null;
        try {
            Vector<InputStream> vector = new Vector<>();
            vector.add(new FileInputStream("G:"+ File.separator+"test"+File.separator+"sequenceInputStream"+File.separator+"part1.txt"));
            vector.add(new FileInputStream("G:"+File.separator+"test"+File.separator+"sequenceInputStream"+File.separator+"part2.txt"));
            vector.add(new FileInputStream("G:"+File.separator+"test"+File.separator+"sequenceInputStream"+File.separator+"part3.txt"));
            Enumeration<InputStream> elements = vector.elements();
            sis = new SequenceInputStream(elements);
            fos = new FileOutputStream("G:"+File.separator+"test"+File.separator+"sequenceInputStream"+File.separator+"result.txt");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = sis.read(bytes)) != -1) {
                fos.write(bytes,0 ,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (fos != null)
                    sis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 切割文件
     */
    @Test
    public void testSplitFile(){
        String sepr = File.separator;
        FileInputStream fis =null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("G:"+ sepr +"test"+ sepr +"splitFile"+ sepr +"source.mp3");
            byte[] bytes = new byte[1024*1024];
            int len = 0;
            int count = 1;
            while ((len = fis.read(bytes)) != -1) {
                fos = new FileOutputStream("G:" + sepr + "test" + sepr + "splitFile" + sepr + "result-" + (count++) + ".part");
                fos.write(bytes, 0, len);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 合并文件
     */
    @Test
    public void testMergeFile(){
        String sepr = File.separator;
        List<FileInputStream> list = new ArrayList<>();
        SequenceInputStream sis = null;
        FileOutputStream fos = null;
        try {
            for (int i = 1; i < 3; i++) {
                list.add(new FileInputStream("G:" + sepr + "test" + sepr + "splitFile" + sepr + "result-" + i + ".part"));
            }
            Iterator<FileInputStream> iterator = list.iterator();
            final Enumeration<FileInputStream> enumeration = new Enumeration<FileInputStream>() {
                @Override
                public boolean hasMoreElements() {
                    return iterator.hasNext();
                }

                @Override
                public FileInputStream nextElement() {
                    return iterator.next();
                }
            };
            sis = new SequenceInputStream(enumeration);
            fos = new FileOutputStream("G:" + sepr + "test" + sepr + "splitFile" + sepr + "result" + ".mp3");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = sis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (sis != null)
                    sis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
