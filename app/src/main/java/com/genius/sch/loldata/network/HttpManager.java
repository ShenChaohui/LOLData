package com.genius.sch.loldata.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.genius.sch.loldata.database.dao.HeroInfoJsonDao;
import com.genius.sch.loldata.entity.HeroInfoJson;

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
//        http://yz.lol.qq.com/v1/zh_cn/search/index.json
        RequestParams params = new RequestParams("http://yz.lol.qq.com/v1/zh_cn/search/index.json");
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
                        HeroInfoJson heroInfoJsonObj = new HeroInfoJson();
                        JSONObject heroInfoJson = champions.getJSONObject(i);
                        String name = heroInfoJson.getString("name");
                        String heroInfo = champions.getJSONObject(i).toString();
                        heroInfoJsonObj.setHeroName(name);
                        heroInfoJsonObj.setHeroInfoJson(heroInfo);
                        dao.save(heroInfoJsonObj);
                    }
                    Message msg = new Message();
                    msg.what = 200;
                    handler.sendMessage(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
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
