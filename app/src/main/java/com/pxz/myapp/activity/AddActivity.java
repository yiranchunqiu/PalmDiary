package com.pxz.myapp.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pxz.myapp.R;
import com.pxz.myapp.activity.base.BaseActivity;
import com.pxz.myapp.db.DiaryDatabaseHelper;
import com.pxz.myapp.util.DateTimeUtils;
import com.pxz.myapp.view.LinedEditText;

import butterknife.Bind;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

/**
 * 类说明：添加页面
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 13:51
 */
public class AddActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.add_diary_tv_date)
    TextView mAddDiaryTvDate;
    @Bind(R.id.add_diary_et_title)
    EditText mAddDiaryEtTitle;
    @Bind(R.id.add_diary_et_content)
    LinedEditText mAddDiaryEtContent;
    @Bind(R.id.right_labels)
    FloatingActionsMenu rightLabels;
    /**
     * 数据库
     */
    private DiaryDatabaseHelper mHelper;

    @Override
    protected int getLayout() {
        return R.layout.activity_add;
    }

    @Override
    protected void initData() {
        super.initData();
        ivBack.setVisibility(View.VISIBLE);
        // 标题
        tvTitle.setText("添加日记");
        // 日期
        mAddDiaryTvDate.setText("今天，" + DateTimeUtils.formatNowDate2String(1) + DateTimeUtils.formatNowDate2String(8));
        // 数据库
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
    }

    @OnClick({R.id.iv_back, R.id.add_diary_fab_back, R.id.add_diary_fab_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.add_diary_fab_back:
                String date = DateTimeUtils.formatNowDate2String(1);
                String time =DateTimeUtils.formatNowDate2String(8);
                // 获取系统时间
                String tag = String.valueOf(System.currentTimeMillis());
                String title = mAddDiaryEtTitle.getText().toString() + "";
                String content = mAddDiaryEtContent.getText().toString() + "";
                if (!title.equals("") || !content.equals("")) {
                    SQLiteDatabase db = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("date", date);
                    values.put("time", time);
                    values.put("title", title);
                    values.put("content", content);
                    values.put("tag", tag);
                    db.insert("Diary", null, values);
                    values.clear();
                }
                finish();
                break;
            case R.id.add_diary_fab_add:
                rightLabels.collapse();
                break;
            default:
                break;
        }
    }
}