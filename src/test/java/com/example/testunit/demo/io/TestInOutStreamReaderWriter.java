package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.*;

/**
 * @Despriction: InputStream,OutputStream,Reader,Writer测试类
 * @Author: wangcheng
 * @Date: 2018/4/7 12:35
 */
public class TestInOutStreamReaderWriter {

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
            fr = new FileReader("G:"+File.separator+"test"+File.separator+"test.txt");
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
            fr = new FileReader("G:"+File.separator+"test"+File.separator+"test.txt");
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

    /**
     * 测试文件复制(使用Reader读取A文件内容并使用Writer写到B文件)
     */
    @Test
    public void testReaderAndWriter(){
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("G:"+File.separator+"test"+File.separator+"test.txt");
            fw = new FileWriter("G:"+File.separator+"test"+File.separator+"testCopy.txt");
            char[] buf = new char[1024];
            int num = 0;
            while ((num = fr.read(buf)) != -1) {
                fw.write(new String(buf, 0, num));
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
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试文件复制(使用BufferedReader读取A文件内容并使用BufferedWriter写到B文件)
     */
    @Test
    public void testBufferedReaderAndBufferedWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("G:"+File.separator+"test"+File.separator+"test.txt"));
            bw = new BufferedWriter(new FileWriter("G:"+File.separator+"test"+File.separator+"testCopy.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试输出字节流
     */
    @Test
    public void testOutputStream1(){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("G:"+File.separator+"test"+File.separator+"properties.txt");
            fos.write("ceshitest哈".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试输入字节流，读到数组中,数组装满了还未读完，则会先打印后再继续读
     */
    @Test
    public void testInputStream2(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("G:"+File.separator+"test"+File.separator+"test.txt");
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fis.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试输入字节流,一次读取一个字符
     */
    @Test
    public void testInputStream(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("G:"+File.separator+"test"+File.separator+"test.txt");
            int integer = 0;
            while ((integer = fis.read()) != -1) {
                System.out.println((char)integer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试文件复制(使用FileInputStream读取A文件并使用FileOutputStream写到B文件)
     */
    @Test
    public void testFileInputStreamAndFileOutputStream(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("G:"+File.separator+"test"+File.separator+"微信图片_20180111151700.jpg");
            fos = new FileOutputStream("G:"+File.separator+"test"+File.separator+"picCopy.jpg");
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试文件复制(使用BufferedInputStream读取A文件并使用BufferedOutputStream写到B文件)
     */
    @Test
    public void testBufferedInputStreamAndBufferedOutputStream(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("G:"+File.separator+"test"+File.separator+"微信图片_20180111151700.jpg"));
            bos = new BufferedOutputStream(new FileOutputStream("G:"+File.separator+"test"+File.separator+"picCopy.jpg"));
            int by = 0;
            while ((by = bis.read()) != -1) {
                bos.write(by);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试键盘标准输入，将键盘输入转换为大写输出，遇到stop时停止
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
     * 测试控制台输出，将文件内容输出到控制台
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
