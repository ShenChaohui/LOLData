package com.genius.sch.loldata;

import android.app.Application;

import org.xutils.x;

public class LOLDataApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
