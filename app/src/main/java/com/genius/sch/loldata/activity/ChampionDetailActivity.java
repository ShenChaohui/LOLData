package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.Utils.ImageUtils;
import com.genius.sch.loldata.entity.Champion;


public class ChampionDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv;
    private TextView tvChampionIntroduce;
    private TextView tvChampionName;
    private TextView tvChampionTitle;
    private TextView tvChampionQuote;
    private TextView tvChampionQuoteName;

    private ImageView ivChampionRole;
    private TextView tvChampionRole;

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
        tvChampionQuote = findViewById(R.id.tv_championdetail_champion_quote);
        tvChampionQuoteName = findViewById(R.id.tv_championdetail_champion_quote_name);
        tvChampionRole = findViewById(R.id.tv_championdetail_champion_role);
        ivChampionRole = findViewById(R.id.iv_championdetail_champion_role);
        btnReadBiography = findViewById(R.id.btn_championdetail_readbiography);
        btnReadBiography.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void main() {
        Intent intent = getIntent();
        champion = (Champion) intent.getSerializableExtra("champion");
        getSupportActionBar().setTitle(champion.getName());
        ImageUtils.bindChampionImage(iv, champion.getImUrl());
        tvChampionName.setText(champion.getName());
        tvChampionTitle.setText(champion.getTitle());
        tvChampionQuote.setText(champion.getQuote());
        tvChampionQuoteName.setText("——" + champion.getName());
        tvChampionIntroduce.setText(Html.fromHtml(champion.getIntroduce()));
        if (champion.getRole2() != null) {
            tvChampionRole.setText(champion.getRole1() + " | " + champion.getRole2());
        } else {
            tvChampionRole.setText(champion.getRole1());
        }
        ivChampionRole.setImageDrawable(champion.getRoleImg(context));
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
