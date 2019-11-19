package com.pxz.zhangshangriji.adapter.rv;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pxz.zhangshangriji.R;
import com.pxz.zhangshangriji.adapter.base.CommonAdapter;
import com.pxz.zhangshangriji.adapter.base.CommonHolder;
import com.pxz.zhangshangriji.bean.DiaryBean;
import com.pxz.zhangshangriji.util.DateTimeUtils;

import java.util.List;

/**
 * 类说明：日记adapter
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 13:37
 */
public class DiaryAdapter extends CommonAdapter<DiaryBean> {
    private int mEditPosition = -1;
    private EditOnClickListener editOnClickListener;

    public DiaryAdapter(List<DiaryBean> datas, Context context,EditOnClickListener editOnClickListener) {
        super(datas, context, R.layout.item_diary);
        this.editOnClickListener=editOnClickListener;
    }

    @Override
    public void bindHolder(final CommonHolder holder, final DiaryBean diaryBean) {
        // 前面的小圆圈
        TextView mTvDate=holder.getView(R.id.main_tv_date);
        TextView mTvTitle= holder.getView(R.id.main_tv_title);
        TextView mTvContent= holder.getView(R.id.main_tv_content);
        final ImageView mIvEdit= holder.getView(R.id.main_iv_edit);
        // 最外层的linerlayout的id
        LinearLayout mLl = holder.getView(R.id.item_ll);
        ImageView mIvCircle= holder.getView(R.id.main_iv_circle);
        // 如果得到的日期与系统的日期相等 ，那么把图标小圆圈加上去。
        if (diaryBean.getDate().equals(DateTimeUtils.formatNowDate2String(1))) {
            mIvCircle.setImageResource(R.mipmap.circle_orange);
        }
        mTvDate.setText(diaryBean.getDate()+" "+diaryBean.getTime());
        mTvTitle.setText(diaryBean.getTitle());
        mTvContent.setText("       " + diaryBean.getContent());
        // INVISIBLE不可见，但仍占位置。
        mIvEdit.setVisibility(View.INVISIBLE);
        if (mEditPosition == holder.getAdapterPosition()) {
            // 点击到游标处 就显示了
            // VISIBLE可见
            mIvEdit.setVisibility(View.VISIBLE);
        } else {
            // GONE不可见，不占据空间
            mIvEdit.setVisibility(View.GONE);
        }
        mLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIvEdit.getVisibility() == View.VISIBLE) {
                    mIvEdit.setVisibility(View.GONE);
                } else {
                    mIvEdit.setVisibility(View.VISIBLE);
                }
                if (mEditPosition != holder.getAdapterPosition()) {
                    // 刷新。
                    notifyItemChanged(mEditPosition);
                }
                mEditPosition = holder.getAdapterPosition();
            }
        });
        mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOnClickListener.editOnClick(diaryBean);
            }
        });
    }

    public interface EditOnClickListener{
        void editOnClick(DiaryBean diaryBean);
    }
}