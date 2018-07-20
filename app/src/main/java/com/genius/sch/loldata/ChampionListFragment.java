package com.genius.sch.loldata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.genius.sch.loldata.activity.ChampionDetailActivity;
import com.genius.sch.loldata.activity.MainActivity;
import com.genius.sch.loldata.adapter.ChampionListAdapter;
import com.genius.sch.loldata.database.dao.ChampionDao;
import com.genius.sch.loldata.entity.Champion;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChampionListFragment extends Fragment {
    private String role;

    private ListView lvChampionList;

    private ArrayList<Champion> champions;
    private ChampionListAdapter adapter;

    public static ChampionListFragment newInstance(String role) {
        Bundle bundle = new Bundle();
        bundle.putString("role", role);
        ChampionListFragment fragment = new ChampionListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_championlist, null);
        role = getArguments().getString("role");
        lvChampionList = view.findViewById(R.id.lv_championlist);
        ChampionDao dao = new ChampionDao(getActivity());
        try {
            champions = (ArrayList<Champion>) dao.query("role", role);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new ChampionListAdapter(getActivity(), champions);
        lvChampionList.setAdapter(adapter);

        lvChampionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChampionDetailActivity.class);
                intent.putExtra("champion", champions.get(i));
                startActivity(intent);
            }
        });

        return view;

    }
}
