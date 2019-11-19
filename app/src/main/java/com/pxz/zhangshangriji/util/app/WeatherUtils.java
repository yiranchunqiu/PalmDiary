package com.pxz.zhangshangriji.util.app;

import android.widget.ImageView;

import com.pxz.zhangshangriji.R;

/**
 * 类说明：天气判断工具类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/23 10:47
 */
public class WeatherUtils {
    /**
     * {
     * "weatherinfo": [
     * {
     * "code": 0,
     * "wea": "晴"
     * },
     * {
     * "code": 1,
     * "wea": "多云"
     * },
     * {
     * "code": 2,
     * "wea": "阴"
     * },
     * {
     * "code": 3,
     * "wea": "阵雨"
     * },
     * {
     * "code": 4,
     * "wea": "雷阵雨"
     * },
     * {
     * "code": 5,
     * "wea": "雷阵雨并伴有冰雹"
     * },
     * {
     * "code": 6,
     * "wea": "雨夹雪"
     * },
     * {
     * "code": 7,
     * "wea": "小雨"
     * },
     * {
     * "code": 8,
     * "wea": "中雨"
     * },
     * {
     * "code": 9,
     * "wea": "大雨"
     * },
     * {
     * "code": 10,
     * "wea": "暴雨"
     * },
     * {
     * "code": 11,
     * "wea": "大暴雨"
     * },
     * {
     * "code": 12,
     * "wea": "特大暴雨"
     * },
     * {
     * "code": 13,
     * "wea": "阵雪"
     * },
     * {
     * "code": 14,
     * "wea": "小雪"
     * },
     * {
     * "code": 15,
     * "wea": "中雪"
     * },
     * {
     * "code": 16,
     * "wea": "大雪"
     * },
     * {
     * "code": 17,
     * "wea": "暴雪"
     * },
     * {
     * "code": 18,
     * "wea": "雾"
     * },
     * {
     * "code": 19,
     * "wea": "冻雨"
     * },
     * {
     * "code": 20,
     * "wea": "沙尘暴"
     * },
     * {
     * "code": 21,
     * "wea": "小雨-中雨"
     * },
     * {
     * "code": 22,
     * "wea": "中雨-大雨"
     * },
     * {
     * "code": 23,
     * "wea": "大雨-暴雨"
     * },
     * {
     * "code": 24,
     * "wea": "暴雨-大暴雨"
     * },
     * {
     * "code": 25,
     * "wea": "大暴雨-特大暴雨"
     * },
     * {
     * "code": 26,
     * "wea": "小雪-中雪"
     * },
     * {
     * "code": 27,
     * "wea": "中雪-大雪"
     * },
     * {
     * "code": 28,
     * "wea": "大雪-暴雪"
     * },
     * {
     * "code": 29,
     * "wea": "浮沉"
     * },
     * {
     * "code": 30,
     * "wea": "扬沙"
     * },
     * {
     * "code": 31,
     * "wea": "强沙尘暴"
     * },
     * {
     * "code": 32,
     * "wea": "飑"
     * },
     * {
     * "code": 33,
     * "wea": "龙卷风"
     * },
     * {
     * "code": 34,
     * "wea": "若高吹雪"
     * },
     * {
     * "code": 35,
     * "wea": "轻雾"
     * },
     * {
     * "code": 53,
     * "wea": "霾"
     * },
     * {
     * "code": 99,
     * "wea": "未知"
     * }
     * ]
     * }
     */
    public static String weatherUtils(String weather, ImageView imageView) {
        String weatherType = "未知";
        switch (weather) {
            case "0":
                weatherType = "晴";
                imageView.setImageResource(R.mipmap.clear);
                break;
            case "1":
                weatherType = "多云";
                imageView.setImageResource(R.mipmap.cloudy);
                break;
            case "2":
                weatherType = "阴";
                imageView.setImageResource(R.mipmap.overcast);
                break;
            case "3":
                weatherType = "阵雨";
                imageView.setImageResource(R.mipmap.shower);
                break;
            case "4":
                weatherType = "雷阵雨";
                imageView.setImageResource(R.mipmap.freezing_rain);
                break;
            case "5":
                weatherType = "雷阵雨并伴有冰雹";
                imageView.setImageResource(R.mipmap.hail);
                break;
            case "6":
                weatherType = "雨夹雪";
                imageView.setImageResource(R.mipmap.sleet);
                break;
            case "7":
                weatherType = "小雨";
                imageView.setImageResource(R.mipmap.light_rain);
                break;
            case "8":
                weatherType = "中雨";
                imageView.setImageResource(R.mipmap.rain);
                break;
            case "9":
                weatherType = "大雨";
                imageView.setImageResource(R.mipmap.rainstorm);
                break;
            case "10":
                weatherType = "暴雨";
                imageView.setImageResource(R.mipmap.heavy_rain);
                break;
            case "11":
                weatherType = "大暴雨";
                imageView.setImageResource(R.mipmap.heavy_rain);
                break;
            case "12":
                weatherType = "特大暴雨";
                imageView.setImageResource(R.mipmap.heavy_rain);
                break;
            case "13":
                weatherType = "阵雪";
                imageView.setImageResource(R.mipmap.snow_shower);
                break;
            case "14":
                weatherType = "小雪";
                imageView.setImageResource(R.mipmap.light_snow);
                break;
            case "15":
                weatherType = "中雪";
                imageView.setImageResource(R.mipmap.snow);
                break;
            case "16":
                weatherType = "大雪";
                imageView.setImageResource(R.mipmap.scattered_snow_showers);
                break;
            case "17":
                weatherType = "暴雪";
                imageView.setImageResource(R.mipmap.blizzard);
                break;
            case "18":
                weatherType = "雾";
                imageView.setImageResource(R.mipmap.foggy);
                break;
            case "19":
                weatherType = "冻雨";
                imageView.setImageResource(R.mipmap.freezing_rain);
                break;
            case "20":
                weatherType = "沙城暴";
                imageView.setImageResource(R.mipmap.blowing_sand);
                break;
            case "21":
                weatherType = "小雨-中雨";
                imageView.setImageResource(R.mipmap.rain);
                break;
            case "22":
                weatherType = "中雨-大雨";
                imageView.setImageResource(R.mipmap.rainstorm);
                break;
            case "23":
                weatherType = "大雨-暴雨";
                imageView.setImageResource(R.mipmap.heavy_rain);
                break;
            case "24":
                weatherType = "暴雨-大暴雨";
                imageView.setImageResource(R.mipmap.heavy_rain);
                break;
            case "25":
                weatherType = "大暴雨-特大暴雨";
                imageView.setImageResource(R.mipmap.heavy_rain);
                break;
            case "26":
                weatherType = "小雪-中雪";
                imageView.setImageResource(R.mipmap.light_snow);
                break;
            case "27":
                weatherType = "中雪-大雪";
                imageView.setImageResource(R.mipmap.snow);
                break;
            case "28":
                weatherType = "大雪-暴雪";
                imageView.setImageResource(R.mipmap.blizzard);
                break;
            case "29":
                weatherType = "沉浮";
                imageView.setImageResource(R.mipmap.blowing_sand);
                break;
            case "30":
                weatherType = "扬沙";
                imageView.setImageResource(R.mipmap.blowing_sand);
                break;
            case "31":
                weatherType = "强沙城暴";
                imageView.setImageResource(R.mipmap.tornado);
                break;
            case "32":
                weatherType = "飑";
                imageView.setImageResource(R.mipmap.tornado);
                break;
            case "33":
                weatherType = "龙卷风";
                imageView.setImageResource(R.mipmap.tornado);
                break;
            case "34":
                weatherType = "若高吹雪";
                imageView.setImageResource(R.mipmap.light_snow);
                break;
            case "35":
                weatherType = "轻雾";
                imageView.setImageResource(R.mipmap.foggy);
                break;
            case "53":
                weatherType = "霾";
                imageView.setImageResource(R.mipmap.haze);
                break;
            case "99":
                weatherType = "未知";
                imageView.setImageResource(R.mipmap.unknown);
                break;
            default:
                weatherType = "未知";
                imageView.setImageResource(R.mipmap.unknown);
                break;
        }
        return weatherType;
    }

