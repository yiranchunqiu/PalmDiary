package com.pxz.palmdiary.api.subscribers;

import android.content.Context;

import com.pxz.palmdiary.api.progress.ProgressCancelListener;
import com.pxz.palmdiary.api.progress.ProgressDialogHandler;
import com.pxz.palmdiary.application.BaseApplication;
import com.pxz.palmdiary.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

import static com.pxz.palmdiary.constant.AppConstant.DISMISS_PROGRESS_DIALOG;
import static com.pxz.palmdiary.constant.AppConstant.SHOW_PROGRESS_DIALOG;

/**
 * 类说明：在Http请求开始时，自动显示一个ProgressDialog，在Http请求结束时，关闭ProgressDialog，调用者自己对请求数据进行处理
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:55
 */
public class ProgressSubscriber <T> extends Subscriber<T> implements ProgressCancelListener {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    /**
     * 是否显示进度对话框,默认不显示
     */
    private boolean isLoading = false;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, false);
    }

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context, boolean isLoading) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.isLoading = isLoading;
        if (isLoading) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
        }
    }

    /**
     * 订阅开始时调用，显示ProgressDialog
     */
    @Override
    public void onStart() {
        if (isLoading) {
            showProgressDialog();
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if(isLoading){
            dismissProgressDialog();
        }
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    /**
     * 对错误进行统一处理隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showLong(BaseApplication.getInstance(),"网络中断，请检查您的网络状态！");
        } else if (e instanceof ConnectException) {
            ToastUtils.showLong(BaseApplication.getInstance(),"网络中断，请检查您的网络状态！！");
        } else {
            ToastUtils.showLong(BaseApplication.getInstance(),e.toString());
        }
        e.printStackTrace();
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError();
            onCancelProgress();
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
            onCancelProgress();
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
        if (isLoading) {
            dismissProgressDialog();
        }
    }

    /**
     * 开始进度框
     */
    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    /**
     * 结束进度框
     */
    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }
}