package com.example.testunit.demo.poi;

import com.example.testunit.utils.PoiUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/12/14 21:53
 */
public class TestDateFormats {

    @Test
    public void testGetDate(){
        String excelFile = "G:\\test\\testDateFormats.xlsx";
        String outExcelFile = "G:\\test\\testDateFormats-result.xlsx";
        String sheetName = "Sheet1";
        Workbook workbook = null;
        FileOutputStream fos = null;
        try {
            workbook = PoiUtils.getWorkbook(excelFile);
            if (null == workbook)
                return;
            Sheet sheet = workbook.getSheet(sheetName);
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            Row row;
            Cell dataCell;
            while (firstRowNum <= lastRowNum) {
                row = sheet.getRow(firstRowNum++);
                dataCell = row.getCell(0);
                String value = PoiUtils.getCellValue(dataCell);
                Cell cell2 = row.createCell(1);
                cell2.setCellValue(value);
                Cell cell3 = row.createCell(2);
                cell3.setCellValue(dataCell.getCellStyle().getDataFormatString());
            }
            fos = new FileOutputStream(outExcelFile);
            workbook.write(fos);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != workbook) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
