package com.genius.sch.loldata.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.genius.sch.loldata.database.BaseDao;
import com.genius.sch.loldata.database.BaseDaoImpl;
import com.genius.sch.loldata.entity.Champion;
import com.genius.sch.loldata.entity.Faction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.sql.SQLException;

public class HttpManager {
    private static int CHAMPION_NUM;

    /**
     * 获取所有英雄
     *
     * @param handler
     * @param context
     */
    public static void getChampionListData(final Handler handler, final Context context) {
        RequestParams params = new RequestParams(Urls.GET_CHAMPION_LIST);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    BaseDao<Champion, Integer> dao = new BaseDaoImpl<>(context, Champion.class);
                    JSONObject obj = new JSONObject(result);
                    JSONArray champions = obj.getJSONArray("champions");
                    //判断本地是否存在所有英雄，如果存在，直接跳转
                    if (dao.count() == champions.length()) {
                        Message message = new Message();
                        message.what = 200;
                        handler.sendMessage(message);
                    } else {
                        CHAMPION_NUM = 0;
                        dao.delete(dao.queryAll());
                        for (int i = 0; i < champions.length(); i++) {
                            JSONObject heroInfoJson = champions.getJSONObject(i);
                            String name = heroInfoJson.getString("name");
                            String slug = heroInfoJson.getString("slug");
                            String faction = heroInfoJson.getString("associated-faction-slug");
                            String imUrl = heroInfoJson.getJSONObject("image").getString("uri");
                            Champion champion = new Champion(name, slug, imUrl, faction);
                            dao.save(champion);
                            getChampionDetail(dao, champion, handler);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError", ex.toString());

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取英雄详情数据
     *
     * @param dao
     * @param champion
     */
    private static void getChampionDetail(final BaseDao dao, final Champion champion, final Handler handler) {
        RequestParams params = new RequestParams(Urls.getChampionDetails(champion.getSlug()));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject championObj = object.getJSONObject("champion");
                    String title = championObj.getString("title");

                    JSONObject biography = championObj.getJSONObject("biography");
                    String full = biography.getString("full");
                    String _short = biography.getString("short");
                    String quote = biography.getString("quote");
                    JSONArray roles = championObj.getJSONArray("roles");
                    String role1 = roles.getJSONObject(0).getString("name");
                    champion.setRole1(role1);
                    if (roles.length() > 1) {
                        String role2 = roles.getJSONObject(1).getString("name");
                        champion.setRole2(role2);
                    }
                    JSONArray related_champions = object.getJSONArray("related-champions");
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < related_champions.length(); i++) {
                        if (i == related_champions.length() - 1) {
                            sb.append(related_champions.getJSONObject(i).getString("name"));
                        } else {
                            sb.append(related_champions.getJSONObject(i).getString("name") + ";");
                        }
                    }
                    champion.setRelated_champions(sb.toString());
                    champion.setBiography(full);
                    champion.setIntroduce(_short);
                    champion.setQuote(quote);
                    champion.setTitle(title);
                    dao.update(champion);
                    CHAMPION_NUM++;
                    if (CHAMPION_NUM == dao.count()) {
                        Message message = new Message();
                        message.what = 200;
                        handler.sendMessage(message);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void getFactionListData(final Context context) {
        RequestParams params = new RequestParams(Urls.GET_FACTION);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    BaseDao<Faction, Integer> dao = new BaseDaoImpl<>(context, Faction.class);
                    dao.delete(dao.queryAll());
                    JSONObject object = new JSONObject(result);
                    JSONArray factions = object.getJSONArray("factions");
                    if (dao.count() == factions.length()) {
                        return;
                    }
                    for (int i = 0; i < factions.length(); i++) {
                        JSONObject factionObj = factions.getJSONObject(i);
                        String name = factionObj.getString("name");
                        String slug = factionObj.getString("slug");
                        String imgUrl = factionObj.getJSONObject("image").getString("uri");
                        Faction faction = new Faction(name, slug, imgUrl);
                        dao.save(faction);
                        getFactionDetails(dao, faction);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSONException", e.toString());

                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.e("SQLException", e.toString());

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("error", ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void getFactionDetails(final BaseDao dao, final Faction faction) {
        RequestParams params = new RequestParams(Urls.getFactionDetails(faction.getSlug()));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    String introduce = object.getJSONObject("faction").getJSONObject("overview").getString("short");
                    JSONArray associated_Champions = object.getJSONArray("associated-champions");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < associated_Champions.length(); i++) {
                        if (i == associated_Champions.length() - 1) {
                            sb.append(associated_Champions.getJSONObject(i).getString("name"));
                        } else {
                            sb.append(associated_Champions.getJSONObject(i).getString("name") + ";");
                        }
                    }
                    faction.setIntroduce(introduce);
                    faction.setAssociatedChampions(sb.toString());
                    dao.update(faction);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }
}