    /**
     * 返回天气图片id
     *
     * @param weather 字段
     * @return 天气图片id
     */
    public static int weatherIconUtils(String weather) {
        int weatherIcon = R.mipmap.unknown;
        switch (weather) {
            case "0":
                weatherIcon = R.mipmap.clear;
                break;
            case "1":
                weatherIcon = R.mipmap.cloudy;
                break;
            case "2":
                weatherIcon = R.mipmap.overcast;
                break;
            case "3":
                weatherIcon = R.mipmap.shower;
                break;
            case "4":
                weatherIcon = R.mipmap.freezing_rain;
                break;
            case "5":
                weatherIcon = R.mipmap.hail;
                break;
            case "6":
                weatherIcon = R.mipmap.sleet;
                break;
            case "7":
                weatherIcon = R.mipmap.light_rain;
                break;
            case "8":
                weatherIcon = R.mipmap.rain;
                break;
            case "9":
                weatherIcon = R.mipmap.rainstorm;
                break;
            case "10":
                weatherIcon = R.mipmap.heavy_rain;
                break;
            case "11":
                weatherIcon = R.mipmap.heavy_rain;
                break;
            case "12":
                weatherIcon = R.mipmap.heavy_rain;
                break;
            case "13":
                weatherIcon = R.mipmap.snow_shower;
                break;
            case "14":
                weatherIcon = R.mipmap.light_snow;
                break;
            case "15":
                weatherIcon = R.mipmap.snow;
                break;
            case "16":
                weatherIcon = R.mipmap.scattered_snow_showers;
                break;
            case "17":
                weatherIcon = R.mipmap.blizzard;
                break;
            case "18":
                weatherIcon = R.mipmap.foggy;
                break;
            case "19":
                weatherIcon = R.mipmap.freezing_rain;
                break;
            case "20":
                weatherIcon = R.mipmap.blowing_sand;
                break;
            case "21":
                weatherIcon = R.mipmap.rain;
                break;
            case "22":
                weatherIcon = R.mipmap.rainstorm;
                break;
            case "23":
                weatherIcon = R.mipmap.heavy_rain;
                break;
            case "24":
                weatherIcon = R.mipmap.heavy_rain;
                break;
            case "25":
                weatherIcon = R.mipmap.heavy_rain;
                break;
            case "26":
                weatherIcon = R.mipmap.light_snow;
                break;
            case "27":
                weatherIcon = R.mipmap.snow;
                break;
            case "28":
                weatherIcon = R.mipmap.blizzard;
                break;
            case "29":
                weatherIcon = R.mipmap.blowing_sand;
                break;
            case "30":
                weatherIcon = R.mipmap.blowing_sand;
                break;
            case "31":
                weatherIcon = R.mipmap.tornado;
                break;
            case "32":
                weatherIcon = R.mipmap.tornado;
                break;
            case "33":
                weatherIcon = R.mipmap.tornado;
                break;
            case "34":
                weatherIcon = R.mipmap.light_snow;
                break;
            case "35":
                weatherIcon = R.mipmap.foggy;
                break;
            case "53":
                weatherIcon = R.mipmap.haze;
                break;
            case "99":
                weatherIcon = R.mipmap.unknown;
                break;
            default:
                weatherIcon = R.mipmap.unknown;
                break;
        }
        return weatherIcon;
    }

