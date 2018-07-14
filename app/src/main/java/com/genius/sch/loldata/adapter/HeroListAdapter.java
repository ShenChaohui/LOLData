package com.genius.sch.loldata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.Utils.GsonTtils;
import com.genius.sch.loldata.Utils.ImageUtils;
import com.genius.sch.loldata.entity.HeroInfo;
import com.genius.sch.loldata.entity.HeroInfoJson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HeroListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HeroInfoJson> data;

    public HeroListAdapter(Context context, ArrayList<HeroInfoJson> data) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_main_herolist, null);
            viewHolder.im = view.findViewById(R.id.im_item_heroinfo_img);
            viewHolder.tv = view.findViewById(R.id.tv_item_heroinfo_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String heroInfoJson = data.get(i).getHeroInfoJson();
        HeroInfo heroInfo = GsonTtils.parseJsonWithGson(heroInfoJson, HeroInfo.class);
        ImageUtils.bindImageByUrl(viewHolder.im,heroInfo.getImage().getUri());
        viewHolder.tv.setText(heroInfo.getName());
        return view;
    }

    class ViewHolder {
        ImageView im;
        TextView tv;
    }
}
