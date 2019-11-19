package com.pxz.zhangshangriji.util.thirdparty;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 类说明：rx定时器
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/15 10:46
 */
public class RxTimerUtils {
    /**
     * 每隔milliseconds毫秒后执行next操作
     *
     * @param milliseconds
     * @param next
     */
    public static Subscription interval(long milliseconds, final IRxNext next) {
        return Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long number) {
                        if (next != null) {
                            next.doNext(number);
                        }
                    }
                });
    }

    /**
     * 取消订阅
     */
    public static void cancel(Subscription mSubscription) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    public interface IRxNext {
        void doNext(long number);
    }
}