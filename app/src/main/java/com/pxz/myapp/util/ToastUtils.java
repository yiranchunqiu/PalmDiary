package com.pxz.myapp.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 类说明：吐司工具类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/16 10:00
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 短时间吐司
     */
    public static void showShort(Context context, int resourceID) {
        show(context, resourceID, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间吐司
     */
    public static void showShort(Context context, String text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间吐司
     */
    public static void showLong(Context context, int resourceID) {
        show(context, resourceID, Toast.LENGTH_LONG);
    }

    /**
     * 短时间吐司
     */
    public static void showLong(Context context, String text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    /**
     * 自定义时长吐司
     */
    public static void show(Context context, Integer resourceID, int duration) {
        // 用于显示的文字
        String text = context.getResources().getString(resourceID);
        show(context, text, duration);
    }

    /**
     * 自定义时长吐司
     */
    public static void show(final Context context, final String text, final int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }
}