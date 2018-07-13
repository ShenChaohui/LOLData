package com.genius.sch.loldata.database.dao;

import android.content.Context;

import com.genius.sch.loldata.database.BaseDao;
import com.genius.sch.loldata.entity.HeroInfoJSON;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class HeroInfoDao extends BaseDao<HeroInfoJSON,Integer> {
    public HeroInfoDao(Context context) {
        super(context);
    }

    @Override
    public Dao<HeroInfoJSON, Integer> getDao() throws SQLException {
        return mDatabaseHelper.getDao(HeroInfoJSON.class);
    }
}
