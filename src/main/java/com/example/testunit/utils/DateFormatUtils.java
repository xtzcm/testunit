package com.example.testunit.utils;


import org.apache.poi.ss.usermodel.Cell;

import java.text.SimpleDateFormat;

/**
 * @Despriction:
 * @Author: wangcheng
 * @Date: 2018/12/15 10:33
 */
public class DateFormatUtils {

    private DateFormatUtils() {
    }

    /**
     * 验证单元格的内容是否是自定义日期时间格式
     * @param cell
     * @return
     */
    public static boolean isCellDateFormatted(Cell cell) {
        boolean isDateFormat = ExtBuiltinFormats.DATE_FORMATTER_MAP.containsKey(cell.getCellStyle().getDataFormatString());
        boolean isExtDateFormat = ExtBuiltinFormats.EXT_DATE_FORMATTER_MAP.containsKey(Integer.valueOf(cell.getCellStyle().getDataFormat()));
        return isDateFormat || isExtDateFormat;
    }

    /**
     * 匹配自定义日期时间格式并返回SimpleDateFormat
     * @param cell
     * @return
     */
    @Deprecated
    public static String format(Cell cell) {
        SimpleDateFormat formatter = ExtBuiltinFormats.DATE_FORMATTER_MAP.get(cell.getCellStyle().getDataFormatString());
        if (null == formatter)
            formatter = ExtBuiltinFormats.EXT_DATE_FORMATTER_MAP.get(Integer.valueOf(cell.getCellStyle().getDataFormat()));
        //TODO formatter待改造，问题：日期格式丢失年份，或时间格式丢失日期
        return null == formatter ? PoiUtils.DATA_FORMATTER.formatCellValue(cell) : formatter.format(cell.getDateCellValue());
    }
}
