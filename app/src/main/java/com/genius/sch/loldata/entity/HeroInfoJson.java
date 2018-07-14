package com.genius.sch.loldata.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class HeroInfoJson {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String heroName;
    @DatabaseField
    private String heroInfoJson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroInfoJson() {
        return heroInfoJson;
    }

    public void setHeroInfoJson(String heroInfoJson) {
        this.heroInfoJson = heroInfoJson;
    }
}
