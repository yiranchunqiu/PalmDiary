package com.pxz.palmdiary.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.pxz.palmdiary.R;
import com.pxz.palmdiary.activity.base.BaseActivity;
import com.pxz.palmdiary.adapter.rv.WeatherDateAdapter;
import com.pxz.palmdiary.adapter.rv.WeatherHoursAdapter;
import com.pxz.palmdiary.api.network.HttpMethods;
import com.pxz.palmdiary.api.subscribers.ProgressSubscriber;
import com.pxz.palmdiary.api.subscribers.SubscriberOnNextListener;
import com.pxz.palmdiary.bean.WeatherDateInfo;
import com.pxz.palmdiary.bean.WeatherHoursInfo;
import com.pxz.palmdiary.bean.app.XiaomiWeatherRequest;
import com.pxz.palmdiary.bean.app.XiaomiWeatherResponse;
import com.pxz.palmdiary.view.SemicircleProgressView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 类说明：天气页面（弃用）
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 16:38
 */
@Deprecated
public class WeatherActivity extends BaseActivity {
    @Bind(R.id.rl_tilte)
    RelativeLayout rlTilte;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.rv_weather_hours)
    RecyclerView rvWeatherHours;
    @Bind(R.id.rv_weather_date)
    RecyclerView rvWeatherDate;
    @Bind(R.id.progress0)
    SemicircleProgressView progress0;
    @Bind(R.id.tv_pm10)
    TextView tvPm10;
    @Bind(R.id.tv_pm25)
    TextView tvPm25;
    @Bind(R.id.tv_no2)
    TextView tvNo2;
    @Bind(R.id.tv_so2)
    TextView tvSo2;
    @Bind(R.id.tv_o2)
    TextView tvO2;
    @Bind(R.id.tv_co)
    TextView tvCo;
    @Bind(R.id.progress1)
    SemicircleProgressView progress1;
    @Bind(R.id.tv_temperature)
    TextView tvTemperature;
    @Bind(R.id.tv_feels_like)
    TextView tvFeelsLike;
    @Bind(R.id.tv_pressure)
    TextView tvPressure;
    @Bind(R.id.tv_visibility)
    TextView tvVisibility;
    @Bind(R.id.tv_dirc)
    TextView tvDirc;
    @Bind(R.id.tv_speed)
    TextView tvSpeed;
    /**
     * 小时时间adapter
     */
    private WeatherHoursAdapter weatherHoursAdapter;
    /**
     * 小时数据
     */
    private List<WeatherHoursInfo> weatherHoursInfos = new ArrayList<>();
    /**
     * 天数时间实体类
     */
    private WeatherDateAdapter weatherDateAdapter;
    /**
     * 天数数据
     */
    private List<WeatherDateInfo> weatherDateInfos = new ArrayList<>();
    /**
     * 是否需要loading
     */
    private boolean isLoading = true;

    @Override
    protected int getLayout() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initSetStatusBar() {
        super.initSetStatusBar();
        // 设置状态栏
        StatusBarUtil.setTranslucent(mActivity, 0);
    }

    @Override
    protected void initData() {
        super.initData();
        ivBack.setVisibility(View.VISIBLE);
        // 标题
        tvTitle.setText("合肥");
        // 设置标题颜色
        rlTilte.setBackgroundColor(getResources().getColor(R.color.transparent));
        // 设置 Header 为 Material风格
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
        // 设置主题颜色
        smartRefreshLayout.setPrimaryColorsId(R.color.transparent, R.color.white);
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        // 小时
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvWeatherHours.setLayoutManager(linearLayoutManager);
        weatherHoursAdapter = new WeatherHoursAdapter(weatherHoursInfos,mContext);
        rvWeatherHours.setAdapter(weatherHoursAdapter);
        // 天数
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mActivity);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rvWeatherDate.setLayoutManager(linearLayoutManager1);
        weatherDateAdapter = new WeatherDateAdapter( weatherDateInfos,mActivity);
        rvWeatherDate.setAdapter(weatherDateAdapter);
    }

    @Override
    protected void initSetListener() {
        super.initSetListener();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isLoading = false;
                httpWeather();
            }
        });
    }

    @Override
    protected void initHttp() {
        super.initHttp();
        httpWeather();
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 天气请求
     */
    private void httpWeather() {
        XiaomiWeatherRequest xiaomiWeatherRequest = new XiaomiWeatherRequest();
        xiaomiWeatherRequest.setLatitude("0");
        xiaomiWeatherRequest.setLongitude("0");
        xiaomiWeatherRequest.setLocationKey("weathercn:101220101");
        xiaomiWeatherRequest.setDays("1");
        xiaomiWeatherRequest.setIsGlobal("false");
        xiaomiWeatherRequest.setLocale("zh_cn");
        xiaomiWeatherRequest.setSign("zUFJoAR2ZVrDy1vF3D07");
        xiaomiWeatherRequest.setAppKey("weather20151024");
        HttpMethods.getInstance().weatherXiaomiHttp(new ProgressSubscriber(weatherXiaomiHttpCommitOnNext, mActivity, isLoading),
                xiaomiWeatherRequest.getLatitude(),
                xiaomiWeatherRequest.getLongitude(),
                xiaomiWeatherRequest.getLocationKey(),
                xiaomiWeatherRequest.getDays(),
                xiaomiWeatherRequest.getAppKey(),
                xiaomiWeatherRequest.getSign(),
                xiaomiWeatherRequest.getIsGlobal(),
                xiaomiWeatherRequest.getLocale());
    }

    /**
     *
     */
    public SubscriberOnNextListener weatherXiaomiHttpCommitOnNext = new SubscriberOnNextListener<XiaomiWeatherResponse>() {
        @Override
        public void onNext(XiaomiWeatherResponse baseResponse) {
            //关闭刷新
            smartRefreshLayout.finishRefresh();
            weatherHoursInfos.clear();
            weatherDateInfos.clear();
            for (int i=0;i<23;i++){
                WeatherHoursInfo weatherHoursInfo=new WeatherHoursInfo();
                weatherHoursInfo.setTemperature(baseResponse.forecastHourly.temperature.value.get(i)+baseResponse.forecastHourly.temperature.unit);
                weatherHoursInfo.setTime(baseResponse.forecastHourly.wind.value.get(i).datetime);
                weatherHoursInfo.setWeather(baseResponse.forecastHourly.weather.value.get(i));
                weatherHoursInfos.add(weatherHoursInfo);
            }
            weatherHoursAdapter.notifyDataSetChanged();
            for (int i=0;i<5;i++){
                WeatherDateInfo weatherHoursInfo=new WeatherDateInfo();
                weatherHoursInfo.setTime(baseResponse.forecastDaily.sunRiseSet.value.get(i).from);
                weatherHoursInfo.setWeather(baseResponse.forecastDaily.weather.value.get(i).from);
                weatherHoursInfo.setTemperatureFrom(baseResponse.forecastDaily.temperature.value.get(i).to+baseResponse.forecastDaily.temperature.unit);
                weatherHoursInfo.setTemperatureTo(baseResponse.forecastDaily.temperature.value.get(i).from+baseResponse.forecastDaily.temperature.unit);
                weatherDateInfos.add(weatherHoursInfo);
            }
            weatherDateAdapter.notifyDataSetChanged();
            tvPm10.setText(baseResponse.aqi.pm10);
            tvPm25.setText(baseResponse.aqi.pm25);
            tvNo2.setText(baseResponse.aqi.no2);
            tvSo2.setText(baseResponse.aqi.so2);
            tvO2.setText(baseResponse.aqi.o3);
            tvCo.setText(baseResponse.aqi.co);
            tvTemperature.setText(baseResponse.current.temperature.value+baseResponse.current.temperature.unit);
            tvFeelsLike.setText(baseResponse.current.feelsLike.value+baseResponse.current.feelsLike.unit);
            tvPressure.setText(baseResponse.current.pressure.value+baseResponse.current.pressure.unit);
            tvVisibility.setText(baseResponse.current.visibility.value+baseResponse.current.visibility.unit);
            tvDirc.setText(baseResponse.current.wind.direction.value+baseResponse.current.wind.direction.unit);
            tvSpeed.setText(baseResponse.current.wind.speed.value+baseResponse.current.wind.speed.unit);
            progress0.setSesameValues(Integer.parseInt(baseResponse.aqi.aqi),500);
            progress1.setSesameValues(Integer.parseInt(baseResponse.current.humidity.value),100);
        }

        @Override
        public void onError() {
            //关闭刷新
            smartRefreshLayout.finishRefresh();
        }
    };
}