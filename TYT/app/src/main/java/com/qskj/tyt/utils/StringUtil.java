package com.qskj.tyt.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * 字符串操作工具类
 * Created by 赵 鑫 on 2015/11/12.
 */
public class StringUtil {

    /**
     * 格式化时间：去除时间中 T 字母
     *
     * @param str
     * @return
     */
    public static String dateRemoveT(String str) {
        if (str != null && !TextUtils.isEmpty(str)) {
            return str.replace("T", " ");
        } else {
            return "";
        }
    }

    /**
     * 将 年月日T时间 转成 年月日
     *
     * @param str
     * @return
     */
    public static String YMDDtoYMD(String str) {
        if (str != null && !TextUtils.isEmpty(str)) {
            String[] ts = str.split("T");
            return ts[0];
        } else {
            return "";
        }
    }

    /**
     * double 双浮点型数据 保留两位小数并格式化成金钱显示的格式 000，000，000.00
     *
     * @param data
     * @return
     */
    public static String numberFormat(double data) {
        return new DecimalFormat("#,###,###,##0.00").format(data);
    }

    /**
     * float 单浮点型数据 保留两位小数并格式化成金钱显示的格式 000，000，000.00
     *
     * @param data
     * @return
     */
    public static String numberFormat(float data) {
        return new DecimalFormat("#,###,###,##0.00").format(data);
    }

    /**
     * int 整型数据 格式化成金钱显示的格式 000，000，000
     *
     * @param data
     * @return
     */
    public static String numberFormat(int data) {
        return new DecimalFormat("#,###,###,###").format(data);
    }

}
