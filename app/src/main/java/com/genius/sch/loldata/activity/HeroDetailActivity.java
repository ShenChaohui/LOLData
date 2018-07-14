package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.entity.HeroInfo;

import org.xutils.x;


public class HeroDetailActivity extends BaseActivity {
    private ImageView iv;
    private TextView tvHeroShort;
    private TextView tvHeroName;
    private TextView tvHeroTitle;

    private HeroInfo heroInfo;

    @Override
    public int getActivity() {
        return R.layout.activity_herodetail;
    }

    @Override
    protected void initView() {
        initTitle();
        iv = findViewById(R.id.iv_herodetail);
        tvHeroShort = findViewById(R.id.tv_herodetail_introduce);
        tvHeroName = findViewById(R.id.tv_herodetail_heroName);
        tvHeroTitle = findViewById(R.id.tv_herodetail_heroTitle);
    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        heroInfo = (HeroInfo) intent.getSerializableExtra("heroInfo");
        getSupportActionBar().setTitle(heroInfo.getName());
        tvHeroName.setText(heroInfo.getName());
        x.image().bind(iv, heroInfo.getImUrl());
        tvHeroTitle.setText(heroInfo.getTitle());
        tvHeroShort.setText(heroInfo.getIntroduce());
    }


}
