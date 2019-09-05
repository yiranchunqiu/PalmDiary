package com.pxz.myapp.bean.app;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明：天气实体类-----响应
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 13:33
 */
public class XiaomiWeatherResponse implements Serializable {
    /** 当前天气状况 */
    public Current current;
    /** 今日预测 */
    public ForecastDaily forecastDaily;
    /** 今日小时预警 */
    public ForecastHourly forecastHourly;
    /** 空气相关 */
    public Aqi aqi;
    /** 昨日信息 */
    public Yesterday yesterday;

    /*------------------------------------ 当前天气状况开始 ------------------------------------*/
    /**
     * 体感温度
     */
    public class FeelsLike{
        public String unit;
        public String value;
    }

    /**
     * 相对空气湿度
     */
    public class Humidity{
        public String unit;
        public String value;
    }

    /**
     * 气压
     */
    public class Pressure{
        public String unit;
        public String value;
    }

    /**
     * 能见度
     */
    public class Visibility{
        public String unit;
        public String value;
    }

    /**
     * 风信息
     */
    public class Wind1{
        /** 方向 */
        public Direction1 direction;
        /** 大小 */
        public Speed1 speed;
    }

    /**
     * 风信息--方向
     */
    public class Direction1{
        public String unit;
        public String value ;
    }

    /**
     * 风信息--大小
     */
    public class Speed1{
        public String unit;
        public String value ;
    }

    /**
     * 温度
     */
    public class Temperature1{
        public String unit;
        public String value ;
    }

    /**
     * 当前天气状况
     */
    public class Current{
        /** 体感温度 */
        public FeelsLike feelsLike;
        /** 相对空气湿度 */
        public Humidity humidity;
        /** 气压 */
        public Pressure pressure;
        /** 更新时间 */
        public String pubTime;
        /** 温度 */
        public Temperature1 temperature;
        /**  */
        public String uvIndex;
        /** 能见度 */
        public Visibility visibility;
        /** 天气状况 */
        public String weather;
        /** 风信息 */
        public Wind1 wind;
    }
    /*------------------------------------ 当前天气状况结束 ------------------------------------*/


    /*------------------------------------ 今日预测开始 ------------------------------------*/
    /**
     * 降雨概率
     */
    public class PrecipitationProbability{
        public int status;
        /** 包括今日的五日内降雨概率 */
        public List<String> value;
    }

    /**
     * 日落日出相关
     */
    public class SunRiseSet{
        public int status;
        /** 日落日出时间 */
        public List<SunRiseSet1> value;
    }

    /**
     * 日落日出时间
     */
    public class SunRiseSet1{
        /** 日出时间 */
        public String from;
        /** 日落日出时间 */
        public String to;
    }

    /**
     *  风信息
     */
    public class Wind{
        /** 风信息--方向 */
        public Direction direction;
        /** 风信息--大小 */
        public Speed speed;
    }

    /**
     *  风信息--方向
     */
    public class Direction{
        public int status;
        public String unit;
        public List<Direction2> value;
    }

    public class Direction2{
        /**  */
        public String from;
        /**  */
        public String to;
    }

    /**
     *  风信息--大小
     */
    public class Speed{
        public int status;
        public String unit;
        public List<Speed2> value;
    }

    public class Speed2{
        /**  */
        public String from;
        /**  */
        public String to;
    }

    /**
     * 温度信息
     */
    public class Temperature2{
        public String pubTime;
        public int status;
        public String unit;
        public List<Temperature3> value;
    }

    public class Temperature3{
        /** 最高温度 */
        public String from;
        /** 最低温度 */
        public String to;
    }

    /**
     * 天气状况
     */
    public class Weather2{
        public String pubTime;
        public int status;
        public String unit;
        public List<Weather3> value ;
    }

    /**
     * 天气状况
     */
    public class Weather3{
        /**  */
        public String from;
        /**  */
        public String to;
    }

