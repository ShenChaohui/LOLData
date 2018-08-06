package com.genius.sch.loldata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.entity.Champion;
import com.genius.sch.loldata.utils.ImageUtils;

import java.util.ArrayList;

public class ChampionDetailRelatedChampionListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Champion> data;

    public ChampionDetailRelatedChampionListAdapter(Context context, ArrayList<Champion> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_chamption_related_champions, null);
            viewHolder.im = view.findViewById(R.id.iv_item_champion_related_champions_img);
            viewHolder.tvName = view.findViewById(R.id.tv_item_champion_related_champions_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Champion champion = data.get(i);
        ImageUtils.bindChampionImageIcon(viewHolder.im, champion.getImUrl());
        viewHolder.tvName.setText(champion.getName());

        return view;
    }

    class ViewHolder {
        ImageView im;
        TextView tvName;
    }
}
