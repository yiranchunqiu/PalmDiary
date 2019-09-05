package com.pxz.myapp.api.progress;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.pxz.myapp.view.NetworkProgressDialog;

import java.lang.ref.WeakReference;

import static com.pxz.myapp.constant.AppConstant.DISMISS_PROGRESS_DIALOG;
import static com.pxz.myapp.constant.AppConstant.SHOW_PROGRESS_DIALOG;

/**
 * 类说明：网络加载进度框提示
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:55
 */
public class ProgressDialogHandler extends Handler {
    /**
     * 定义进度条
     */
    private NetworkProgressDialog pd;
    /**
     * 弱引用防止内存泄露
     */
    private WeakReference<Context> context;
    /**
     * 是否显示进度条
     */
    private boolean cancelable;
    /**
     * 回调接口
     */
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean cancelable) {
        super();
        this.context = new WeakReference<Context>(context);
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    /**
     * 异步通信处理
     */
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
                default:
                    break;
        }
    }

    /**
     * 初始化进度对话框
     */
    private void initProgressDialog() {
        if (pd == null) {
            pd = NetworkProgressDialog.createDialog((context.get()));
            pd.setCancelable(cancelable);
            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }
            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    /**
     * 关闭进度对话框
     */
    private void dismissProgressDialog() {
        try {
            if (pd != null) {
                mProgressCancelListener.onCancelProgress();
                pd.dismiss();
                pd = null;
                context = null;
                NetworkProgressDialog.clear();
            }
        } catch (IllegalArgumentException l) {
        }
    }
}