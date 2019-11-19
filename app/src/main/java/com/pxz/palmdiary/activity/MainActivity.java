package com.pxz.palmdiary.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pxz.palmdiary.R;
import com.pxz.palmdiary.activity.base.BaseActivity;
import com.pxz.palmdiary.application.BaseApplication;
import com.pxz.palmdiary.fragment.ConstellationFragmnet;
import com.pxz.palmdiary.fragment.HomeFragment;
import com.pxz.palmdiary.fragment.WeatherFragment;
import com.pxz.palmdiary.util.ToastUtils;
import com.pxz.palmdiary.util.TwoHitsUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 类说明：主页
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/24 13:52
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.rl_tilte)
    RelativeLayout rlTilte;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.fl_main)
    FrameLayout fl_main;
    @Bind(R.id.ll_home_main)
    LinearLayout ll_home_main;
    @Bind(R.id.iv_riji)
    ImageView iv_riji;
    @Bind(R.id.tv_riji)
    TextView tv_riji;
    @Bind(R.id.iv_xingzuo)
    ImageView iv_xingzuo;
    @Bind(R.id.tv_xingzuo)
    TextView tv_xingzuo;
    @Bind(R.id.iv_tianqi)
    ImageView iv_tianqi;
    @Bind(R.id.tv_tianqi)
    TextView tv_tianqi;
    private HomeFragment homeFragment;
    private ConstellationFragmnet constellationFragmnet;
    private WeatherFragment weatherFragment;
    private int type = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        /*初始化页面和fragment*/
        viewColorImg(type);
        viewFragment(type);
        finishActivityAnim=false;
    }

    @OnClick({R.id.ll_riji, R.id.ll_xingzuo, R.id.ll_tianqi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_riji:
                type = 1;
                viewColorImg(type);
                viewFragment(type);
                break;
            case R.id.ll_xingzuo:
                type = 2;
                viewColorImg(type);
                viewFragment(type);
                break;
            case R.id.ll_tianqi:
                type = 3;
                viewColorImg(type);
                viewFragment(type);
                break;
            default:
                break;
        }
    }

    private void viewFragment(int type) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (type) {
            case 1:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.fl_main, homeFragment);
                transaction.commit();
                break;
            case 2:
                if (constellationFragmnet == null) {
                    constellationFragmnet = new ConstellationFragmnet();
                }
                transaction.replace(R.id.fl_main, constellationFragmnet);
                transaction.commit();
                break;
            case 3:
                if (weatherFragment == null) {
                    weatherFragment = new WeatherFragment();
                }
                transaction.replace(R.id.fl_main, weatherFragment);
                transaction.commit();
                break;
            default:
                break;
        }
    }

    private void viewColorImg(int type) {
        switch (type) {
            case 1:
                // 标题
                tvTitle.setText("掌上日记");
                iv_riji.setImageResource(R.mipmap.diaryclick);
                tv_riji.setTextColor(getResources().getColor(R.color.black));
                iv_xingzuo.setImageResource(R.mipmap.constellation);
                tv_xingzuo.setTextColor(getResources().getColor(R.color.color_999999));
                iv_tianqi.setImageResource(R.mipmap.weather);
                tv_tianqi.setTextColor(getResources().getColor(R.color.color_999999));
                break;
            case 2:
                // 标题
                tvTitle.setText("星座物语");
                iv_riji.setImageResource(R.mipmap.diary);
                tv_riji.setTextColor(getResources().getColor(R.color.color_999999));
                iv_xingzuo.setImageResource(R.mipmap.constellationclick);
                tv_xingzuo.setTextColor(getResources().getColor(R.color.black));
                iv_tianqi.setImageResource(R.mipmap.weather);
                tv_tianqi.setTextColor(getResources().getColor(R.color.color_999999));
                break;
            case 3:
                // 标题
                tvTitle.setText("合肥");
                iv_riji.setImageResource(R.mipmap.diary);
                tv_riji.setTextColor(getResources().getColor(R.color.color_999999));
                iv_xingzuo.setImageResource(R.mipmap.constellation);
                tv_xingzuo.setTextColor(getResources().getColor(R.color.color_999999));
                iv_tianqi.setImageResource(R.mipmap.weatherclick);
                tv_tianqi.setTextColor(getResources().getColor(R.color.black));
                break;
            default:
                break;
        }
    }

    /**
     * 退出app
     */
    @Override
    public void onBackPressed() {
        if (!TwoHitsUtils.check()) {
            ToastUtils.showLong(mBaseApplication,"再次点击退出程序");
        } else {
            finish();
            BaseApplication.getInstance().appExit();
        }
    }
}