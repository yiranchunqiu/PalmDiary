package com.pxz.palmdiary.adapter.vp;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：引导页适配器
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 16:44
 */
public class LeadAdapter extends PagerAdapter {
    private List<View> views;
    private List<String> strings;

    public LeadAdapter() {
        super();
        views = new ArrayList<View>();
        strings = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void addView(View v) {
        views.add(v);
    }

    public void delView(View v) {
        views.remove(v);
    }

    public void addTitle(String t) {
        strings.add(t);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
    }

    public void delTitle(String t) {
        strings.remove(t);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

    /**
     * PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    /**
     * 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = views.get(position);
        container.addView(v);
        return v;
    }
}
