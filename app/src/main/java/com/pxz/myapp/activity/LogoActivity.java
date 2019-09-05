package com.pxz.myapp.activity;

import android.widget.ImageView;

import com.pxz.myapp.R;
import com.pxz.myapp.activity.base.BaseActivity;

import butterknife.Bind;

/**
 * 类说明：启动页
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 16:20
 */
public class LogoActivity extends BaseActivity{
    @Bind(R.id.iv_logo)
    ImageView iv_logo;

    @Override
    protected int getLayout() {
        hideStatusBar();
        return R.layout.activity_logo;
    }

    @Override
    protected void initData() {
        super.initData();iv_logo.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (iv_logo != null) {
                    startActivity(LeadActivity.class);
                    finish();
                }
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
    }
}