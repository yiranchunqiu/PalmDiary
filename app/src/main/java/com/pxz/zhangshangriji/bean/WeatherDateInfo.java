package com.pxz.zhangshangriji.bean;

import java.io.Serializable;

/**
 * 类说明：天数时间实体类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/24 10:14
 */
public class WeatherDateInfo implements Serializable{
    /** 温度信息---最低 */
    private String temperatureFrom;
    /** 温度信息---最高 */
    private String temperatureTo;
    /** 天气状况 */
    private String weather;
    /** 时间 */
    private String time;

    public String getTemperatureFrom() {
        return temperatureFrom;
    }

    public void setTemperatureFrom(String temperatureFrom) {
        this.temperatureFrom = temperatureFrom;
    }

    public String getTemperatureTo() {
        return temperatureTo;
    }

    public void setTemperatureTo(String temperatureTo) {
        this.temperatureTo = temperatureTo;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}