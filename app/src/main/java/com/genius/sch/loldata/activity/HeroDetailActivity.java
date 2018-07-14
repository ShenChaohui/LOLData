package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.database.dao.HeroInfoDao;
import com.genius.sch.loldata.entity.HeroInfo;
import com.genius.sch.loldata.network.HttpManager;

import org.xutils.x;

import java.sql.SQLException;

public class HeroDetailActivity extends BaseActivity {
    private ImageView iv;
    private TextView tvHeroStory;
    private TextView tvHeroName;
    private TextView tvHeroTitle;

    private String heroName;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                getHeroInfoFromSql();
            }
        }
    };

    @Override
    public int getActivity() {
        return R.layout.activity_herodetail;
    }

    @Override
    protected void initView() {
        initTitle();
        iv = findViewById(R.id.iv_herodetail);
        tvHeroStory = findViewById(R.id.tv_herodetail);
        tvHeroName = findViewById(R.id.tv_herodetail_heroName);
        tvHeroTitle = findViewById(R.id.tv_herodetail_heroTitle);
    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        heroName = intent.getStringExtra("heroInfoName");
        getSupportActionBar().setTitle(heroName);
        tvHeroName.setText(heroName);
        HeroInfo heroInfo = getHeroInfoFromSql();
        x.image().bind(iv, heroInfo.getImUrl());
    }


    private HeroInfo getHeroInfoFromSql() {
        HeroInfo heroInfo = null;
        HeroInfoDao dao = new HeroInfoDao(context);
        try {
            heroInfo = dao.queryT("name", heroName);
            initHeroInfoStory(heroInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroInfo;
    }

    private void initHeroInfoStory(HeroInfo heroInfo) {
        if (heroInfo.getStory() == null) {
            x.image().bind(iv, heroInfo.getImUrl());
            HttpManager.getHeroDetail(handler, context, heroInfo);
        } else {
            tvHeroStory.setText(Html.fromHtml(heroInfo.getStory()));
            tvHeroTitle.setText(heroInfo.getTitle());
        }
    }


}
