package com.pxz.myapp.bean;

import java.io.Serializable;

/**
 * 类说明：日记数据
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 13:37
 */
public class DiaryBean implements Serializable{
    private String date;
    private String time;
    private String title;
    private String content;
    private String tag;

    public DiaryBean(String date, String time, String title, String content, String tag) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}