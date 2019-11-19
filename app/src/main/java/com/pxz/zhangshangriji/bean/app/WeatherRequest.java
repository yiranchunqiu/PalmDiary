package com.pxz.zhangshangriji.bean.app;

import java.io.Serializable;

/**
 * 类说明：天气实体类-----请求
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:53
 */
public class WeatherRequest implements Serializable{
    private String area;
    private String areaid;
    private String need3HourForcast;
    private String needAlarm;
    private String needHourData;
    private String needIndex;
    private String needMoreDay;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getNeed3HourForcast() {
        return need3HourForcast;
    }

    public void setNeed3HourForcast(String need3HourForcast) {
        this.need3HourForcast = need3HourForcast;
    }

    public String getNeedAlarm() {
        return needAlarm;
    }

    public void setNeedAlarm(String needAlarm) {
        this.needAlarm = needAlarm;
    }

    public String getNeedHourData() {
        return needHourData;
    }

    public void setNeedHourData(String needHourData) {
        this.needHourData = needHourData;
    }

    public String getNeedIndex() {
        return needIndex;
    }

    public void setNeedIndex(String needIndex) {
        this.needIndex = needIndex;
    }

    public String getNeedMoreDay() {
        return needMoreDay;
    }

    public void setNeedMoreDay(String needMoreDay) {
        this.needMoreDay = needMoreDay;
    }
}