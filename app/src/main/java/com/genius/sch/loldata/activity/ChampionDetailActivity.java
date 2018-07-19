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
    private TextView tvHeroShort;
    private TextView tvHeroName;
    private TextView tvHeroTitle;
    private Button btnReadBiography;


    private Champion champion;

    @Override
    public int getActivity() {
        return R.layout.activity_championdetail;
    }

    @Override
    protected void initView() {
        initTitle();
        iv = findViewById(R.id.iv_herodetail);
        tvHeroShort = findViewById(R.id.tv_herodetail_introduce);
        tvHeroName = findViewById(R.id.tv_herodetail_heroName);
        tvHeroTitle = findViewById(R.id.tv_herodetail_heroTitle);
        btnReadBiography = findViewById(R.id.btn_herodetail_readBiography);
        btnReadBiography.setOnClickListener(this);
    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        champion = (Champion) intent.getSerializableExtra("champion");
        getSupportActionBar().setTitle(champion.getName());
        tvHeroName.setText(champion.getName());
        x.image().bind(iv, champion.getImUrl());
        tvHeroTitle.setText(champion.getTitle());
        tvHeroShort.setText(champion.getIntroduce());
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_herodetail_readBiography){
            Intent intent = new Intent(context,ChampionBiography.class);
            intent.putExtra("champion", champion);
        }
    }
}
