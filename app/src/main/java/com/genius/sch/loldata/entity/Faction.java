package com.genius.sch.loldata.entity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ScrollView;

import com.genius.sch.loldata.R;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Faction {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String slug;
    @DatabaseField
    private String imgUrl;
    @DatabaseField
    private String introduce;
    @DatabaseField
    private String associatedChampions;

    public Faction() {
    }

    public Faction(String name, String slug, String imgUrl) {
        this.name = name;
        this.slug = slug;
        this.imgUrl = imgUrl;
    }

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Drawable getIcon(Context context) {
        Drawable drawable = null;
        switch (slug) {
            case "bilgewater":
                drawable = context.getResources().getDrawable(R.mipmap.icon_bilgewater);
                break;
            case "ionia":
                drawable = context.getResources().getDrawable(R.mipmap.icon_iona);
                break;
            case "shurima":
                drawable = context.getResources().getDrawable(R.mipmap.icon_shurima);
                break;
            case "freljord":
                drawable = context.getResources().getDrawable(R.mipmap.icon_freljord);
                break;
            case "zaun":
                drawable = context.getResources().getDrawable(R.mipmap.icon_zaun);
                break;
            case "piltover":
                drawable = context.getResources().getDrawable(R.mipmap.icon_piltover);
                break;
            case "noxus":
                drawable = context.getResources().getDrawable(R.mipmap.icon_noxus);
                break;
            case "void":
                drawable = context.getResources().getDrawable(R.mipmap.icon_void);
                break;
            case "bandle-city":
                drawable = context.getResources().getDrawable(R.mipmap.icon_bandle);
                break;
            case "mount-targon":
                drawable = context.getResources().getDrawable(R.mipmap.icon_mt_targon);
                break;
            case "shadow-isles":
                drawable = context.getResources().getDrawable(R.mipmap.icon_shadow);
                break;
            case "demacia":
                drawable = context.getResources().getDrawable(R.mipmap.icon_demacia);
                break;
        }
        return drawable;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAssociatedChampions() {
        return associatedChampions;
    }

    public void setAssociatedChampions(String associatedChampions) {
        this.associatedChampions = associatedChampions;
    }
}
