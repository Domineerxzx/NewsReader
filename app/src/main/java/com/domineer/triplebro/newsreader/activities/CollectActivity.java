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
import com.domineer.triplebro.newsreader.fragments.VideoCollectFragment;
import com.domineer.triplebro.newsreader.views.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_close_collect;
    private NavitationLayout nl_collect;
    private ViewPager vp_collect;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_collect = (ImageView) findViewById(R.id.iv_close_collect);
        nl_collect = (NavitationLayout) findViewById(R.id.nl_collect);
        vp_collect = (ViewPager) findViewById(R.id.vp_collect);
    }

    private void initData() {
        String titles[] = new String[]{"新闻","实况"};
        nl_collect.setViewPager(this, titles, vp_collect, R.color.colorLine, R.color.colorAppStyle, 16, 16, 0, 0, true);
        nl_collect.setBgLine(this, 1, R.color.colorLine);
        nl_collect.setNavLine(this, 2, R.color.colorAppStyle, 0);
        List<Fragment> collectFragmentList =  new ArrayList<>();
        collectFragmentList.add(new NewsCollectFragment());
        collectFragmentList.add(new VideoCollectFragment());
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), collectFragmentList);
        vp_collect.setAdapter(pagerAdapter);
        vp_collect.setCurrentItem(0);
    }

    private void setOnClickListener() {
        iv_close_collect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_collect:
                finish();
                break;
        }
    }
}
