package com.pxz.palmdiary.util;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;

/**
 * 类说明：log日志
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 10:07
 */
public class LogUtils {
    /**
     * debug开关
     */
    public static boolean D = true;
    /**
     * info开关
     */
    public static boolean I = true;
    /**
     * error开关
     */
    public static boolean E = true;
    /**
     * 起始执行时间
     */
    public static long startLogTimeInMillis = 0;

    /**
     * debug日志
     *
     * @param tag     标记
     * @param message 描述
     */
    public static void d(String tag, String message) {
        if (D) {
            int max_str_length = 3001;
            //大于3001时
            while (message.length() > max_str_length) {
                Log.d(tag, message.substring(0, max_str_length));
                message = message.substring(max_str_length);
            }
            //剩余部分
            Log.d(tag, message);
        }
    }

    /**
     * debug日志
     *
     * @param context 上下文
     * @param message 描述
     */
    public static void d(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        d(tag, message);
    }

    /**
     * debug日志
     *
     * @param clazz   类
     * @param message 描述
     */
    public static void d(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        d(tag, message);
    }

    /**
     * info日志
     *
     * @param tag     标记
     * @param message 描述
     */
    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    /**
     * info日志
     *
     * @param context 上下文
     * @param message 描述
     */
    public static void i(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        i(tag, message);
    }

    /**
     * info日志
     *
     * @param clazz   类
     * @param message 描述
     */
    public static void i(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        i(tag, message);
    }

    /**
     * error日志
     *
     * @param tag     标记
     * @param message 描述
     */
    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    /**
     * error日志
     *
     * @param context 上下文
     * @param message 描述
     */
    public static void e(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        e(tag, message);
    }

    /**
     * error日志
     *
     * @param clazz   类
     * @param message 描述
     */
    public static void e(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        e(tag, message);
    }

    /**
     * 记录当前时间毫秒
     *
     * @param tag 描述
     */
    public static void prepareLog(String tag) {
        Calendar current = Calendar.getInstance();
        startLogTimeInMillis = current.getTimeInMillis();
        LogUtils.d(tag, "日志计时开始：" + startLogTimeInMillis);
    }

    /**
     * 记录当前时间毫秒
     *
     * @param context 上下文
     */
    public static void prepareLog(Context context) {
        String tag = context.getClass().getSimpleName();
        prepareLog(tag);
    }

    /**
     * 记录当前时间毫秒
     *
     * @param clazz 类
     */
    public static void prepareLog(Class<?> clazz) {
        String tag = clazz.getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param tag       标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(String tag, String message, boolean printTime) {
        Calendar current = Calendar.getInstance();
        long endLogTimeInMillis = current.getTimeInMillis();
        LogUtils.d(tag, message + ":" + (endLogTimeInMillis - startLogTimeInMillis) + "ms");
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Context context, String message, boolean printTime) {
        String tag = context.getClass().getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param clazz     标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Class<?> clazz, String message, boolean printTime) {
        String tag = clazz.getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * debug日志的开关
     */
    public static void debug(boolean d) {
        D = d;
    }

    /**
     * info日志的开关
     */
    public static void info(boolean i) {
        I = i;
    }

    /**
     * error日志的开关
     */
    public static void error(boolean e) {
        E = e;
    }

    /**
     * 设置日志的开关
     */
    public static void setVerbose(boolean d, boolean i, boolean e) {
        D = d;
        I = i;
        E = e;
    }

    /**
     * 打开所有日志，默认全打开
     */
    public static void openAll() {
        D = true;
        I = true;
        E = true;
    }

    /**
     * 关闭所有日志
     */
    public static void closeAll() {
        D = false;
        I = false;
        E = false;
    }
}