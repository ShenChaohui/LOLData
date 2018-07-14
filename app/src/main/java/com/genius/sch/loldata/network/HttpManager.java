package com.genius.sch.loldata.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.genius.sch.loldata.database.dao.HeroInfoJsonDao;
import com.genius.sch.loldata.entity.HeroInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.sql.SQLException;

public class HttpManager {


    /**
     * 获取英雄列表
     *
     * @param handler
     * @param context
     */
    public static void getHeroListData(final Handler handler, final Context context) {
        RequestParams params = new RequestParams(Urls.HeroList);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    HeroInfoJsonDao dao = new HeroInfoJsonDao(context);
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
                        HeroInfo heroInfo = new HeroInfo(name, slug, imUrl, faction);
                        dao.save(heroInfo);
                    }
                    Message msg = new Message();
                    msg.what = 200;
                    handler.sendMessage(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSONException",e.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.e("SQLException",e.toString());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("onError",ex.toString());

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
