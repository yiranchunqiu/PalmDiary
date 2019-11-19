package com.pxz.palmdiary.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pxz.palmdiary.R;
import com.pxz.palmdiary.activity.base.BaseActivity;
import com.pxz.palmdiary.adapter.rv.DiaryAdapter;
import com.pxz.palmdiary.application.BaseApplication;
import com.pxz.palmdiary.bean.DiaryBean;
import com.pxz.palmdiary.db.DiaryDatabaseHelper;
import com.pxz.palmdiary.util.DateTimeUtils;
import com.pxz.palmdiary.util.ToastUtils;
import com.pxz.palmdiary.util.TwoHitsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 类说明：日记主页（弃用）
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 12:41
 */
@Deprecated
public class HomeActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_right_weather)
    TextView tvRightWeather;
    @Bind(R.id.main_tv_date)
    TextView mMainTvDate;
    @Bind(R.id.main_rv_show_diary)
    RecyclerView mMainRvShowDiary;
    @Bind(R.id.item_first)
    LinearLayout mItemFirst;
    /**
     * 数据
     */
    private List<DiaryBean> mDiaryBeanList;
    /**
     * db
     */
    private DiaryDatabaseHelper mHelper;
    /**
     * 适配器
     */
    private DiaryAdapter diaryAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        super.initData();
        // 初始化
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
        tvRight.setVisibility(View.VISIBLE);
        tvRightWeather.setVisibility(View.VISIBLE);
        tvTitle.setText("掌上日记");
        finishActivityAnim=false;
        getDiaryBeanList();
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        // 适配器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMainRvShowDiary.setLayoutManager(linearLayoutManager);
    }

    @OnClick({R.id.tv_right,R.id.tv_right_weather,R.id.main_fab_enter_edit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right:
                startActivity(ConstellationActivity.class);
                break;
            case R.id.tv_right_weather:
                startActivity(WeatherActivity.class);
                break;
            case R.id.main_fab_enter_edit:
                startActivity(AddActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainTvDate.setText("今天，" + DateTimeUtils.formatNowDate2String(1)+DateTimeUtils.formatNowDate2String(8));
        getDiaryBeanList();
        if (mDiaryBeanList.size()<=0){
            mItemFirst.setVisibility(View.VISIBLE);
        }else{
            mItemFirst.setVisibility(View.GONE);
        }
        diaryAdapter = new DiaryAdapter(mDiaryBeanList, mContext, new DiaryAdapter.EditOnClickListener() {
            @Override
            public void editOnClick(DiaryBean diaryBean) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("diaryBean", diaryBean);
                startActivity(EditActivity.class, bundle);
            }
        });
        mMainRvShowDiary.setAdapter(diaryAdapter);
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

    /**
     * 获取数据
     *
     * @return
     */
    private List<DiaryBean> getDiaryBeanList() {
        mDiaryBeanList = new ArrayList<>();
        List<DiaryBean> diaryList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Diary", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                // 获取当前的日期，
                // 检索到数据库里面，有某篇日记的日期跟当天的日期一致的话，
                // 就将伪日记从布局中 remove 掉就行了
                String dateSystem = DateTimeUtils.formatNowDate2String(1);
                if (date.equals(dateSystem)) {
                    mItemFirst.setVisibility(View.GONE);
                    break;
                }else{
                    mItemFirst.setVisibility(View.VISIBLE);
                }
            } while (cursor.moveToNext());
        }
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));
                mDiaryBeanList.add(new DiaryBean(date, time, title, content, tag));
            } while (cursor.moveToNext());
        }
        cursor.close();
        for (int i = mDiaryBeanList.size() - 1; i >= 0; i--) {
            diaryList.add(mDiaryBeanList.get(i));
        }
        mDiaryBeanList = diaryList;
        return mDiaryBeanList;
    }
}