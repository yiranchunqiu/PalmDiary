package com.pxz.zhangshangriji.adapter.rv;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.pxz.zhangshangriji.R;
import com.pxz.zhangshangriji.adapter.base.CommonAdapter;
import com.pxz.zhangshangriji.adapter.base.CommonHolder;
import com.pxz.zhangshangriji.bean.WeatherDateInfo;
import com.pxz.zhangshangriji.util.app.WeatherUtils;

import java.util.List;

/**
 * 类说明：天数时间实体类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/24 10:18
 */
public class WeatherDateAdapter extends CommonAdapter<WeatherDateInfo> {
    public WeatherDateAdapter(List<WeatherDateInfo> datas, Context context) {
        super(datas, context, R.layout.item_weather_date);
    }

    @Override
    public void bindHolder(CommonHolder holder, WeatherDateInfo weatherDateInfo) {
        TextView tvItemTime = holder.getView(R.id.tv_item_time);
        ImageView ivItemWeather = holder.getView(R.id.iv_item_weather);
        TextView tvItemTemperature = holder.getView(R.id.tv_item_temperature);
        tvItemTime.setText(weatherDateInfo.getTime().substring(0,10));
        ivItemWeather.setImageResource(WeatherUtils.weatherIconUtils(weatherDateInfo.getWeather()));
        tvItemTemperature.setText(weatherDateInfo.getTemperatureFrom()+"/"+weatherDateInfo.getTemperatureTo());
    }
}