package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.Utils.ImageUtils;
import com.genius.sch.loldata.adapter.FactionDetailChampionListAdapter;
import com.genius.sch.loldata.database.BaseDao;
import com.genius.sch.loldata.database.BaseDaoImpl;
import com.genius.sch.loldata.entity.Champion;
import com.genius.sch.loldata.entity.Faction;
import com.genius.sch.loldata.view.ScollListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class FactionDetailActivity extends BaseActivity {
    private Faction faction;
    private ImageView ivImg, ivIcon;
    private TextView tvName;
    private TextView tvIntroduce;
    private ScollListView slvChampions;
    private ArrayList<Champion> championArrayList;
    private FactionDetailChampionListAdapter adapter;
    private TextView tvChampionsTitle;

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
        tvIntroduce = findViewById(R.id.tv_factiondetail_introduce);
        slvChampions = findViewById(R.id.slv_faction_champions);
        slvChampions.setFocusable(false);
        tvChampionsTitle = findViewById(R.id.tv_factiondetail_champions_title);
    }

    @Override
    protected void main() {
        final Intent intent = getIntent();
        String name = intent.getStringExtra("factionName");
        getSupportActionBar().setTitle(name);
        tvChampionsTitle.setText(name + "地区的英雄们");
        BaseDao<Faction, Integer> dao = new BaseDaoImpl<>(context, Faction.class);
        try {
            faction = dao.query("name", name).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageUtils.bindFactionImage(ivImg, faction.getImgUrl());
        ivIcon.setImageDrawable(faction.getIcon(context));
        tvName.setText(faction.getName());
        tvIntroduce.setText(Html.fromHtml(faction.getIntroduce()));
        BaseDao<Champion, Integer> championDao = new BaseDaoImpl<>(context, Champion.class);
        final String champions[] = faction.getAssociatedChampions().split(";");
        championArrayList = new ArrayList<>();
        for (int i = 0; i < champions.length; i++) {
            try {
                championArrayList.add(championDao.query("name", champions[i]).get(0));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        adapter = new FactionDetailChampionListAdapter(context, championArrayList);
        slvChampions.setAdapter(adapter);
        slvChampions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(context, ChampionDetailActivity.class);
                intent1.putExtra("champion", championArrayList.get(i));
                startActivity(intent1);
            }
        });
    }
}
