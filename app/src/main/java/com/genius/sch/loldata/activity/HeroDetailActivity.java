package com.genius.sch.loldata.activity;

import android.content.Intent;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.entity.HeroInfo;

public class HeroDetailActivity extends BaseActivity {
    @Override
    public int getActivity() {
        return R.layout.activity_herodetail;
    }

    @Override
    protected void initView() {
        initTitle();

    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        HeroInfo heroInfo = (HeroInfo) intent.getSerializableExtra("heroInfo");
        toolbar.setTitle(heroInfo.getName());


    }
}
