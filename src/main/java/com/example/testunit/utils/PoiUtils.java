package com.example.testunit.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/5/13 11:12
 */
public class PoiUtils {

    /**
     * DataFormatter,用于格式化单元格本身的格式来格式化值
     */
    public static final DataFormatter DATA_FORMATTER = new DataFormatter();
    /**
     * FormulaEvaluator,用于获取公式值,传入null时表示获取公式本身.
     * FormulaEvaluator对象获取:可通过Workbook对象{@link Workbook#getCreationHelper()}获取CreationHelper对象,
     * 再通过CreationHelper对象{@link CreationHelper#createFormulaEvaluator()}创建FormulaEvaluator对象.
     * e.g. workbook.getCreationHelper().createFormulaEvaluator()
     */
    private static ThreadLocal<FormulaEvaluator> formulaEvaluator = new ThreadLocal<>();

    /**
     * 获取工作簿
     * @param filePath excel文件的绝对路径,例:D:\\test\\test.xlsx
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(String filePath) throws Exception {
        return getWorkbook(new FileInputStream(filePath));
    }
    /**
     * 获取工作簿
     * @param is excel文件的输入流
     * @return
     */
    public static Workbook getWorkbook(InputStream is) throws Exception {
        Workbook workbook;
        BufferedInputStream bis = null;
        try {
            long startTime = System.currentTimeMillis();
            bis = new BufferedInputStream(is);
            workbook = WorkbookFactory.create(bis);
            //TODO 添加日志
            System.out.println("get Workbook use time: " + (System.currentTimeMillis() - startTime) + " ms");
            //log.info("get Workbook use time: {} {}",System.currentTimeMillis() - startTime, "ms");
            if (null == workbook)
                throw new Exception("get Workbook, Workbook is null");
            return workbook;
        } catch (Exception e) {
            //TODO 添加日志
            System.out.println("get Workbook, failed to get Workbook");
            //log.error("failed to get Workbook", e);
            throw new Exception("get Workbook, failed to get Workbook");
        } finally {
            try {
                if (null != bis)
                    bis.close();
            } catch (IOException e) {
                //TODO 添加日志
                System.out.println("get Workbook, failed to close IO stream");
                //log.error("get Workbook, failed to close IO stream", e);
            }
            try {
                if (null != is)
                    is.close();
            } catch (IOException e) {
                //TODO 添加日志
                System.out.println("get Workbook, failed to close IO stream");
                //log.error("get Workbook, failed to close IO stream", e);
            }
        }
    }

    /**
     * 获取单元格的值
     * 数字与excel显示格式保持一致,不跳过公式，获取公式的计算结果，允许错误格式
     * @param cell
     * @return
     * @throws Exception
     */
    public static String getCellValue(Cell cell) throws Exception {
        return getCellValue(cell, true, true);
    }

    /**
     * 获取单元格的值
     * 数字与excel显示格式保持一致,可选择是否跳过公式，当不跳过公式时将获取公式的计算结果并允许错误格式
     * @param cell
     * @return
     * @throws Exception
     */
    public static String getCellValue(Cell cell, boolean isIgnoreFormula) throws Exception {
        return getCellValue(cell, isIgnoreFormula, true, true);
    }

    /**
     * 获取单元格的值
     * 数字与excel显示格式保持一致,可选择是否跳过公式，是否获取公式的计算结果以及是否允许错误格式
     * @param cell 单元格
     * @param isIgnoreFormula 是否跳过公式,若为true,则单元格是公式时返回""
     * @param isGetFormulaValue 是否获取公式的计算结果，true为获取，false为不获取(返回公式本身)
     * @param isAllowErrorType 是否允许错误格式，，true为允许，false为不允许(返回错误值)
     * @return
     * @throws Exception
     */
    public static String getCellValue(Cell cell, boolean isIgnoreFormula, boolean isGetFormulaValue, boolean isAllowErrorType) throws Exception {
        String result = "";
        if (null != cell && !(isIgnoreFormula & CellType.FORMULA.equals(cell.getCellType())))
            result = getCellValue(cell, isGetFormulaValue, isAllowErrorType);
        return result;
    }

