package com.pxz.zhangshangriji.api.network;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * 类说明：统一接口异常重试处理
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:54
 */
public class RetryWhenNetworkException  implements Func1<Observable<? extends Throwable>, Observable<?>> {
    /**
     * retry次数
     */
    private int count = 1;
    /**
     * 延迟
     */
    private long delay = 5000;
    /**
     * 叠加延迟
     */
    private long increaseDelay = 5000;

    public RetryWhenNetworkException() {
    }

    public RetryWhenNetworkException(int count, long delay) {
        this.count = count;
        this.delay = delay;
    }

    public RetryWhenNetworkException(int count, long delay, long increaseDelay) {
        this.count = count;
        this.delay = delay;
        this.increaseDelay = increaseDelay;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable.zipWith(Observable.range(1, count + 1), new Func2<Throwable, Integer, Wrapper>() {
            @Override
            public Wrapper call(Throwable throwable, Integer integer) {
                return new Wrapper(throwable, integer);
            }
        }).flatMap(new Func1<Wrapper, Observable<?>>() {
            @Override
            public Observable<?> call(Wrapper wrapper) {
                if ((wrapper.throwable instanceof ConnectException ||
                        wrapper.throwable instanceof SocketTimeoutException ||
                        wrapper.throwable instanceof TimeoutException) && wrapper.index < count + 1) {
                    // 如果超出重试次数也抛出错误，否则默认是会进入onCompleted
                    return Observable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MILLISECONDS);
                }
                return Observable.error(wrapper.throwable);
            }
        });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }
}