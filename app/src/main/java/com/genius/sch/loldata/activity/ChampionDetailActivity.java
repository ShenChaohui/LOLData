package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.adapter.ChampionDetailRelatedChampionListAdapter;
import com.genius.sch.loldata.utils.ImageUtils;
import com.genius.sch.loldata.database.BaseDao;
import com.genius.sch.loldata.database.BaseDaoImpl;
import com.genius.sch.loldata.entity.Champion;
import com.genius.sch.loldata.entity.Faction;
import com.genius.sch.loldata.view.HorizontalListView;
import com.genius.sch.loldata.view.ScollListView;

import java.sql.SQLException;
import java.util.ArrayList;


public class ChampionDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv;
    private TextView tvChampionIntroduce;
    private TextView tvChampionName;
    private TextView tvChampionTitle;
    private TextView tvChampionQuote;
    private TextView tvChampionQuoteName;

    private ImageView ivChampionRole;
    private TextView tvChampionRole;

    private ImageView ivChampionFaction;
    private TextView tvChampionFaction;

    private Button btnReadBiography;

    private ScollListView slvRelatedChampions;

    private Champion champion;
    private ArrayList<Champion> relatedChampionsList;

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
        ivChampionFaction = findViewById(R.id.iv_championdetail_champion_faction);
        tvChampionFaction = findViewById(R.id.tv_championdetail_champion_faction);
        slvRelatedChampions = findViewById(R.id.slv_champion_related_champions);
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
        ivChampionFaction.setImageDrawable(champion.getFactionIcon(context));
        tvChampionFaction.setText(champion.getFaction());

        BaseDao<Champion, Integer> championDao = new BaseDaoImpl<>(context, Champion.class);
        String relatedChampions = champion.getRelated_champions();
        String champions[] = relatedChampions.split(";");
        relatedChampionsList = new ArrayList<>();
        for (int i = 0; i < champions.length; i++) {
            try {
                relatedChampionsList.add(championDao.queryT("name", champions[i]));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ChampionDetailRelatedChampionListAdapter adapter = new ChampionDetailRelatedChampionListAdapter(context, relatedChampionsList);
        slvRelatedChampions.setAdapter(adapter);
        slvRelatedChampions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, ChampionDetailActivity.class);
                intent.putExtra("champion", relatedChampionsList.get(i));
                startActivity(intent);
                finish();
            }
        });
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
