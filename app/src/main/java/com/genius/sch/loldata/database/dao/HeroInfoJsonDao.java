package com.genius.sch.loldata.database.dao;

import android.content.Context;

import com.genius.sch.loldata.database.BaseDao;
import com.genius.sch.loldata.entity.HeroInfo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class HeroInfoJsonDao extends BaseDao<HeroInfo, Integer> {
    public HeroInfoJsonDao(Context context) {
        super(context);
    }

    @Override
    public Dao<HeroInfo, Integer> getDao() throws SQLException {
        return mDatabaseHelper.getDao(HeroInfo.class);
    }
}
