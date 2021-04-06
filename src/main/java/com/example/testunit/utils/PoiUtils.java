package com.example.testunit.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/13 11:12
 */
public class PoiUtils {

    /**
     * 通过路径获取workbook
     * @param path
     * @return
     */
    public static Workbook getWorkbook(String path){
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
            return WorkbookFactory.create(bis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bis)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过文件流获取workbook
     * @param is
     * @return
     */
    public static Workbook getWorkbook(InputStream is){
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(is);
            return WorkbookFactory.create(bis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bis)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
