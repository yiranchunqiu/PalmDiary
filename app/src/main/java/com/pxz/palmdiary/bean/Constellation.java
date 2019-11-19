package com.pxz.palmdiary.bean;

import java.io.Serializable;

/**
 * 类说明：星座实体类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/17 15:19
 */
public class Constellation implements Serializable{
    private int id;
    private String name;
    private String head;
    private String img;
    private String icon;
    private String date;
    private String info;
    private int whole;
    private int love;
    private int career;
    private int money;
    private String whole_info;
    private String love_info;
    private String career_info;
    private String money_info;
    private String health_info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getWhole() {
        return whole;
    }

    public void setWhole(int whole) {
        this.whole = whole;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getWhole_info() {
        return whole_info;
    }

    public void setWhole_info(String whole_info) {
        this.whole_info = whole_info;
    }

    public String getLove_info() {
        return love_info;
    }

    public void setLove_info(String love_info) {
        this.love_info = love_info;
    }

    public String getCareer_info() {
        return career_info;
    }

    public void setCareer_info(String career_info) {
        this.career_info = career_info;
    }

    public String getMoney_info() {
        return money_info;
    }

    public void setMoney_info(String money_info) {
        this.money_info = money_info;
    }

    public String getHealth_info() {
        return health_info;
    }

    public void setHealth_info(String health_info) {
        this.health_info = health_info;
    }
}