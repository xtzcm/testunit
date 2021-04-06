package com.example.testunit.demo.parseExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
            Cell cell = workbook.getSheet("Sheet2").getRow(0).getCell(0);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            String value = null;
            switch (cell.getCellType()) {
                case _NONE:
                    break;
                case NUMERIC:
                    break;
                case STRING:
                    break;
                case FORMULA:
                    CellValue cellValue = evaluator.evaluate(cell);
                    switch (cellValue.getCellType()) {
                        case STRING:
                            System.out.print("String :");
                            value = cellValue.getStringValue();
                            break;

                        case NUMERIC:
                            System.out.print("NUMERIC:");
                            value = String.valueOf(cellValue.getNumberValue());
                            break;
                        case FORMULA:
                            System.out.print("FORMULA:");
                            break;
                        default:
                            break;
                    }
                    break;
                case BLANK:
                    break;
                case BOOLEAN:
                    break;
                case ERROR:
                    break;
            }
            System.out.println(value);
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
    }
}
