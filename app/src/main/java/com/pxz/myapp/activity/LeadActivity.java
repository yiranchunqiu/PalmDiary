package com.pxz.myapp.activity;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pxz.myapp.R;
import com.pxz.myapp.activity.base.BaseActivity;
import com.pxz.myapp.adapter.vp.LeadAdapter;

import butterknife.Bind;
import butterknife.OnClick;

import static com.pxz.myapp.constant.AppConstant.IS_FIRST_RUN;

/**
 * 类说明：引导页
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 16:20
 */
public class LeadActivity extends BaseActivity {
    @Bind(R.id.vp_lead)
    ViewPager vp_lead;
    @Bind(R.id.btn_enter)
    Button btn_enter;
    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.iv_2)
    ImageView iv2;
    @Bind(R.id.iv_3)
    ImageView iv3;
    private LeadAdapter leadAdapter;

    @Override
    protected int getLayout() {
        hideStatusBar();
        return R.layout.activity_lead;
    }

    @Override
    protected void initData() {
        super.initData();
        if (TextUtils.isEmpty(mCacheUtils.getAsString(IS_FIRST_RUN))) {
            /*第一次进入*/
            mCacheUtils.put(IS_FIRST_RUN, "1");
            playVp();
            initDots();
        } else {
            if (mCacheUtils.getAsString(IS_FIRST_RUN).equals("1")) {
                /*跳过引导页*/
                startActivity(MainActivity.class);
                finish();
            } else {
                /*第一次进入*/
                mCacheUtils.put(IS_FIRST_RUN, "1");
                playVp();
                initDots();
            }
        }
    }

    @OnClick({R.id.btn_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_enter:
                startActivity(MainActivity.class);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initSetListener() {
        super.initSetListener();
        /*滑动*/
        vp_lead.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // 设置底部小点选中状态
                setCurDot(position);
                /*按钮的隐藏和显示*/
                if (position == leadAdapter.getCount() - 1) {
                    btn_enter.setVisibility(View.VISIBLE);
                } else {
                    btn_enter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 初始化数据和适配器
     */
    public void playVp() {
        leadAdapter = new LeadAdapter();
        vp_lead.setAdapter(leadAdapter);
        ImageView i1 = new ImageView(mActivity);
        i1.setScaleType(ImageView.ScaleType.FIT_XY);
        i1.setImageResource(R.mipmap.lead_1);
        ImageView i2 = new ImageView(mActivity);
        i2.setScaleType(ImageView.ScaleType.FIT_XY);
        i2.setImageResource(R.mipmap.lead_2);
        ImageView i3 = new ImageView(mActivity);
        i3.setScaleType(ImageView.ScaleType.FIT_XY);
        i3.setImageResource(R.mipmap.lead_3);
        leadAdapter.addView(i1);
        leadAdapter.addView(i2);
        leadAdapter.addView(i3);
        leadAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
    }

    /**
     * 初始化
     */
    private void initDots() {
        iv1.setEnabled(true);
        iv2.setEnabled(false);
        iv3.setEnabled(false);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        switch (position) {
            case 0:
                iv1.setEnabled(true);
                iv2.setEnabled(false);
                iv3.setEnabled(false);
                break;
            case 1:
                iv1.setEnabled(false);
                iv2.setEnabled(true);
                iv3.setEnabled(false);
                break;
            case 2:
                iv1.setEnabled(false);
                iv2.setEnabled(false);
                iv3.setEnabled(true);
                break;
            default:
                break;
        }
    }
}