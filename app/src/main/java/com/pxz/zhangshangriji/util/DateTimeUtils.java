package com.pxz.zhangshangriji.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类说明：时间工具类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 15:14
 */
@SuppressLint("SimpleDateFormat")
public class DateTimeUtils {
    private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
    private static SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat simpleDateFormat6 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat simpleDateFormat7 = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat simpleDateFormat8 = new SimpleDateFormat("HH时mm分ss秒");

    /**
     * 获取当前年份
     *
     * @return 返回年份
     */
    public static int getNowYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return 返回月份
     */
    public static int getNowMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return 当前日
     */
    public static int getNowDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前小时
     *
     * @return 当前日
     */
    public static String getNowTime() {
        return formatDate2String(getNowDate(),8);
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间昨天日期
     *
     * @return 昨天日期
     */
    public static Date lastDayAgo() {
        return lastDayAgo(getNowDate());
    }

    /**
     * 获取昨天日期
     *
     * @param date 日期
     * @return 昨天日期
     */
    public static Date lastDayAgo(Date date) {
        return dayAgo(date, -7);
    }

    /**
     * 获取前几天或者后几天的日期
     *
     * @param date 日期
     * @param time 天数
     * @return 前time天或者后time天的日期
     */
    public static Date dayAgo(Date date, int time) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, time);
        return c.getTime();
    }

    /**
     * 获取当前时间上个月日期
     *
     * @return 上个月日期
     */
    public static Date lastMonthAgo() {
        return lastMonthAgo(getNowDate());
    }

    /**
     * 获取上个月日期
     *
     * @param date 日期
     * @return 上个月日期
     */
    public static Date lastMonthAgo(Date date) {
        return monthAgo(date, -1);
    }

    /**
     * 获取前几个月或者后几个月的日期
     *
     * @param date 日期
     * @param time 月数
     * @return 前几个月或者后几个月的日期
     */
    public static Date monthAgo(Date date, int time) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, time);
        return c.getTime();
    }

    /**
     * 获取当前时间去年日期
     *
     * @return 去年日期
     */
    public static Date lastYearAgo() {
        return lastYearAgo(getNowDate());
    }

    /**
     * 获取去年日期
     *
     * @param date 日期
     * @return 去年日期
     */
    public static Date lastYearAgo(Date date) {
        return yearAgo(date, -1);
    }

    /**
     * 获取前几个年或者后几个年的日期
     *
     * @param date 日期
     * @param time 年数
     * @return 前几个年或者后几个年的日期
     */
    public static Date yearAgo(Date date, int time) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, time);
        return c.getTime();
    }

    /**
     * 判断今年是否是闰年
     *
     * @return false：不是闰年  true：是闰年
     */
    public static boolean isLeapYear() {
        return isLeapYear(getNowYear());
    }

    /**
     * 判断是否是闰年
     *
     * @param year 年份
     * @return false：不是闰年  true：是闰年
     */
    public static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 将 Date 类型转为时间戳
     *
     * @param date date时间
     * @return 时间戳
     */
    public static long date2Millis(final Date date) {
        return date.getTime();
    }

    /**
     * 将时间戳转为 Date 类型
     *
     * @param millis 时间戳
     * @return date时间
     */
    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }

    /**
     * 格式化今天时间戳
     *
     * @param type 格式化类型
     * @return 格式化之后
     */
    public static String formatNowMillis2String(int type) {
        return formatMillis2String(getNowMills(), type);
    }

    /**
     * 格式化时间
     * （yyyy年MM月dd日）
     * （yyyy年MM月dd日HH时mm分ss秒）
     * （yyyy-MM-dd）
     * （yyyy-MM-dd HH:mm:ss）
     * （yyyyMMdd）
     * （yyyyMMddHHmmss）
     *
     * @param millis 需要格式化的时间戳
     * @param type   格式化类型
     * @return 格式化之后
     */
    public static String formatMillis2String(long millis, int type) {
        switch (type) {
            case 1:
                return simpleDateFormat1.format(new Date(millis));
            case 2:
                return simpleDateFormat2.format(new Date(millis));
            case 3:
                return simpleDateFormat3.format(new Date(millis));
            case 4:
                return simpleDateFormat4.format(new Date(millis));
            case 5:
                return simpleDateFormat5.format(new Date(millis));
            case 6:
                return simpleDateFormat6.format(new Date(millis));
            case 7:
                return simpleDateFormat7.format(new Date(millis));
            default:
                return simpleDateFormat1.format(new Date(millis));
        }
    }

    /**
     * 格式化时间
     * （yyyy年MM月dd日）
     * （yyyy年MM月dd日HH时mm分ss秒）
     * （yyyy-MM-dd）
     * （yyyy-MM-dd HH:mm:ss）
     * （yyyyMMdd）
     * （yyyyMMddHHmmss）
     *
     * @param time 需要格式化的时间
     * @param type 格式化类型
     * @return 格式化之后时间戳
     */
    public static long formatString2Millis(String time, int type) {
        try {
            switch (type) {
                case 1:
                    return simpleDateFormat1.parse(time).getTime();
                case 2:
                    return simpleDateFormat2.parse(time).getTime();
                case 3:
                    return simpleDateFormat3.parse(time).getTime();
                case 4:
                    return simpleDateFormat4.parse(time).getTime();
                case 5:
                    return simpleDateFormat5.parse(time).getTime();
                case 6:
                    return simpleDateFormat6.parse(time).getTime();
                case 7:
                    return simpleDateFormat7.parse(time).getTime();
                default:
                    return simpleDateFormat1.parse(time).getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 格式化今天时间
     *
     * @param type 格式化类型
     * @return 格式化之后
     */
    public static String formatNowDate2String(int type) {
        return formatDate2String(getNowDate(), type);
    }

    /**
     * 格式化时间
     * （yyyy年MM月dd日）
     * （yyyy年MM月dd日HH时mm分ss秒）
     * （yyyy-MM-dd）
     * （yyyy-MM-dd HH:mm:ss）
     * （yyyyMMdd）
     * （yyyyMMddHHmmss）
     *
     * @param date 需要格式化的时间
     * @param type 格式化类型
     * @return 格式化之后
     */
    public static String formatDate2String(Date date, int type) {
        switch (type) {
            case 1:
                return simpleDateFormat1.format(date);
            case 2:
                return simpleDateFormat2.format(date);
            case 3:
                return simpleDateFormat3.format(date);
            case 4:
                return simpleDateFormat4.format(date);
            case 5:
                return simpleDateFormat5.format(date);
            case 6:
                return simpleDateFormat6.format(date);
            case 7:
                return simpleDateFormat7.format(date);
            case 8:
                return simpleDateFormat8.format(date);
            default:
                return simpleDateFormat1.format(date);
        }
    }

    /**
     * 格式化时间
     * （yyyy年MM月dd日）
     * （yyyy年MM月dd日HH时mm分ss秒）
     * （yyyy-MM-dd）
     * （yyyy-MM-dd HH:mm:ss）
     * （yyyyMMdd）
     * （yyyyMMddHHmmss）
     *
     * @param time 需要格式化的时间
     * @param type 格式化类型
     * @return 格式化之后时间date
     */
    public static Date formatString2Date(String time, int type) {
        try {
            switch (type) {
                case 1:
                    return simpleDateFormat1.parse(time);
                case 2:
                    return simpleDateFormat2.parse(time);
                case 3:
                    return simpleDateFormat3.parse(time);
                case 4:
                    return simpleDateFormat4.parse(time);
                case 5:
                    return simpleDateFormat5.parse(time);
                case 6:
                    return simpleDateFormat6.parse(time);
                case 7:
                    return simpleDateFormat7.parse(time);
                default:
                    return simpleDateFormat1.parse(time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格林尼治时间转string
     * @param greenwich 格林尼治时间
     * @return string
     */
    public static String formatGreenwich2String(String greenwich){
        // yyyy-MM-dd'T'HH:mm:ss.SSSZ
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = null;
        try {
            date = df.parse(greenwich);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df2.format(date);
    }

    /**
     * 格林尼治时间转string
     * @param greenwich 格林尼治时间
     * @return string
     */
    public static String formatGreenwich2DateString(String greenwich){
        // yyyy-MM-dd'T'HH:mm:ss.SSSZ
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = null;
        try {
            date = df.parse(greenwich);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        return df2.format(date);
    }

    /**
     * 格林尼治时间转string
     * @param greenwich 格林尼治时间
     * @return string
     */
    public static String formatGreenwich2TimeString(String greenwich){
        // yyyy-MM-dd'T'HH:mm:ss.SSSZ
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = null;
        try {
            date = df.parse(greenwich);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat df2 = new SimpleDateFormat("HH:mm");
        return df2.format(date);
    }
}