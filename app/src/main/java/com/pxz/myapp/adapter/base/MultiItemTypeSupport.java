package com.pxz.myapp.adapter.base;

/**
 * 类说明：多种 item 布局支持接口
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 10:57
 */
public interface MultiItemTypeSupport<T> {
    /**
     * 根据不同的 itemtype 获取不同类型的布局文件
     * @param itemType
     * @return
     */
    int getLayoutId(int itemType);

    /**
     * 根据不同 position 的 bean 生成不同的的 item 布局
     * @param position
     * @param t 多种不同 item 传入的 bean 数据
     * @return
     */
    int getItemViewType(int position, T t);
}