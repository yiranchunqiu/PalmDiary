package com.pxz.zhangshangriji.api.network;

import com.pxz.zhangshangriji.bean.app.XiaomiWeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 类说明：网络api
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:51
 */
public interface AppApi {
    /*--------------------------------通用接口开始--------------------------------*/

    @GET("/wtr-v3/weather/all")
    Observable<XiaomiWeatherResponse> weatherXiaomi(@Query("latitude") String latitude, @Query("longitude") String longitude,
                                                    @Query("locationKey") String locationKey,
                                                    @Query("days") String days, @Query("appKey") String appKey,
                                                    @Query("sign") String sign, @Query("isGlobal") String isGlobal,
                                                    @Query("locale") String locale);
    /*--------------------------------通用接口开始--------------------------------*/
}