    /**
     * 空气质量相关
     */
    public class Aqi1{
        public String aqi;
        public String co;
        public String no2;
        public String o3;
        public String pm10;
        public String pm25;
        public String primary;
        /** 更新时间 */
        public String pubTime;
        public String so2;
        public String src;
        public int status;
        /** 包括今日的五日内空气质量指数 */
        public List<String> value;
    }

    /**
     * 今日预测
     */
    public class ForecastDaily{
        /** 空气质量相关 */
        public Aqi1 aqi;
        /** 降雨概率 */
        public PrecipitationProbability precipitationProbability;
        /** 更新时间 */
        public String pubTime;
        /**  */
        public int status;
        /** 日落日出相关 */
        public SunRiseSet sunRiseSet;
        /** 温度信息 */
        public Temperature2 temperature;
        /** 天气状况 */
        public Weather2 weather;
        /** 风信息 */
        public Wind wind;
    }
    /*------------------------------------ 今日预测结束 ------------------------------------*/


    /*------------------------------------ 24小时预测开始 ------------------------------------*/

    /**
     * 温度信息
     */
    public class Temperature{
        public String pubTime;
        public int status;
        public String unit;
        public List<String> value ;
    }

    /**
     * 空气质量相关
     */
    public class Aqi2{
        public String aqi;
        public String co;
        public String no2;
        public String o3;
        public String pm10;
        public String pm25;
        public String primary;
        public String pubTime;
        public String so2;
        public String src;
        public int status;
    }

    /**
     * 天气状况
     */
    public class Weather{
        public String pubTime;
        public int status;
        public List<String> value ;
    }

    /**
     * 风信息
     */
    public class Wind2{
        public int status;
        public List<Value> value;
    }

    /**
     * 风信息
     */
    public class Value{
        /** 时间 */
        public String datetime;
        /** 风向 */
        public String direction;
        /** 风速 */
        public String speed;
    }

    /**
     * 今日小时预警
     */
    public class ForecastHourly{
        /** 空气质量相关 */
        public Aqi2 aqi;
        /**  */
        public String desc;
        /**  */
        public int status;
        /** 温度信息 */
        public Temperature temperature;
        /** 天气状况 */
        public Weather weather;
        /** 风信息 */
        public Wind2 wind;
    }
    /*------------------------------------ 24小时预测结束 ------------------------------------*/

    /*------------------------------------ 昨日信息开始 ------------------------------------*/

    public class Yesterday{
        /**  */
        public String aqi;
        /**  */
        public String date;
        /**  */
        public int status;
        /** 日出时间 */
        public String sunRise;
        /** 日落时间 */
        public String sunSet;
        /**  */
        public String tempMax;
        /**  */
        public String tempMin;
        /**  */
        public String weatherEnd;
        /** 天气状况 */
        public String weatherStart;
        /** 开始风向 */
        public String windDircEnd;
        /** 结束风向 */
        public String windDircStart;
        /** 开始风速 */
        public String windSpeedEnd;
        /** 结束风速 */
        public String windSpeedStart;
    }
    /*------------------------------------ 昨日信息结束 ------------------------------------*/

    /*------------------------------------ 通用开始 ------------------------------------*/

    /**
     * 空气质量相关
     */
    public class Aqi{
        /** co */
        public String co;
        /** co介绍 */
        public String coDesc;
        /** no2 */
        public String no2;
        /** no2介绍 */
        public String no2Desc;
        /** o3 */
        public String o3;
        /** o3介绍 */
        public String o3Desc;
        /** pm10 */
        public String pm10;
        /** pm10介绍 */
        public String pm10Desc;
        /** pm25 */
        public String pm25;
        /** pm25介绍 */
        public String pm25Desc;
        /** so2 */
        public String so2;
        /** so2介绍 */
        public String so2Desc;
        /** 更新时间 */
        public String pubTime;
        /**  */
        public String primary;
        /**  */
        public String aqi;
        /**  */
        public String src;
        /**  */
        public int status;
        /**  */
        public String suggest;
    }
    /*------------------------------------ 通用结束 ------------------------------------*/
}