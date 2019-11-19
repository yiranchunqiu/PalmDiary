package com.pxz.zhangshangriji.api.subscribers;

/**
 * 类说明：网络响应接口
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:55
 */
public interface SubscriberOnNextListener<T> {
    /**
     * 完成
     */
    void onNext(T t);

    /**
     * 错误
     */
    void onError();
}