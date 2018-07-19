package com.genius.sch.loldata.database.dao;

import android.content.Context;

import com.genius.sch.loldata.database.BaseDao;
import com.genius.sch.loldata.entity.Champion;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class ChampionDao extends BaseDao<Champion, Integer> {
    public ChampionDao(Context context) {
        super(context);
    }

    @Override
    public Dao<Champion, Integer> getDao() throws SQLException {
        return mDatabaseHelper.getDao(Champion.class);
    }
}
