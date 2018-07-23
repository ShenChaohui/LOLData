package com.genius.sch.loldata.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.adapter.ChampionListFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 位于主页activity中的fragment
 */
public class ChampionFragment extends Fragment {
    private TabLayout mTabLyout;
    private ViewPager mViewPager;
    private String roles[] = {"战士", "坦克", "刺客", "法师", "射手", "辅助"};
    private List<Fragment> mFragments;
    private List<String> mTitles;
    private ChampionListFragmentAdapter adapter;

    public ChampionFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion, null);
        mTabLyout = view.findViewById(R.id.tl_main);
        mViewPager = view.findViewById(R.id.vp_main);
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        for (int i = 0; i < roles.length; i++) {
            mTitles.add(roles[i]);
            mFragments.add(ChampionListFragment.newInstance(roles[i]));
        }
        adapter = new ChampionListFragmentAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
        mTabLyout.setupWithViewPager(mViewPager);

        return view;
    }
}
