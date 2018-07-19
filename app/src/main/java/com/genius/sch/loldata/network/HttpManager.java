package com.genius.sch.loldata.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.genius.sch.loldata.Utils.SharedPreferencesUtil;
import com.genius.sch.loldata.database.dao.ChampionDao;
import com.genius.sch.loldata.entity.Champion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.sql.SQLException;

public class HttpManager {


    /**
     * 获取所有英雄
     *
     * @param handler
     * @param context
     */
    public static void getChampionListData(final Handler handler, final Context context) {
        RequestParams params = new RequestParams(Urls.HEROLIST);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    ChampionDao dao = new ChampionDao(context);
                    //先清空数据库
                    dao.deleteAll();
                    JSONObject obj = new JSONObject(result);
                    JSONArray champions = obj.getJSONArray("champions");
                    for (int i = 0; i < champions.length(); i++) {
                        JSONObject heroInfoJson = champions.getJSONObject(i);
                        String name = heroInfoJson.getString("name");
                        String slug = heroInfoJson.getString("slug");
                        String faction = heroInfoJson.getString("associated-faction-slug");
                        String imUrl = heroInfoJson.getJSONObject("image").getString("uri");
                        Champion champion = new Champion(name, slug, imUrl, faction);
                        dao.save(champion);
                        getChampionDetail(dao, champion);
                    }
                    SharedPreferencesUtil.setParam(context, "isFirst", false);
                    Message message = new Message();
                    message.what = 200;
                    handler.sendMessage(message);
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
    private static void getChampionDetail(final ChampionDao dao, final Champion champion) {
        RequestParams params = new RequestParams(Urls.getHeroDetails(champion.getSlug()));
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
                    champion.setBiography(full);
                    champion.setIntroduce(_short);
                    champion.setQuote(quote);
                    champion.setTitle(title);
                    dao.update(champion);


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
