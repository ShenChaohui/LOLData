package com.genius.sch.loldata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.utils.ImageUtils;
import com.genius.sch.loldata.entity.Faction;

import java.util.ArrayList;

public class FactionListAdapter extends BaseAdapter {
    private ArrayList<Faction> data;
    private Context context;

    public FactionListAdapter(ArrayList<Faction> data, Context context) {
        this.data = data;
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_factionlist, null);
            viewHolder.img = view.findViewById(R.id.iv_item_faction_img);
            viewHolder.tvName = view.findViewById(R.id.tv_item_faction_name);
            viewHolder.icon = view.findViewById(R.id.iv_item_faction_icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Faction faction = data.get(i);
        ImageUtils.bindFactionImage(viewHolder.img, faction.getImgUrl());
        viewHolder.tvName.setText(faction.getName());
        viewHolder.icon.setImageDrawable(faction.getIcon(context));

        return view;
    }

    class ViewHolder {
        ImageView img;
        ImageView icon;
        TextView tvName;
    }
}
