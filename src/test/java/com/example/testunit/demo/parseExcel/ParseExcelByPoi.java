package com.example.testunit.demo.parseExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/4/9 20:19
 */
public class ParseExcelByPoi {

    @Test
    public void testParseExcel() {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("G:\\test\\test.xlsx"));
            Workbook workbook = WorkbookFactory.create(bis);
            System.out.println("sheet数量 = " + workbook.getNumberOfSheets());
            if (workbook.getNumberOfSheets() > 0) {
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    System.out.println("sheet" + (i + 1) + "有效行数 = " + workbook.getSheetAt(i).getLastRowNum());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != bis)
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
