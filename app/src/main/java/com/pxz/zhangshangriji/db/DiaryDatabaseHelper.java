package com.pxz.zhangshangriji.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类说明：db
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 14:02
 */
public class DiaryDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    /**
     * 建了一张表
     */
    public static final String CREATE_DIARY = "create table Diary("
            + "id integer primary key autoincrement, "//主键ID自动递增
            + "date text, "//日期
            + "time text, "//日期
            + "title text, "//主题
            + "tag text, "//标签
            + "content text)";//内容

    public DiaryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Diary");
        onCreate(db);
    }
}
