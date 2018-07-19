package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.entity.Champion;

import org.xutils.x;

public class ChampionBiographyActivity extends BaseActivity {
    private ImageView iv;
    private TextView tvName;
    private TextView tvTitle;
    private TextView tvBiography;

    @Override
    public int getActivity() {
        return R.layout.activity_championbiography;
    }

    @Override
    protected void initView() {
        initTitle();
        iv = findViewById(R.id.iv_biography);
        tvName = findViewById(R.id.tv_biography_champion_name);
        tvTitle = findViewById(R.id.tv_biography_champion_title);
        tvBiography = findViewById(R.id.tv_biography_biography);
    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        Champion champion = (Champion) intent.getSerializableExtra("champion");
        x.image().bind(iv, champion.getImUrl());
        tvName.setText(champion.getName());
        tvTitle.setText(champion.getTitle());
        tvBiography.setText(Html.fromHtml(champion.getBiography()));
    }
}
