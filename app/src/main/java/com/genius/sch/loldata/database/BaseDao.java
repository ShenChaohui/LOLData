package com.genius.sch.loldata.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Genius on 2017/7/7/0007.
 */

public abstract class BaseDao<T, Integer> {
    protected DatabaseHelper mDatabaseHelper;

    protected Context mContext;

    public BaseDao(Context context) {
        mContext = context;
        getHelper();
    }

    public DatabaseHelper getHelper() {
        mDatabaseHelper = DatabaseHelper.getHelper(mContext);
        return mDatabaseHelper;
    }

    public abstract Dao<T, Integer> getDao() throws SQLException;

    /**
     * 存入
     *
     * @param t 存入类型实体
     * @return
     * @throws SQLException
     */
    public int save(T t) throws SQLException {
        return getDao().create(t);
    }

    public void saveList(List<T> list) throws SQLException {
        for (T t : list) {
            getDao().create(t);
        }
    }

    /**
     * 查询
     *
     * @param preparedQuery
     * @return
     * @throws SQLException
     */
    public List<T> query(PreparedQuery<T> preparedQuery) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.query(preparedQuery);
    }

    /**
     * 条件查询
     *
     * @param attributeName  属性名
     * @param attributeValue 属性值
     * @return 符合查询条件的list
     * @throws SQLException
     */
    public List<T> query(String attributeName, String attributeValue) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        queryBuilder.where().eq(attributeName, attributeValue);
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * 条件查询
     *
     * @param attributeNames  属性名[]
     * @param attributeValues 属性值[]
     * @return 符合条件的list
     * @throws SQLException
     */
    public List<T> query(String[] attributeNames, String[] attributeValues) throws SQLException {
        if (attributeNames.length != attributeValues.length) {
            throw new SQLException("params size is not equal");
        }
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        for (int i = 0; i < attributeNames.length; i++) {
            wheres.eq(attributeNames[i], attributeValues[i]);
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * 查询所有
     *
     * @return 数据库中所有数据的list
     * @throws SQLException
     */
    public List<T> queryAll() throws SQLException {
        // QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        // PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        // return query(preparedQuery);
        Dao<T, Integer> dao = getDao();
        List<T> list = dao.queryForAll();
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    /**
     * 条件查询
     *
     * @param idName  属性名
     * @param idValue 属性值
     * @return *符合条件的一个实体,如果有多个，返回第一个*
     * @throws SQLException
     */
    public T queryT(String idName, String idValue) throws SQLException {
        List<T> lst = query(idName, idValue);
        if (null != lst && !lst.isEmpty()) {
            return lst.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除
     *
     * @param preparedDelete
     * @return
     * @throws SQLException
     */
    public int delete(PreparedDelete<T> preparedDelete) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(preparedDelete);
    }

    /**
     * 删除-单个实体
     *
     * @param t 需要删除的实体
     * @return
     * @throws SQLException
     */
    public int delete(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.delete(t);
    }

    /**
     * 删除-list
     *
     * @param lst 需要删除的list
     * @return
     * @throws SQLException
     */
    public int delete(List<T> lst) throws SQLException {
        Dao<T, Integer> dao = getDao();
        if (lst == null) {
            lst = new ArrayList<>();
        }
        return dao.delete(lst);
    }

    /**
     * 条件删除
     *
     * @param attributeNames  属性名集合
     * @param attributeValues 属性值集合
     * @return
     * @throws SQLException
     */
    public int delete(String[] attributeNames, String[] attributeValues) throws SQLException
    // , InvalidParamsException
    {
        List<T> lst = query(attributeNames, attributeValues);
        if (null != lst && !lst.isEmpty()) {
            return delete(lst);
        }
        return 0;
    }

    /**
     * 条件删除T
     * <p>
     * 属性名
     * 属性值
     *
     * @return
     * @throws SQLException
     */
    public int deleteT(String idName, String idValue) throws SQLException
    // , InvalidParamsException
    {
        T t = queryT(idName, idValue);
        if (null != t) {
            return delete(t);
        }
        return 0;
    }

    /**
     * 条件删除List
     *
     * @param idName  属性名
     * @param idValue 属性值
     * @return
     * @throws SQLException
     */
    public int deleteL(String idName, String idValue) throws SQLException
    // , InvalidParamsException
    {
        List<T> tlist = query(idName, idValue);
        for (int i = 0; i < tlist.size(); i++) {
            T t = tlist.get(i);
            if (null != t) {
                return delete(t);
            }
        }
//		T t = queryT(idName, idValue);
//		if (null != t) {
//			return delete(t);
//		}
        return 0;
    }
    /**
     * 删除所有
     *
     * @throws SQLException
     */
    public void deleteAll() throws SQLException {
        Dao<T, Integer> dao = getDao();
        List<T> list = dao.queryForAll();
//        for (T t: list
//             ) {
//            dao.delete(t);
//        }
        if (list == null) {
            list = new ArrayList<>();
        }
        dao.delete(list);
    }

    /**
     * 更新
     *
     * @param t 需要更新的实体
     * @return
     * @throws SQLException
     */
    public int update(T t) throws SQLException {
        Dao<T, Integer> dao = getDao();
        return dao.update(t);
    }

    public boolean isTableExsits() throws SQLException {
        return getDao().isTableExists();
    }

    public long countOf() throws SQLException {
        return getDao().countOf();
    }

    public List<T> query(Map<String, Object> map) throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        if (!map.isEmpty()) {
            Where<T, Integer> wheres = queryBuilder.where();
            Set<String> keys = map.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (i == 0) {
                    wheres.eq(keyss.get(i), map.get(keyss.get(i)));
                } else {
                    wheres.and().eq(keyss.get(i), map.get(keyss.get(i)));
                }
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**
     * 查询
     *
     * @param map     查询Map<String, Object>
     * @param lowMap  下限Map<String, Object>(包含)
     * @param highMap 上限限Map<String, Object>(不包含)
     * @return
     * @throws SQLException
     */
    public List<T> query(Map<String, Object> map, Map<String, Object> lowMap, Map<String, Object> highMap)
            throws SQLException {
        QueryBuilder<T, Integer> queryBuilder = getDao().queryBuilder();
        Where<T, Integer> wheres = queryBuilder.where();
        if (!map.isEmpty()) {
            Set<String> keys = map.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (i == 0) {
                    wheres.eq(keyss.get(i), map.get(keyss.get(i)));
                } else {
                    wheres.and().eq(keyss.get(i), map.get(keyss.get(i)));
                }
            }
        }
        if (!lowMap.isEmpty()) {
            Set<String> keys = lowMap.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (map.isEmpty()) {
                    wheres.gt(keyss.get(i), lowMap.get(keyss.get(i)));
                } else {
                    wheres.and().gt(keyss.get(i), lowMap.get(keyss.get(i)));
                }
            }
        }

        if (!highMap.isEmpty()) {
            Set<String> keys = highMap.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                wheres.and().lt(keyss.get(i), highMap.get(keyss.get(i)));
            }
        }
        PreparedQuery<T> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }
}
