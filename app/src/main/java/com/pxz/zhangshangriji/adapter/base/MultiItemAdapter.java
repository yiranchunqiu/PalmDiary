package com.pxz.zhangshangriji.adapter.base;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * 类说明：多种 item 布局adapter
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 10:57
 */
public abstract class MultiItemAdapter<T> extends CommonAdapter {
    private MultiItemTypeSupport<T> mMultiItemTypeSupport;

    /**
     * 构造方法
     * @param datas
     * @param context
     * @param multiItemTypeSupport  多种 item 布局支持接口
     */
    public MultiItemAdapter(List<T> datas, Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(datas, context, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, (T) mDatas.get(position));
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        CommonHolder holder = CommonHolder.getHolder(mContext, parent, layoutId);
        return holder;
    }
}