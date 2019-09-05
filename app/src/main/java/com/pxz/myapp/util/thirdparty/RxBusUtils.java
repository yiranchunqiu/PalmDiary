package com.pxz.myapp.util.thirdparty;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 类说明：rx异步通信
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 10:46
 */
public class RxBusUtils {
    private static volatile RxBusUtils mInstance;
    private final Subject<Object, Object> bus;

    public RxBusUtils() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 单例模式RxBus
     */
    public static RxBusUtils getInstance() {
        RxBusUtils rxBus2 = mInstance;
        if (mInstance == null) {
            synchronized (RxBusUtils.class) {
                rxBus2 = mInstance;
                if (mInstance == null) {
                    rxBus2 = new RxBusUtils();
                    mInstance = rxBus2;
                }
            }
        }
        return rxBus2;
    }

    /**
     * 发送消息
     */
    public void post(Object object) {
        bus.onNext(object);
    }

    /**
     * 接收消息
     */
    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}