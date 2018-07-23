package com.genius.sch.loldata.database.dao;

import android.content.Context;

import com.genius.sch.loldata.database.BaseDao;
import com.genius.sch.loldata.entity.Faction;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class FactionDao extends BaseDao<Faction,Integer> {
    public FactionDao(Context context) {
        super(context);
    }

    @Override
    public Dao<Faction, Integer> getDao() throws SQLException {
        return mDatabaseHelper.getDao(Faction.class);
    }
}
