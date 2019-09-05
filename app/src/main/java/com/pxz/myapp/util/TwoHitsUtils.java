package com.pxz.myapp.util;

import android.view.View;

import java.util.Calendar;

/**
 * 类说明：检测两次点击
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 15:57
 */
public abstract class TwoHitsUtils implements View.OnClickListener{
    public static long lastClick = 0L;
    private static final int THRESHOLD = 2000;
    private long lastClickTime = 0L;
    public static final int MIN_CLICK_DELAY_TIME = 1000;

    /**
     * 双击退出检测
     */
    public static boolean check() {
        long now = System.currentTimeMillis();
        boolean b = now - lastClick < THRESHOLD;
        lastClick = now;
        return b;
    }

    /**
     * 防止两次点击
     */
    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);
}
