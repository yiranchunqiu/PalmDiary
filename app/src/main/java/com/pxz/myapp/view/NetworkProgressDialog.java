package com.pxz.myapp.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.pxz.myapp.R;

/**
 * 类说明：自定义网络加载进度条
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 12:00
 */
public class NetworkProgressDialog extends Dialog {
    public static NetworkProgressDialog ProgressDialogBarView = null;

    public NetworkProgressDialog(Context context) {
        super(context);
    }

    public NetworkProgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(true);
    }

    public static NetworkProgressDialog createDialog(Context context) {
        ProgressDialogBarView = new NetworkProgressDialog(context, R.style.dialog_network);
        ProgressDialogBarView.setContentView(R.layout.progress_network);
        Window wd = ProgressDialogBarView.getWindow();
        WindowManager.LayoutParams lp = wd.getAttributes();
        lp.alpha = 0.96f;
        wd.setAttributes(lp);
        ProgressDialogBarView.setCanceledOnTouchOutside(false);
        return ProgressDialogBarView;
    }

    public static void clear() {
        ProgressDialogBarView = null;
    }
}