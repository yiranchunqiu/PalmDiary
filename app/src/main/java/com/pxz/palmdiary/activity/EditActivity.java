package com.pxz.palmdiary.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pxz.palmdiary.R;
import com.pxz.palmdiary.activity.base.BaseActivity;
import com.pxz.palmdiary.bean.DiaryBean;
import com.pxz.palmdiary.db.DiaryDatabaseHelper;
import com.pxz.palmdiary.util.DateTimeUtils;
import com.pxz.palmdiary.view.LinedEditText;

import butterknife.Bind;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

/**
 * 类说明：修改页面
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 14:35
 */
public class EditActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.update_diary_tv_date)
    TextView mUpdateDiaryTvDate;
    @Bind(R.id.update_diary_et_title)
    EditText mUpdateDiaryEtTitle;
    @Bind(R.id.update_diary_et_content)
    LinedEditText mUpdateDiaryEtContent;
    @Bind(R.id.update_diary_tv_tag)
    TextView mTvTag;
    @Bind(R.id.right_labels)
    FloatingActionsMenu rightLabels;
    private DiaryDatabaseHelper mHelper;
    private DiaryBean diaryBean;

    @Override
    protected int getLayout() {
        return R.layout.actviity_edit;
    }

    @Override
    protected void initData() {
        super.initData();
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
        ivBack.setVisibility(View.VISIBLE);
        // 标题
        tvTitle.setText("修改日记");
        mUpdateDiaryTvDate.setText("今天，" + DateTimeUtils.formatNowDate2String(1) + DateTimeUtils.formatNowDate2String(8));
        Bundle bundle = getIntent().getExtras();
        diaryBean = (DiaryBean) bundle.getSerializable("diaryBean");
        mUpdateDiaryEtTitle.setText(diaryBean.getTitle());
        mUpdateDiaryEtContent.setText(diaryBean.getContent());
        mTvTag.setText(diaryBean.getTag());
        mUpdateDiaryEtTitle.setSelection(mUpdateDiaryEtTitle.getText().length());
    }

    @OnClick({R.id.iv_back, R.id.update_diary_fab_back, R.id.update_diary_fab_add, R.id.update_diary_fab_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.update_diary_fab_back:
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("确定要删除该日记吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tag = mTvTag.getText().toString();
                        SQLiteDatabase dbDelete = mHelper.getWritableDatabase();
                        dbDelete.delete("Diary", "tag = ?", new String[]{tag});
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
                break;
            case R.id.update_diary_fab_add:
                SQLiteDatabase dbUpdate = mHelper.getWritableDatabase();
                ContentValues valuesUpdate = new ContentValues();
                String title = mUpdateDiaryEtTitle.getText().toString();
                String content = mUpdateDiaryEtContent.getText().toString();
                valuesUpdate.put("title", title);
                valuesUpdate.put("content", content);
                dbUpdate.update("Diary", valuesUpdate, "title = ?", new String[]{title});
                dbUpdate.update("Diary", valuesUpdate, "content = ?", new String[]{content});
                finish();
                break;
            case R.id.update_diary_fab_delete:
                rightLabels.collapse();
                break;
            default:
                break;
        }
    }
}