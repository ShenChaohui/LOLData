package com.genius.sch.loldata.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.genius.sch.loldata.database.dao.ChampionDao;
import com.genius.sch.loldata.database.dao.FactionDao;
import com.genius.sch.loldata.entity.Champion;
import com.genius.sch.loldata.entity.Faction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                    ChampionDao dao = new ChampionDao(context);
                    JSONObject obj = new JSONObject(result);
                    JSONArray champions = obj.getJSONArray("champions");
                    if (dao.queryAll().size() == champions.length()) {
                        Message message = new Message();
                        message.what = 200;
                        handler.sendMessage(message);
                    } else {
                        CHAMPION_NUM = 0;
                        dao.deleteAll();
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
    private static void getChampionDetail(final ChampionDao dao, final Champion champion, final Handler handler) {
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
                    champion.setBiography(full);
                    champion.setIntroduce(_short);
                    champion.setQuote(quote);
                    champion.setTitle(title);
                    dao.update(champion);
                    CHAMPION_NUM++;
                    if (CHAMPION_NUM == dao.queryAll().size()) {
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
                    FactionDao dao = new FactionDao(context);
                    dao.deleteAll();
                    JSONObject object = new JSONObject(result);
                    JSONArray factions = object.getJSONArray("factions");
                    if (dao.queryAll().size() == factions.length()) {
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

    public static void getFactionDetails(final FactionDao dao, final Faction faction) {
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
