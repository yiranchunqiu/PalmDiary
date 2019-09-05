package com.pxz.myapp.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.pxz.myapp.BuildConfig;
import com.pxz.myapp.R;
import com.pxz.myapp.util.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 类说明：BaseApplication
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 09:59
 */
public class BaseApplication extends Application {
    /**
     * 应用实例
     */
    public static BaseApplication baseApplication;
    /**
     * 管理activity
     */
    private static List<Activity> mActivitys = Collections.synchronizedList(new LinkedList<Activity>());

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化基类
        baseApplication = this;
        // 开启log日志
        if (BuildConfig.DEBUG) {
            LogUtils.openAll();
            // 初始化检测内存泄漏
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        } else {
            LogUtils.closeAll();
        }
    }

    /**
     * 获取Application
     *
     * @return Application
     */
    public static synchronized BaseApplication getInstance() {
        if (baseApplication == null) {
            synchronized (BaseApplication.class) {
                if (baseApplication == null) {
                    baseApplication = new BaseApplication();
                }
            }
        }
        return baseApplication;
    }

    /**
     * 刷新设置
     */
    static {
        //设置Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //设置主题颜色
                layout.setPrimaryColorsId(R.color.white, R.color.black);
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    /**
     * 添加activity
     *
     * @param activity 添加的activity
     */
    public void pushActivity(Activity activity) {
        mActivitys.add(activity);
    }

    /**
     * 移除activity
     *
     * @param activity 移除的activity
     */
    public void popActivity(Activity activity) {
        mActivitys.remove(activity);
    }

    /**
     * 返回当前activity
     *
     * @return 当前activity
     */
    public Activity currentActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return null;
        }
        Activity activity = mActivitys.get(mActivitys.size() - 1);
        return activity;
    }

    /**
     * 结束当前activity
     */
    public void finishCurrentActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        Activity activity = mActivitys.get(mActivitys.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的activity
     *
     * @param activity 指定的activity
     */
    public void finishActivity(Activity activity) {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        if (activity != null) {
            mActivitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类型的activity
     *
     * @param cls 指定的activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        for (int i = 0; i < mActivitys.size(); i++) {
            if (mActivitys.get(i).getClass().equals(cls)) {
                finishActivity(mActivitys.get(i));
            }
        }
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivity() {
        if (mActivitys == null) {
            return;
        }
        for (int i = 0; i < mActivitys.size(); i++) {
            mActivitys.get(i).finish();
        }
        mActivitys.clear();
    }

    /**
     * 退出程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
        }
    }
}