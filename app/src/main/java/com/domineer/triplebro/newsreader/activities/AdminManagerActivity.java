package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.newsreader.R;

public class AdminManagerActivity extends Activity implements View.OnClickListener {

    private TextView tv_shut_up;
    private ImageView iv_close_admin_manager;
    private TextView tv_submit_news;
    private TextView tv_admin_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager);
        initView();
        setOnClickListener();
    }

    private void initView() {
        tv_shut_up = (TextView) findViewById(R.id.tv_shut_up);
        tv_submit_news = (TextView) findViewById(R.id.tv_submit_news);
        tv_admin_info = (TextView) findViewById(R.id.tv_admin_info);
        iv_close_admin_manager = (ImageView) findViewById(R.id.iv_close_admin_manager);
    }

    private void setOnClickListener() {
        tv_shut_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_shut_up:
                Intent shutUp = new Intent(this, ShutUpActivity.class);
                startActivity(shutUp);
                break;
            case R.id.tv_submit_news:
                break;
            case R.id.tv_admin_info:
                break;
            case R.id.iv_close_admin_manager:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = adminInfo.edit();
        edit.clear();
        edit.commit();
    }
}
