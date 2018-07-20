package com.genius.sch.loldata.entity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.genius.sch.loldata.R;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Champion implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;//中文名
    @DatabaseField
    private String title;//辛德拉
    @DatabaseField
    private String slug;//英文名
    @DatabaseField
    private String imUrl;//原画
    @DatabaseField
    private String faction;//地区
    @DatabaseField
    private String biography;//传记
    @DatabaseField
    private String introduce;//简介
    @DatabaseField
    private String quote;//引述

    @DatabaseField
    private String role1;//类型1
    @DatabaseField
    private String role2;//类型2

    public Champion() {
    }

    public Champion(String name, String slug, String imUrl, String faction) {
        this.name = name;
        this.slug = slug;
        this.imUrl = imUrl;
        this.faction = faction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImUrl() {
        return imUrl;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }

    public String getFaction() {
        switch (faction) {
            case "bilgewater":
                return "比尔吉沃特";
            case "ionia":
                return "艾欧尼亚";
            case "shurima":
                return "恕瑞玛";
            case "freljord":
                return "弗雷尔卓德";
            case "zaun":
                return "祖安";
            case "piltover":
                return "皮尔特沃夫";
            case "noxus":
                return "诺克萨斯";
            case "void":
                return "虚空之地";
            case "bandle-city":
                return "班德尔城";
            case "mount-targon":
                return "巨神峰";
            case "shadow-isles":
                return "暗影岛";
            case "demacia":
                return "德玛西亚";
            case "unaffiliated":
                return "符文之地";
            default:
                return "无地区";
        }
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getRole1() {
        return role1;
    }

    public void setRole1(String role1) {
        this.role1 = role1;
    }

    public String getRole2() {
        return role2;
    }

    public void setRole2(String role2) {
        this.role2 = role2;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Drawable getRoleImg(Context context) {
        Drawable imageView = null;
        if (role1.equals("战士")) {
            imageView = context.getDrawable(R.mipmap.role_icon_fighter);
        } else if (role1.equals("法师")) {
            imageView = context.getDrawable(R.mipmap.role_icon_mage);
        } else if (role1.equals("射手")) {
            imageView = context.getDrawable(R.mipmap.role_icon_marksman);
        } else if (role1.equals("辅助")) {
            imageView = context.getDrawable(R.mipmap.role_icon_support);
        } else if (role1.equals("刺客")) {
            imageView = context.getDrawable(R.mipmap.role_icon_assassin);
        }

        return imageView;

    }
}
