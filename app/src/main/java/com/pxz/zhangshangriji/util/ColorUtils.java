package com.pxz.zhangshangriji.util;

import android.graphics.Color;

import java.util.Random;

/**
 * 类说明：颜色工具类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 13:15
 */
public class ColorUtils {
    public static Random mRandom=new Random();
    public static int randomColor() {
        return Color.rgb(mRandom.nextInt(255),mRandom.nextInt(255),mRandom.nextInt(255));
    }
}
