package com.pxz.palmdiary.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 类说明：RecyclerView 的通用 Holder
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 10:56
 */
public class CommonHolder extends RecyclerView.ViewHolder{
    /**
     * item里面的控件集
     */
    protected SparseArray<View> mViews;
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 条目布局
     */
    protected View mConvertView;

    public CommonHolder(View itemView, Context context, ViewGroup parent) {
        super(itemView);
        mContext = context;
        mViews = new SparseArray<>();
        mConvertView = itemView;
    }

    /**
     * 获取 ViewHolder
     *
     * @param context   上下文
     * @param parent    parent
     * @param layoutId  item 布局文件 id
     * @return holder
     */
    public static CommonHolder getHolder(Context context, ViewGroup parent, int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CommonHolder viewHolder = new CommonHolder(itemView, context, parent);
        return viewHolder;
    }

    /**
     * 通过 viewId 获取控件
     *
     * @param viewId    item 中 子view 的 id
     * @return  子view
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    /**
     * 给 TextView 设置内容
     *
     * @param viewId
     * @param s
     */
    public CommonHolder setText(int viewId, String s){
        TextView view = getView(viewId);
        view.setText(s);
        return this;
    }

    /**
     * 给 ImageView 设置内容
     *
     * @param viewId
     * @param resId
     */
    public CommonHolder setImg(int viewId, int resId){
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 给 view 设置点击监听事件
     *
     * @param viewId
     * @param onClickListener
     */
    public CommonHolder setClickListener(int viewId, View.OnClickListener onClickListener){
        View view = getView(viewId);
        view.setOnClickListener(onClickListener);
        return this;
    }
}