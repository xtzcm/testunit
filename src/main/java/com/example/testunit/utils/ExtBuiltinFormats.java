package com.example.testunit.utils;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author wangcheng
 * @Despriction 扩展自定义格式
 * @since 2018/12/13 20:19
 */
public class ExtBuiltinFormats {

     /**
     * 内建日期时间格式化枚举类,用于扩展获取dataFormatString为空的类型
     * e.g. 使用CellStyle获取{@link Cell#getCellStyle}{@link CellStyle#getDataFormatString}
     */
    public static Map<Integer, SimpleDateFormat> EXT_DATE_FORMATTER_MAP = new HashMap<>();
    public static Map<String, SimpleDateFormat> DATE_FORMATTER_MAP = new HashMap<>();
    public static final SimpleDateFormat FORMAT_YYYY_M_D = new SimpleDateFormat("yyyy/M/d");
    public static final SimpleDateFormat FORMAT_YY_M_D = new SimpleDateFormat("yy/M/d");
    public static final SimpleDateFormat FORMAT_YYYY_NIAN_M_YUE_D_RI = new SimpleDateFormat("yyyy年M月d日");
    public static final SimpleDateFormat FORMAT_YYYY_NIAM_M_YUE = new SimpleDateFormat("yyyy年M月");
    public static final SimpleDateFormat FORMAT_M_YUE_D_RI = new SimpleDateFormat("M月d日");
    public static final SimpleDateFormat FORMAT_M_D = new SimpleDateFormat("M/d");
    public static final SimpleDateFormat FORMAT_MM_DD_YY = new SimpleDateFormat("MM/dd/yy");
    public static final SimpleDateFormat FORMAT_M_D_YY = new SimpleDateFormat("M/d/yy");
    public static final SimpleDateFormat FORMAT_MMM_ENGLISH = new SimpleDateFormat("MMM", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_MMM_S_YY_ENGLISH = new SimpleDateFormat("MMM-yy", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_MMMM_S_YY_ENGLISH = new SimpleDateFormat("MMMM-yy", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_DD_S_MMM_S_YY_ENGLISH = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_D_S_MMM_S_YY_ENGLISH = new SimpleDateFormat("d-MMM-yy", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_D_S_MMM_ENGLISH = new SimpleDateFormat("d-MMM", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_TIME_24 = new SimpleDateFormat("yyyy/M/d H:mm:ss");
    public static final SimpleDateFormat FORMAT_TIME_H_MM_24 = new SimpleDateFormat("yyyy/M/d H:mm");
    public static final SimpleDateFormat FORMAT_TIME_12 = new SimpleDateFormat("yyyy/M/d h:mm:ss a", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_WEEK = new SimpleDateFormat("E");
    public static final SimpleDateFormat FORMAT_H_MM_SS = new SimpleDateFormat("H:mm:ss");
    public static final SimpleDateFormat FORMAT_H_MM = new SimpleDateFormat("H:mm");
    public static final SimpleDateFormat FORMAT_MM_SS = new SimpleDateFormat("mm:ss");
    public static final SimpleDateFormat FORMAT_MM_SS_S = new SimpleDateFormat("mm:ss.S");
    public static final SimpleDateFormat FORMAT_H_MM_A = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_H_MM_SS_A = new SimpleDateFormat("h:mm:ss a", Locale.ENGLISH);
    public static final SimpleDateFormat FORMAT_H_SHI_MM_FEN = new SimpleDateFormat("H时mm分");
    public static final SimpleDateFormat FORMAT_H_SHI_MM_FEN_SS_MIAO = new SimpleDateFormat("H时mm分ss秒");
    public static final SimpleDateFormat FORMAT_A_H_SHI_MM_FEN = new SimpleDateFormat("ah时mm分");
    public static final SimpleDateFormat FORMAT_A_H_SHI_MM_FEN_SS_MIAO = new SimpleDateFormat("ah时mm分ss秒");

    static {
        EXT_DATE_FORMATTER_MAP.put(55, ExtBuiltinFormats.FORMAT_A_H_SHI_MM_FEN);//上午/下午h"时"mm"分"
        EXT_DATE_FORMATTER_MAP.put(56, ExtBuiltinFormats.FORMAT_A_H_SHI_MM_FEN_SS_MIAO);//上午/下午h"时"mm"分"ss"秒"
        EXT_DATE_FORMATTER_MAP.put(57, ExtBuiltinFormats.FORMAT_YYYY_NIAM_M_YUE);//yyyy"年"m"月"
        EXT_DATE_FORMATTER_MAP.put(58, ExtBuiltinFormats.FORMAT_M_YUE_D_RI);//m"月"d"日"
        //date
        DATE_FORMATTER_MAP.put("m/d/yy", FORMAT_YYYY_M_D);
        DATE_FORMATTER_MAP.put("yyyy/m/d;@", FORMAT_YYYY_M_D);
        DATE_FORMATTER_MAP.put("[$-F800]dddd\\,\\ mmmm\\ dd\\,\\ yyyy", FORMAT_YYYY_NIAN_M_YUE_D_RI);
        DATE_FORMATTER_MAP.put("m/d/yy;@", FORMAT_M_D_YY);
        DATE_FORMATTER_MAP.put("mm/dd/yy;@", FORMAT_MM_DD_YY);
        DATE_FORMATTER_MAP.put("[DBNum1][$-804]yyyy\"年\"m\"月\"d\"日\";@", FORMAT_YYYY_NIAN_M_YUE_D_RI);//TODO
        DATE_FORMATTER_MAP.put("[DBNum1][$-804]yyyy\"年\"m\"月\";@", FORMAT_YYYY_NIAN_M_YUE_D_RI);//TODO
        DATE_FORMATTER_MAP.put("[DBNum1][$-804]m\"月\"d\"日\";@", FORMAT_M_YUE_D_RI);//TODO
        DATE_FORMATTER_MAP.put("yyyy\"年\"m\"月\"d\"日\";@", FORMAT_YYYY_NIAN_M_YUE_D_RI);
        DATE_FORMATTER_MAP.put("yyyy\"年\"m\"月\";@", FORMAT_YYYY_NIAM_M_YUE);
        DATE_FORMATTER_MAP.put("m\"月\"d\"日\";@", FORMAT_M_YUE_D_RI);
        DATE_FORMATTER_MAP.put("[$-804]aaaa;@", FORMAT_WEEK);//星期几
        DATE_FORMATTER_MAP.put("[$-804]aaa;@", FORMAT_WEEK);//周几 TODO
        DATE_FORMATTER_MAP.put("[$-409]yyyy/m/d\\ h:mm\\ AM/PM;@", FORMAT_TIME_12);
        DATE_FORMATTER_MAP.put("yyyy/m/d\\ h:mm;@", FORMAT_TIME_24);
        DATE_FORMATTER_MAP.put("yy/m/d;@", FORMAT_YY_M_D);
        DATE_FORMATTER_MAP.put("m/d;@", FORMAT_M_D);
        DATE_FORMATTER_MAP.put("[$-409]d\\-mmm;@", FORMAT_D_S_MMM_ENGLISH);
        DATE_FORMATTER_MAP.put("[$-409]d\\-mmm\\-yy;@", FORMAT_D_S_MMM_S_YY_ENGLISH);
        DATE_FORMATTER_MAP.put("[$-409]dd\\-mmm\\-yy;@", FORMAT_DD_S_MMM_S_YY_ENGLISH);
        DATE_FORMATTER_MAP.put("[$-409]mmm\\-yy;@", FORMAT_MMM_S_YY_ENGLISH);
        DATE_FORMATTER_MAP.put("[$-409]mmmm\\-yy;@", FORMAT_MMMM_S_YY_ENGLISH);
        DATE_FORMATTER_MAP.put("[$-409]mmmmm;@", FORMAT_MMM_ENGLISH);
        DATE_FORMATTER_MAP.put("[$-409]mmmmm\\-yy;@", FORMAT_MMM_S_YY_ENGLISH);
        DATE_FORMATTER_MAP.put("yyyy\"年\"m\"月\"", FORMAT_YYYY_NIAM_M_YUE);
        DATE_FORMATTER_MAP.put("m\"月\"d\"日\"", FORMAT_M_YUE_D_RI);
        //time
        DATE_FORMATTER_MAP.put("[$-F400]h:mm:ss\\ AM/PM", FORMAT_H_MM_SS);
        DATE_FORMATTER_MAP.put("h:mm;@", FORMAT_H_MM);
        DATE_FORMATTER_MAP.put("[$-409]h:mm\\ AM/PM;@", FORMAT_H_MM_A);
        DATE_FORMATTER_MAP.put("h:mm:ss;@", FORMAT_H_MM_SS);
        DATE_FORMATTER_MAP.put("[$-409]h:mm:ss\\ AM/PM;@", FORMAT_H_MM_SS_A);
        DATE_FORMATTER_MAP.put("h\"时\"mm\"分\";@", FORMAT_H_SHI_MM_FEN);
        DATE_FORMATTER_MAP.put("h\"时\"mm\"分\"ss\"秒\";@", FORMAT_H_SHI_MM_FEN_SS_MIAO);
        DATE_FORMATTER_MAP.put("上午/下午h\"时\"mm\"分\";@", FORMAT_A_H_SHI_MM_FEN);
        DATE_FORMATTER_MAP.put("上午/下午h\"时\"mm\"分\"ss\"秒\";@", FORMAT_A_H_SHI_MM_FEN_SS_MIAO);
        DATE_FORMATTER_MAP.put("[DBNum1][$-804]h\"时\"mm\"分\";@", FORMAT_H_SHI_MM_FEN);
        DATE_FORMATTER_MAP.put("[DBNum1][$-804]上午/下午h\"时\"mm\"分\";@", FORMAT_A_H_SHI_MM_FEN);
        DATE_FORMATTER_MAP.put("上午/下午h\"时\"mm\"分\"", FORMAT_A_H_SHI_MM_FEN);
        DATE_FORMATTER_MAP.put("上午/下午h\"时\"mm\"分\"ss\"秒\"", FORMAT_A_H_SHI_MM_FEN_SS_MIAO);
        //BuiltinFormats
        DATE_FORMATTER_MAP.put("reserved-0x1F", FORMAT_YYYY_NIAN_M_YUE_D_RI);
        DATE_FORMATTER_MAP.put("reserved-0x1E", FORMAT_M_D_YY);
        DATE_FORMATTER_MAP.put("d-mmm-yy", FORMAT_DD_S_MMM_S_YY_ENGLISH);
        DATE_FORMATTER_MAP.put("d-mmm", FORMAT_D_S_MMM_ENGLISH);
        DATE_FORMATTER_MAP.put("mmm-yy", FORMAT_MMM_S_YY_ENGLISH);
        DATE_FORMATTER_MAP.put("h:mm AM/PM", FORMAT_H_MM_A);
        DATE_FORMATTER_MAP.put("h:mm:ss AM/PM", FORMAT_H_MM_SS_A);
        DATE_FORMATTER_MAP.put("h:mm", FORMAT_H_MM);
        DATE_FORMATTER_MAP.put("h:mm:ss", FORMAT_H_MM_SS);
        DATE_FORMATTER_MAP.put("reserved-0x20", FORMAT_H_SHI_MM_FEN);
        DATE_FORMATTER_MAP.put("reserved-0x21", FORMAT_H_SHI_MM_FEN_SS_MIAO);
        DATE_FORMATTER_MAP.put("m/d/yy h:mm", FORMAT_TIME_H_MM_24);
        DATE_FORMATTER_MAP.put("mm:ss", FORMAT_MM_SS);
        DATE_FORMATTER_MAP.put("mm:ss.0", FORMAT_MM_SS_S);
        DATE_FORMATTER_MAP.put("[h]:mm:ss", FORMAT_H_MM_SS);

    }

    private final static String[] EXT_FORMATS = {
        //日期
        "yyyy/m/d;@",
        "[$-F800]dddd\\,\\ mmmm\\ dd\\,\\ yyyy",
        "m/d/yy;@",
        "mm/dd/yy;@",
        "[DBNum1][$-804]yyyy\"年\"m\"月\"d\"日\";@",
        "[DBNum1][$-804]yyyy\"年\"m\"月\";@",
        "[DBNum1][$-804]m\"月\"d\"日\";@",
        "yyyy\"年\"m\"月\"d\"日\";@",
        "yyyy\"年\"m\"月\";@",
        "m\"月\"d\"日\";@",
        "[$-804]aaaa;@",
        "[$-804]aaa;@",
        "[$-409]yyyy/m/d\\ h:mm\\ AM/PM;@",
        "yyyy/m/d\\ h:mm;@",
        "yy/m/d;@",
        "m/d;@",
        "[$-409]d\\-mmm;@",
        "[$-409]d\\-mmm\\-yy;@",
        "[$-409]dd\\-mmm\\-yy;@",
        "[$-409]mmm\\-yy;@",
        "[$-409]mmmm\\-yy;@",
        "[$-409]mmmmm;@",
        "[$-409]mmmmm\\-yy;@",
        "[$-409]mmmmm;@",
        "[$-409]mmmmm\\-yy;@",
        "yyyy\"年\"m\"月\"",
        "m\"月\"d\"日\"",
        //时间
        "[$-F400]h:mm:ss\\ AM/PM",
        "h:mm;@",
        "[$-409]h:mm\\ AM/PM;@",
        "h:mm:ss;@",
        "[$-409]h:mm:ss\\ AM/PM;@",
        "h\"时\"mm\"分\";@",
        "h\"时\"mm\"分\"ss\"秒\";@",
        "上午/下午h\"时\"mm\"分\";@",
        "上午/下午h\"时\"mm\"分\"ss\"秒\";@",
        "[DBNum1][$-804]h\"时\"mm\"分\";@",
        "[DBNum1][$-804]上午/下午h\"时\"mm\"分\";@",
        "上午/下午h\"时\"mm\"分\"",
        "上午/下午h\"时\"mm\"分\"ss\"秒\""

    };

    /**
     * 根据索引获取自定义格式
     * @param index
     * @return
     */
    public static String getBuiltinFormat(int index) {
        if (index < 0 || index >= EXT_FORMATS.length) {
            return null;
        }
        return EXT_FORMATS[index];
    }

    /**
     * 匹配自定义格式并返回索引
     * @param fmt
     * @return
     */
    public static int getBuiltinFormat(String fmt) {
        int i = -1;
        for (String f : EXT_FORMATS) {
            i++;
            if (f.equals(fmt)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 校验格式是否为内置格式
     * @param fmt
     * @return
     */
    public static boolean isBuiltinFormat(String fmt) {
        fmt = "TEXT".equalsIgnoreCase(fmt) ? "@" : fmt;
        for (String f : BuiltinFormats.getAll()) {
            if (f.equals(fmt)) {
                return true;
            }
        }
        for (String f : EXT_FORMATS) {
            if (f.equals(fmt)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 匹配自定义格式并返回SimpleDateFormat
     * @param cell
     * @return
     */
    public static SimpleDateFormat getDateFormatter(Cell cell) {
        SimpleDateFormat formatter = DATE_FORMATTER_MAP.get(cell.getCellStyle().getDataFormatString());
        return null == formatter ? EXT_DATE_FORMATTER_MAP.get(Integer.valueOf(cell.getCellStyle().getDataFormat())) : formatter;
    }

}
