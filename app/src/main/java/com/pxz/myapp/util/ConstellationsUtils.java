package com.pxz.myapp.util;

/**
 * 类说明：星座工具类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/17 18:33
 */
public class ConstellationsUtils {
    public static String[][] constellationstext = {{"摩羯座", "水瓶座"}, {"水瓶座", "双鱼座"}, {"双鱼座", "白羊座"}, {"白羊座", "金牛座"},
            {"金牛座", "双子座"}, {"双子座", "巨蟹座"}, {"巨蟹座", "狮子座"}, {"狮子座", "处女座"},
            {"处女座", "天秤座"}, {"天秤座", "天蝎座"}, {"天蝎座", "射手座"}, {"射手座", "摩羯座"}};
    /**
     * 星座分割时间
     */
    public static int[] date = {20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};

    public static String ConstellationsName(){
        return getConstellations(DateTimeUtils.formatNowDate2String(3));
    }

    /**
     * 星座生成 传进是日期格式为: yyyy-mm-dd
     */
    private static String getConstellations(String time) {
        String[] data = time.split("-");
        int day = date[Integer.parseInt(data[1]) - 1];
        String[] cl1 = constellationstext[Integer.parseInt(data[1]) - 1];
        if (Integer.parseInt(data[2]) >= day) {
            return cl1[1];
        } else {
            return cl1[0];
        }
    }
}