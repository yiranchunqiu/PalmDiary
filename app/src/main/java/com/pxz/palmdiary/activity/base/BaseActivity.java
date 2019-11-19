package com.pxz.palmdiary.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.pxz.palmdiary.R;
import com.pxz.palmdiary.application.BaseApplication;
import com.pxz.palmdiary.util.CacheUtils;
import com.pxz.palmdiary.util.LogUtils;

import butterknife.ButterKnife;

/**
 * 类说明：BaseActivity
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 09:58
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * activity
     */
    protected Activity mActivity;
    /**
     * context
     */
    protected Context mContext;
    /**
     * application
     */
    protected BaseApplication mBaseApplication;
    /**
     * 存储
     */
    protected CacheUtils mCacheUtils;
    /**
     * 退出是否有动画(true：有动画 false：无动画)
     */
    protected boolean finishActivityAnim = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化上下文
        mActivity = this;
        mContext=this;
        // 初始化存储
        mCacheUtils = CacheUtils.get(mActivity);
        // 添加activity
        mBaseApplication=BaseApplication.getInstance();
        mBaseApplication.pushActivity(mActivity);
        // 添加onCreate的log日志
        LogUtils.d(mContext, "onCreate");
        // 绑定布局
        setContentView(getLayout());
        // 初始化ButterKnife
        ButterKnife.bind(mActivity);
        // 沉浸状态栏
        initSetStatusBar();
        // 参数获取
        initData();
        // 初始化控件
        initFindById();
        // 适配器
        initAdapter();
        // 网络请求
        initHttp();
        // 业务处理
        initLogic();
        // 点击事件处理
        initSetListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 添加onStart的log日志
        LogUtils.d(mActivity, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // 添加onRestart的log日志
        LogUtils.d(mActivity, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 添加onResume的log日志
        LogUtils.d(mActivity, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 添加onPause的log日志
        LogUtils.d(mActivity, "onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 添加onNewIntent的log日志
        LogUtils.d(mActivity, "onNewIntent");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 添加onActivityResult的log日志
        LogUtils.d(mActivity, "onActivityResult");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 添加onStart的log日志
        LogUtils.d(mActivity, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 添加onDestroy的log日志
        LogUtils.d(mActivity, "onDestroy");
        // 移除activity
        mBaseApplication.popActivity(mActivity);
        // 解绑
        ButterKnife.unbind(mActivity);
    }

    /**
     * 绑定布局
     */
    protected abstract int getLayout();

    /**
     * 沉浸状态栏
     */
    protected void initSetStatusBar() {
        StatusBarUtil.setColor(mActivity, getResources().getColor(R.color.black), 0);
    }

    /**
     * 参数获取
     */
    protected void initData() {
    }

    /**
     * 初始化控件
     */
    protected void initFindById(){
    }

    /**
     * 适配器
     */
    protected void initAdapter(){
    }

    /**
     * 网络请求
     */
    protected void initHttp(){
    }

    /**
     * 业务处理
     */
    protected void initLogic(){
    }

    /**
     * 点击事件处理
     */
    protected void initSetListener(){
    }

    /**
     * 隐藏状态栏
     */
    public void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 点击空白区域隐藏软键盘
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {
                    hideSoftInput(v.getWindowToken());
                }
            }
            return super.dispatchTouchEvent(ev);
        } catch (IllegalArgumentException l) {
            return false;
        }
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 跳转activity(添加动画，添加数据)
     */
    protected void startActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(mContext, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }

    /**
     * 跳转activity(添加动画，不添加数据)
     */
    protected void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(mContext, targetClass);
        startActivity(intent);
        overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }

    /**
     * 结束activity(添加动画)
     */
    @Override
    public void finish() {
        super.finish();
        // true：有动画 false：无动画
        if (finishActivityAnim) {
            overridePendingTransition(R.anim.act_home, R.anim.act_right_out);
        }
    }
}