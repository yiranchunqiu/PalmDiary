package com.pxz.myapp.adapter.vp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.pxz.myapp.R;
import com.pxz.myapp.bean.Constellation;
import com.pxz.myapp.util.ResourcesUtils;

import java.util.ArrayList;

/**
 * 类说明：星座适配器
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/17 15:26
 */
public class ConstellationAdapter extends PagerAdapter {
    /**
     * 数据
     */
    private ArrayList<Constellation> imgResponses = new ArrayList<>();
    /**
     * 上下文
     */
    private Context context;

    public ConstellationAdapter(Context context, ArrayList<Constellation> imgResponses) {
        this.context = context;
        this.imgResponses = imgResponses;
    }

    @Override
    public int getCount() {
        return imgResponses.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.item_constellation, null);
        final RelativeLayout rlImg = view.findViewById(R.id.rl_img);
        ImageView ivHead = view.findViewById(R.id.iv_head);
        ImageView ivIcon = view.findViewById(R.id.iv_icon);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvDate = view.findViewById(R.id.tv_date);
        TextView tvInfo = view.findViewById(R.id.tv_info);
        RatingBar rbWhole = view.findViewById(R.id.rb_whole);
        RatingBar rbLove = view.findViewById(R.id.rb_love);
        RatingBar rbCareer = view.findViewById(R.id.rb_career);
        RatingBar rbMoney = view.findViewById(R.id.rb_money);
        TextView tvWholeInfo = view.findViewById(R.id.tv_whole_info);
        TextView tvLoveInfo = view.findViewById(R.id.tv_love_info);
        TextView tvCareerInfo = view.findViewById(R.id.tv_career_info);
        TextView tvMoneyInfo = view.findViewById(R.id.tv_money_info);
        TextView tvHealthInfo = view.findViewById(R.id.tv_health_info);
        // 背景
        Glide.with(context)
                .load(ResourcesUtils.getResourceByReflect(imgResponses.get(position).getHead()))
                .into(new ViewTarget<View, GlideDrawable>(rlImg) {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        rlImg.setBackground(resource.getCurrent());
                    }
                });
        // 头像
        Glide.with(context)
                .load(ResourcesUtils.getResourceByReflect(imgResponses.get(position).getImg()))
                .dontAnimate()
                .into(ivHead);
        // 星座图标
        Glide.with(context)
                .load(ResourcesUtils.getResourceByReflect(imgResponses.get(position).getIcon()))
                .into(ivIcon);
        // 名字
        tvName.setText(imgResponses.get(position).getName());
        // 日期
        tvDate.setText(imgResponses.get(position).getDate());
        // 概要
        tvInfo.setText(imgResponses.get(position).getInfo());
        // 整体运势
        rbWhole.setRating(imgResponses.get(position).getWhole());
        // 爱情运势
        rbLove.setRating(imgResponses.get(position).getLove());
        // 事业学业
        rbCareer.setRating(imgResponses.get(position).getCareer());
        // 财富运势
        rbMoney.setRating(imgResponses.get(position).getMoney());
        // 整体运势文字
        tvWholeInfo.setText(imgResponses.get(position).getWhole_info());
        // 爱情运势文字
        tvLoveInfo.setText(imgResponses.get(position).getLove_info());
        // 事业学业文字
        tvCareerInfo.setText(imgResponses.get(position).getCareer_info());
        // 财富运势文字
        tvMoneyInfo.setText(imgResponses.get(position).getMoney_info());
        // 健康运势文字
        tvHealthInfo.setText(imgResponses.get(position).getHealth_info());
        container.addView(view);
        return view;
    }
}