package com.pxz.myapp.adapter.base;

import android.view.View;

/**
 * 类说明：点击监听接口
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 11:01
 */
public interface OnItemClickListener {
    /**
     * 点击监听接口
     */
    void onItemClick(View view, int position);

    /**
     * 长按监听接口
     */
    void onItemLongClick(View view, int position);
}