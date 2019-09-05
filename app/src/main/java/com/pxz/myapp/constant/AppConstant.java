package com.pxz.myapp.constant;

import com.pxz.myapp.application.BaseApplication;

import java.io.File;

/**
 * 类说明：app常量
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 16:47
 */
public class AppConstant {
    /**
     * 第一次进入参数
     */
    public static final String IS_FIRST_RUN = "isFirstRun";

    /**
     * 进度条
     */
    /**
     * 网络请求loading开始显示标志
     */
    public static final int SHOW_PROGRESS_DIALOG = 1;
    /**
     * 网络请求loading结束显示标志
     */
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    /**
     * 生产
     */
    public static final String URL_HOST = "https://weatherapi.market.xiaomi.com";
    /**
     * 默认超时
     */
    public static final int DEFAULT_TIMEOUT = 10;
    /**
     * 读写超时
     */
    public static final int READ_OR_WRITE_TIMEOUT = 20;
    /**
     * 路径
     */
    public static final String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/zhangshangqiji";
}
