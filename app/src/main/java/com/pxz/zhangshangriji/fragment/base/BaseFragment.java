package com.pxz.zhangshangriji.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pxz.zhangshangriji.R;
import com.pxz.zhangshangriji.util.CacheUtils;

import butterknife.ButterKnife;

/**
 * 类说明：BaseFragment
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 11:24
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 布局view
     */
    protected View mView;
    /**
     * 所在activity
     */
    protected Activity mActivity;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 缓存
     */
    protected CacheUtils mCacheUtils;

    @Override
    public void onAttach(Context context) {
        //初始化当前activity
        mActivity = (Activity) context;
        //初始化上下文
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //绑定布局
        mView = inflater.inflate(getLayoutId(), container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化存储
        mCacheUtils = CacheUtils.get(mActivity);
        // 注解框架初始化
        ButterKnife.bind(this, view);
        //沉浸状态栏
        initSetStatusBar();
        //参数获取
        initData();
        //初始化控件
        initFindById();
        //适配器
        initAdapter();
        //网络请求
        initHttp();
        //业务处理
        initLogic();
        //点击事件处理
        initSetListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 解绑
        ButterKnife.unbind(this);
    }

    /**
     * 绑定布局
     */
    protected abstract int getLayoutId();


    /**
     * 沉浸状态栏
     */
    protected void initSetStatusBar() {
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
     * activity的跳转,带参数（动画右进）
     */
    protected void startActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }

    /**
     * activity的跳转，不带参数（动画右进）
     */
    protected void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(getActivity(), targetClass);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.act_right_in, R.anim.act_home);
    }
}