    /**
     * 返回天气信息
     *
     * @param weather 字段
     * @return 天气信息
     */
    public static String weatherTextUtils(String weather) {
        String weatherType = "未知";
        switch (weather) {
            case "0":
                weatherType = "晴";
                break;
            case "1":
                weatherType = "多云";
                break;
            case "2":
                weatherType = "阴";
                break;
            case "3":
                weatherType = "阵雨";
                break;
            case "4":
                weatherType = "雷阵雨";
                break;
            case "5":
                weatherType = "雷阵雨并伴有冰雹";
                break;
            case "6":
                weatherType = "雨夹雪";
                break;
            case "7":
                weatherType = "小雨";
                break;
            case "8":
                weatherType = "中雨";
                break;
            case "9":
                weatherType = "大雨";
                break;
            case "10":
                weatherType = "暴雨";
                break;
            case "11":
                weatherType = "大暴雨";
                break;
            case "12":
                weatherType = "特大暴雨";
                break;
            case "13":
                weatherType = "阵雪";
                break;
            case "14":
                weatherType = "小雪";
                break;
            case "15":
                weatherType = "中雪";
                break;
            case "16":
                weatherType = "大雪";
                break;
            case "17":
                weatherType = "暴雪";
                break;
            case "18":
                weatherType = "雾";
                break;
            case "19":
                weatherType = "冻雨";
                break;
            case "20":
                weatherType = "沙城暴";
                break;
            case "21":
                weatherType = "小雨-中雨";
                break;
            case "22":
                weatherType = "中雨-大雨";
                break;
            case "23":
                weatherType = "大雨-暴雨";
                break;
            case "24":
                weatherType = "暴雨-大暴雨";
                break;
            case "25":
                weatherType = "大暴雨-特大暴雨";
                break;
            case "26":
                weatherType = "小雪-中雪";
                break;
            case "27":
                weatherType = "中雪-大雪";
                break;
            case "28":
                weatherType = "大雪-暴雪";
                break;
            case "29":
                weatherType = "沉浮";
                break;
            case "30":
                weatherType = "扬沙";
                break;
            case "31":
                weatherType = "强沙城暴";
                break;
            case "32":
                weatherType = "飑";
                break;
            case "33":
                weatherType = "龙卷风";
                break;
            case "34":
                weatherType = "若高吹雪";
                break;
            case "35":
                weatherType = "轻雾";
                break;
            case "53":
                weatherType = "霾";
                break;
            case "99":
                weatherType = "未知";
                break;
            default:
                weatherType = "未知";
                break;
        }
        return weatherType;
    }
}