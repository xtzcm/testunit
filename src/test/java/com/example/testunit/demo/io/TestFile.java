package com.example.testunit.demo.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Despriction: File测试类
 * @Author: wangcheng
 * @Date: 2018/4/7 12:35
 */
public class TestFile {

    /**
     * 创建文件，并在虚拟机退出时删除该文件
     */
    @Test
    public void testMakeFile(){
        try {
            File dir = new File("G:" + File.separator + "test");//目录
            File file  = new File(dir, "testFile.txt");//文件
            file.deleteOnExit();//在程序退出时删除文件
            file.createNewFile();
            //file.createTempFile(prefix, suffix);
            //file.createTempFile("testFile2", ".txt", dir);
            System.out.println("File create success!");//breakpoint here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除目录及其所有文件(递归)
     */
    @Test
    public void testDeleteFileAndDir(){
        deleteFileAndDir(new File("G:" + File.separator + "test" + File.separator + "testDelete"));
    }

    /**
     * 测试将指定目录下的指定后缀的文件信息写出到指定目录的文件中
     */
    @Test
    public void testFileInfo2List(){
        File dir = new File("G:" + File.separator + "test");
        File resultFile = new File(dir, "info.txt");
        List<File> listFile = new ArrayList<>();
        fileInfo2List(dir, ".java", listFile);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(resultFile));
            for (File file: listFile) {
                String path = file.getAbsolutePath();
                bw.write(path);
                bw.newLine();
                bw.flush();
            }
            System.out.println("file info write success, result file location is : " + resultFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除目录及其所有文件(递归)
     */
    public static void deleteFileAndDir(File dir){
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFileAndDir(file);
                } else {
                    System.out.println("delete file ===> " + file + " <=== " + file.delete());
                }
            }
            System.out.println("delete dir  ===> " + dir + " <=== " + dir.delete());
        } else {
            System.out.println("delete file/dir failed, file not found!");
        }
    }

    /**
     * 将文件
     * @param dir 目录
     * @param suffix 要查找的文件的后缀
     * @param fileList 结果集
     */
    public static void fileInfo2List(File dir, String suffix, List<File> fileList) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file: files) {
                if (file.isDirectory()) {
                    fileInfo2List(file, suffix, fileList);
                } else {
                    if (file.getName().endsWith(suffix)) {
                        fileList.add(file);
                    }
                }
            }
        } else {
            System.out.println("operation failed, file not found!");
        }
    }
}
