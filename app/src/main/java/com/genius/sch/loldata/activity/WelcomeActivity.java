package com.genius.sch.loldata.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.genius.sch.loldata.R;
import com.genius.sch.loldata.network.HttpManager;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 200){
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        HttpManager.getHeroListData(handler,this);
    }
}
