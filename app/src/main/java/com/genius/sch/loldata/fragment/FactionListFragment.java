package com.genius.sch.loldata.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.activity.FactionDetailActivity;
import com.genius.sch.loldata.adapter.FactionListAdapter;
import com.genius.sch.loldata.database.dao.FactionDao;
import com.genius.sch.loldata.entity.Faction;

import java.sql.SQLException;
import java.util.ArrayList;

public class FactionListFragment extends Fragment {
    private ArrayList<Faction> factions;
    private FactionListAdapter adapter;
    private ListView lv;

    public FactionListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_factionlist, null);
        lv = view.findViewById(R.id.lv_faction);
        FactionDao factionDao = new FactionDao(getActivity());
        try {
            factions = (ArrayList<Faction>) factionDao.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new FactionListAdapter(factions, getActivity());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), FactionDetailActivity.class);
                intent.putExtra("factionName", factions.get(i).getName());
                startActivity(intent);
            }
        });
        return view;
    }
}
