package com.pxz.myapp.bean;

import java.io.Serializable;

/**
 * 类说明：小时时间实体类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/24 09:53
 */
public class WeatherHoursInfo implements Serializable{
    /** 温度信息 */
    private String temperature;
    /** 天气状况 */
    private String weather;
    /** 时间 */
    private String time;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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