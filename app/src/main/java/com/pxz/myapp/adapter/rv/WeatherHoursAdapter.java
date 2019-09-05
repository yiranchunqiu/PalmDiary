package com.pxz.myapp.adapter.rv;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.pxz.myapp.R;
import com.pxz.myapp.adapter.base.CommonAdapter;
import com.pxz.myapp.adapter.base.CommonHolder;
import com.pxz.myapp.bean.WeatherHoursInfo;
import com.pxz.myapp.util.app.WeatherUtils;

import java.util.List;

/**
 * 类说明：小时时间adapter
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/24 09:55
 */
public class WeatherHoursAdapter extends CommonAdapter<WeatherHoursInfo> {
    public WeatherHoursAdapter(List<WeatherHoursInfo> datas, Context context) {
        super(datas, context, R.layout.item_weather_hours);
    }

    @Override
    public void bindHolder(CommonHolder holder, WeatherHoursInfo weatherHoursInfo) {
        TextView tvItemTime = holder.getView(R.id.tv_item_time);
        ImageView ivItemWeather = holder.getView(R.id.iv_item_weather);
        TextView tvItemTemperature = holder.getView(R.id.tv_item_temperature);
        tvItemTime.setText(weatherHoursInfo.getTime().substring(11,16));
        ivItemWeather.setImageResource(WeatherUtils.weatherIconUtils(weatherHoursInfo.getWeather()));
        tvItemTemperature.setText(weatherHoursInfo.getTemperature());
    }
}