    /**
     * 获取单元格的值
     * 数字与excel显示格式保持一致,不跳过公式，可选择是否获取公式的计算结果以及是否允许错误格式
     * @param cell
     * @param isGetFormulaValue 是否获取公式的计算结果，true为获取，false为不获取(返回公式本身)
     * @param isAllowErrType 是否允许错误格式,true为允许,false为不允许(将抛出异常)
     * @return a string value of the cell
     * @throws Exception
     */
    public static String getCellValue(Cell cell, boolean isGetFormulaValue, boolean isAllowErrType) throws Exception {
        if (null == cell) {
            return "";
        }
        CellType cellType = cell.getCellType();
        if (cellType == CellType.FORMULA) {
            if (!isGetFormulaValue) {
                return cell.getCellFormula();
            }
            FormulaEvaluator evaluator = PoiUtils.formulaEvaluator.get();
            if (null == evaluator){
                PoiUtils.formulaEvaluator.set(cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator());
                evaluator = PoiUtils.formulaEvaluator.get();
            }
            cellType = evaluator.evaluateFormulaCell(cell);
            if (cellType == CellType.NUMERIC) {
                cell.setCellType(CellType.NUMERIC);
            }
        }
        switch (cellType) {
            case NUMERIC:
                if (DateFormatUtils.isCellDateFormatted(cell))
                    return DateFormatUtils.format(cell);//TODO 待改造，问题：日期格式丢失年份，或时间格式丢失日期
                else
                    return DATA_FORMATTER.formatCellValue(cell);
            case STRING:
                return cell.getRichStringCellValue().getString();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case BLANK:
                return "";
            case ERROR:
                if (isAllowErrType)
                    return FormulaError.forInt(cell.getErrorCellValue()).getString();
                throw new Exception("data format error of the cell " + getCellLocation(cell));
            default:
                throw new Exception("unexpected celltype " + cellType + " of the cell " + getCellLocation(cell));
        }
    }


    /**
     * 返回单元格对应的显示位置
     * @param cell
     * @return 返回单元格的显示位置,如A2
     */
    public static String getCellLocation(Cell cell) {
        int columnIndex = cell.getColumnIndex();
        if(++columnIndex < 1 || columnIndex > 16384)//判断列号是否超出范围
            throw new IllegalArgumentException();
        String[] sources = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder sb = new StringBuilder(5);
        int remainder = columnIndex % 26;//求最右边的字母
        if(remainder == 0){	//说明(num_n-1)=26，第26个字母是Z
            sb.append("Z");
            remainder = 26;	//因为接下来W-(num_n-1)也就是columnNo-remainder,所以需要把remainder赋值回26
        } else {//如果最右边字母不是Z的话，就去sources数组相应的位置取字母，remainder不用变
            sb.append(sources[remainder - 1]);
        }
        columnIndex = (columnIndex - remainder) / 26 - 1;//用来判断接下来是否还有其他字母
        //当 当前循环是求最后一个字母时（从右往左），(columnNo-remainder)/26就会是0，再减1也就是-1。
        //因此通过判断(columnNo-remainder)/26-1是否大于-1来判断结束
        while(columnIndex > -1){
            remainder = columnIndex % 26;
            sb.append(sources[remainder]);
            columnIndex = (columnIndex - remainder) / 26 - 1;
        }
        return sb.reverse().toString() + (cell.getRow().getRowNum() + 1);//因为是从右往左解析的,所以需要反转
    }


    /**
     * 获取合并单元格信息
     * @param sheet
     * @return 返回以合并单元格值为key,以包含起始行和起始列信息的Map为value
     * @throws Exception
     */
    public static Map<String, Map<String, Integer>> getMergedRegions(Sheet sheet) throws Exception {
        Map<String, Map<String, Integer>> mergedRegions = new HashMap<>();
        int numMergedRegions = sheet.getNumMergedRegions();
        Map<String, Integer> mergedRegionTemp;
        for (int i = 0; i < numMergedRegions; i++) {
            mergedRegionTemp = new HashMap<>();
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            mergedRegionTemp.put("firstRow", mergedRegion.getFirstRow());
            mergedRegionTemp.put("lastRow", mergedRegion.getLastRow());
            mergedRegionTemp.put("firstColumn", mergedRegion.getLastColumn());
            mergedRegionTemp.put("lastColumn", mergedRegion.getFirstColumn());
            String mergedRegionValue = getCellValue(sheet.getRow(mergedRegion.getFirstRow()).getCell(mergedRegion.getFirstColumn()));
            if (null != mergedRegionValue && "".equals(mergedRegionValue.trim())){
                mergedRegions.put(mergedRegionValue.trim(), mergedRegionTemp);
            }
        }
        return mergedRegions;
    }

    /**
     * 获取当前Sheet的有效行数据
     * @param sheet
     * @param startRow 起始行行号,从0开始
     * @param startRow 结束行行号,从0开始
     * @return
     */
    public static List<Row> getRows(Sheet sheet, int startRow, int endRow) {
        startRow = startRow < 0 ? 0 : startRow;
        endRow = endRow < 0 ? 0 : (endRow > sheet.getLastRowNum() ? sheet.getLastRowNum() : endRow);
        List<Row> rows = new ArrayList<>();
        Row row;
        while (endRow >= startRow) {
            row = sheet.getRow(startRow++);
            if (null != row)
                rows.add(row);
        }
        return rows;
    }

    /**
     * 获取行的最大有效列号
     * @param row
     * @return
     */
    public static int getLastColumnNumOfRow(Row row) {
        short firstCellNum = row.getFirstCellNum();
        short lastCellNum = row.getLastCellNum();//getLastCellNum=最大有效列号+1
        return firstCellNum == lastCellNum ? lastCellNum : lastCellNum - 1;
    }

}
