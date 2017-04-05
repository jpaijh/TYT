package com.qskj.tyt.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtil {

    /**
     * 将long类型转化成字符串格式 MM月dd日
     *
     * @param time
     * @return 返回短时间字符串格式MM月dd日
     */
    public static String longDateToStrMD(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将long类型转化成字符串格式 MM月dd日HH:mm:ss
     *
     * @param time
     * @return 返回短时间字符串格式 MM月dd日HH:mm:ss
     */
    public static String longDateToStrMDHMS(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 将long类型转化成字符串格式yyyy年MM月dd日
     *
     * @param time
     * @return 返回短时间字符串格式yyyy年MM月dd日
     */
    public static String longDateToStrYMD(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将long类型转化成字符串格式yyyy年MM月dd日 HH:mm:ss
     *
     * @param time
     * @return 返回短时间字符串格式yyyy年MM月dd日 HH:mm:ss
     */
    public static String longDateToStrYMDHMS(long time) {
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

}
