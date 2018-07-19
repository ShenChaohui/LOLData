package com.genius.sch.loldata.entity;

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
    private String title;//辛德拉

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
        return faction;
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
}
