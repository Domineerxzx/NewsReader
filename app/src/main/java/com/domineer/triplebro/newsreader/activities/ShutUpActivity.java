package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.adapters.ViewPagerAdapter;
import com.domineer.triplebro.newsreader.fragments.NewsCollectFragment;
import com.domineer.triplebro.newsreader.fragments.NotShutUpUserFragment;
import com.domineer.triplebro.newsreader.fragments.ShutUpUserFragment;
import com.domineer.triplebro.newsreader.fragments.VideoCollectFragment;
import com.domineer.triplebro.newsreader.views.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

public class ShutUpActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_close_shut_up;
    private NavitationLayout nl_shut_up;
    private ViewPager vp_shut_up;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shut_up);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_shut_up = (ImageView) findViewById(R.id.iv_close_shut_up);
        nl_shut_up = (NavitationLayout) findViewById(R.id.nl_shut_up);
        vp_shut_up = (ViewPager) findViewById(R.id.vp_shut_up);
    }

    private void initData() {
        String titles[] = new String[]{"未禁言","已禁言"};
        nl_shut_up.setViewPager(this, titles, vp_shut_up, R.color.colorLine, R.color.colorAppStyle, 16, 16, 0, 0, true);
        nl_shut_up.setBgLine(this, 1, R.color.colorLine);
        nl_shut_up.setNavLine(this, 2, R.color.colorAppStyle, 0);
        List<Fragment> shutUpFragmentList =  new ArrayList<>();
        shutUpFragmentList.add(new NotShutUpUserFragment());
        shutUpFragmentList.add(new ShutUpUserFragment());
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), shutUpFragmentList);
        vp_shut_up.setAdapter(pagerAdapter);
        vp_shut_up.setCurrentItem(0);
    }



    private void setOnClickListener() {
        iv_close_shut_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_shut_up:
                finish();
                break;
        }
    }
}
