package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.entity.Champion;

import org.xutils.x;


public class ChampionDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv;
    private TextView tvChampionIntroduce;
    private TextView tvChampionName;
    private TextView tvChampionTitle;
    private Button btnReadBiography;


    private Champion champion;

    @Override
    public int getActivity() {
        return R.layout.activity_championdetail;
    }

    @Override
    protected void initView() {
        initTitle();
        iv = findViewById(R.id.iv_championdetail);
        tvChampionIntroduce = findViewById(R.id.tv_championdetail_champion_introduce);
        tvChampionName = findViewById(R.id.tv_championdetail_champion_name);
        tvChampionTitle = findViewById(R.id.tv_championdetail_champion_title);
        btnReadBiography = findViewById(R.id.btn_championdetail_readbiography);
        btnReadBiography.setOnClickListener(this);
    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        champion = (Champion) intent.getSerializableExtra("champion");
        getSupportActionBar().setTitle(champion.getName());
        tvChampionName.setText(champion.getName());
        x.image().bind(iv, champion.getImUrl());
        tvChampionTitle.setText(champion.getTitle());
        tvChampionIntroduce.setText(champion.getIntroduce());
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_championdetail_readbiography) {
            Intent intent = new Intent(context, ChampionBiographyActivity.class);
            intent.putExtra("champion", champion);
            startActivity(intent);
        }
    }
}
