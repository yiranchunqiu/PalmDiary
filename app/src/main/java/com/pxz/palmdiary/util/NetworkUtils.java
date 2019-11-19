package com.pxz.palmdiary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * 类说明：网络判断工具类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/16 09:47
 */
@SuppressLint("MissingPermission")
public class NetworkUtils {
    /**
     * 判断网络是否可用
     *
     * @param context 上下文
     * @return true：网络可用 false：网络不可用
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        } else {
            return false;
        }
    }

    /**
     * 判断GPS是否打开
     *
     * @param context 上下文
     * @return true：gps打开 false：gps未打开
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = lm.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }

    /**
     * 判断WIFI是否打开
     *
     * @param context 上下文
     * @return true：wifi打开 false：wifi未打开
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断是否是4G/3G/2G网络
     *
     * @param context 上下文
     * @return true：是4G/3G/2G网络 false：不是4G/3G/2G网络
     */
    public static boolean is3rd(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是WIFI网络
     *
     * @param context 上下文
     * @return true：是wifi网络 false：不是wifi网络
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 设备网络连接类型
     *
     * @param context 上下文
     * @return 连接类型
     */
    public String getPhoneNetworkType(Context context) {
        ConnectivityManager connManager;
        connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (!(info != null && info.isConnected())) {
            return "OFFLINE";
        }
        if (info != null) {
            return info.getTypeName();
        } else {
            return "OFFLINE";
        }
    }
}