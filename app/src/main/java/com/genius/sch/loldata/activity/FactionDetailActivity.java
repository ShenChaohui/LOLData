package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.database.dao.FactionDao;
import com.genius.sch.loldata.entity.Faction;

import org.xutils.x;

import java.sql.SQLException;

public class FactionDetailActivity extends BaseActivity {
    private Faction faction;
    private ImageView ivImg, ivIcon;
    private TextView tvName;
    private TextView tvintroduce;

    @Override
    public int getActivity() {
        return R.layout.activity_factiondetail;
    }

    @Override
    protected void initView() {
        initTitle();
        ivImg = findViewById(R.id.iv_factiondetail_img);
        ivIcon = findViewById(R.id.iv_factiondetail_icon);
        tvName = findViewById(R.id.tv_factiondetail_name);
        tvintroduce = findViewById(R.id.tv_factiondetail_introduce);

    }

    @Override
    protected void main() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("factionName");
        getSupportActionBar().setTitle(name);
        FactionDao dao = new FactionDao(context);
        try {
            faction = dao.queryT("name", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        x.image().bind(ivImg, faction.getImgUrl());
        ivIcon.setImageDrawable(faction.getIcon(context));
        tvName.setText(faction.getName());
        tvintroduce.setText(Html.fromHtml(faction.getIntroduce()));

    }
}
