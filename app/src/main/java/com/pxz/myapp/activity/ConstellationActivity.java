package com.pxz.myapp.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pxz.myapp.R;
import com.pxz.myapp.activity.base.BaseActivity;
import com.pxz.myapp.adapter.vp.ConstellationAdapter;
import com.pxz.myapp.bean.Constellation;
import com.pxz.myapp.util.ColorUtils;
import com.pxz.myapp.util.ConstellationsUtils;
import com.pxz.myapp.util.ConvertUtils;
import com.pxz.myapp.util.thirdparty.RxTimerUtils;
import com.pxz.myapp.view.FixedViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import tyrantgit.widget.HeartLayout;

/**
 * 类说明：星座页面（弃用）
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 12:48
 */
@Deprecated
public class ConstellationActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.vp_constellation)
    FixedViewPager vpConstellation;
    @Bind(R.id.heart_layout)
    HeartLayout heartLayout;
    /**
     * 星座数据
     */
    private ArrayList<Constellation> constellations = new ArrayList<>();
    /**
     * 适配器
     */
    private ConstellationAdapter constellationAdapter;
    /**
     * 当前位置
     */
    private int position = 1;
    /**
     * json数据
     */
    String jsonConstellation = null;
    /**
     * 定时器
     */
    private Subscription mSubscription;

    @Override
    protected int getLayout() {
        return R.layout.activity_constellation;
    }

    @Override
    protected void initData() {
        super.initData();
        ivBack.setVisibility(View.VISIBLE);
        // 标题
        tvTitle.setText("星座物语");
        // 获取json数据
        try {
            jsonConstellation = ConvertUtils.toString(mContext.getAssets().open("constellation_data.json"));
            Gson gson = new Gson();
            constellations.addAll((Collection<? extends Constellation>) gson.fromJson(jsonConstellation, new TypeToken<List<Constellation>>() {
            }.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 处理当前日期星座
        for (int i = 0; i < constellations.size(); i++) {
            if (ConstellationsUtils.ConstellationsName().equals(constellations.get(i).getName())) {
                position = i;
            }
        }
        // 点赞动画
        // 定时器
        mSubscription = RxTimerUtils.interval(200, new RxTimerUtils.IRxNext() {
            @Override
            public void doNext(long number) {
                heartLayout.addHeart(ColorUtils.randomColor());
            }
        });
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        // 初始化适配器
        constellationAdapter = new ConstellationAdapter(mContext, constellations);
        vpConstellation.setAdapter(constellationAdapter);
        vpConstellation.addOnPageChangeListener(new CustomPageChangeListener());
        // 设置当前位置
        vpConstellation.setCurrentItem(position);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解除绑定
        RxTimerUtils.cancel(mSubscription);
    }

    /**
     * 滑动
     */
    private class CustomPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(final int position) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }
}