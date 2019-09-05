package com.pxz.myapp.api.network;

import com.pxz.myapp.BuildConfig;
import com.pxz.myapp.application.BaseApplication;
import com.pxz.myapp.bean.app.XiaomiWeatherResponse;
import com.pxz.myapp.util.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.pxz.myapp.constant.AppConstant.DEFAULT_TIMEOUT;
import static com.pxz.myapp.constant.AppConstant.PATH_CACHE;
import static com.pxz.myapp.constant.AppConstant.READ_OR_WRITE_TIMEOUT;
import static com.pxz.myapp.constant.AppConstant.URL_HOST;

/**
 * 类说明：接口调用和处理
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:55
 */
public class HttpMethods {
    /**
     * url
     */
    public static String baseUrl = URL_HOST;
    /**
     * 定义okhttp
     */
    private static OkHttpClient okHttpClient = null;
    /**
     * 定义retrofit
     */
    private static Retrofit retrofit = null;
    /**
     * 定义api
     */
    private static AppApi api = null;

    /**
     * 构造方法私有
     */
    private HttpMethods() {
        init();
    }

    /**
     * HTTP初始化
     */
    private void init() {
        initOkHttp();
        initRetrofit();
        api = retrofit.create(AppApi.class);
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    /**
     * 获取单例
     */
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * OkHttp初始化
     */
    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                if (!NetworkUtils.isNetworkConnected(BaseApplication.getInstance())) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                okhttp3.Response response = chain.proceed(request);
                if (NetworkUtils.isNetworkConnected(BaseApplication.getInstance())) {
                    // 有网络时不缓存,最大保存时长为0
                    int maxAge = 0;
                    response.newBuilder().header("Cache-Control", "public, max-age=" + maxAge).removeHeader("Pragma").build();
                } else {
                    // 无网络时,设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale).removeHeader("Pragma").build();
                }
                return response;
            }
        };
        // 设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        // 这里可以添加其他的拦截器
        builder.cache(cache);
        // 设置head里面appid
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request mRequest = chain.request();
                Request lRequest = mRequest.newBuilder()
                        .header("Authorization", "APPCODE " + "c868a90b992e48678d285ac57c09fa50")
                        .build();
                return chain.proceed(lRequest);
            }
        });
        // 设置超时
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_OR_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(READ_OR_WRITE_TIMEOUT, TimeUnit.SECONDS);
        // 错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    /**
     * 初始化Retrofit
     */
    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * @param o   观测者
     * @param s   订阅者
     * @param <T> 泛型
     * @功能 请求分发(订阅)
     */
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWhenNetworkException())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     */
    private class HttpResultIsFunc<T> implements Func1<T, T> {
        @Override
        public T call(T httpResult) {
            return httpResult;
        }
    }

    /*--------------------------------通用接口开始--------------------------------*/

    /**
     * 小米天气接口
     *
     * @param subscriber
     * @param latitude
     * @param longitude
     * @param locationKey
     * @param days
     * @param appKey
     * @param sign
     * @param isGlobal
     * @param locale
     */
    public void weatherXiaomiHttp(Subscriber<XiaomiWeatherResponse> subscriber, String latitude, String longitude, String locationKey,
                                  String days, String appKey, String sign, String isGlobal, String locale) {
        Observable observable = api.weatherXiaomi(latitude, longitude, locationKey, days, appKey, sign, isGlobal, locale)
                .map(new HttpResultIsFunc<XiaomiWeatherResponse>());
        toSubscribe(observable, subscriber);
    }
    /*--------------------------------通用接口结束--------------------------------*/
}