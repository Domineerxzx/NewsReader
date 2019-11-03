package com.domineer.triplebro.newsreader.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.NewsActivity;
import com.domineer.triplebro.newsreader.activities.SearchActivity;
import com.domineer.triplebro.newsreader.adapters.HomeAdapter;
import com.domineer.triplebro.newsreader.controllers.HomeController;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.utils.imageUtils.GlideImageLoader;
import com.domineer.triplebro.newsreader.views.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, OnBannerListener {

    private View fragment_home;
    private Banner bn_banner;
    private FrameLayout fl_search;
    private RelativeLayout rl_search;
    private ImageView iv_search;
    private TextView tv_search;
    private RecyclerView rv_home;
    private MyListView mlv_home;
    private HomeController homeController;
    private List<NewsInfo> newsInfoList;
    private List<NewsInfo> bannerNewsList;
    private HomeAdapter homeAdapter;
    private List<String> bannerImageList;
    private SharedPreferences userInfo;
    private int user_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_home = inflater.inflate(R.layout.fragment_home, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_home;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void setOnClickListener() {
       fl_search.setOnClickListener(this);
       rl_search.setOnClickListener(this);
       iv_search.setOnClickListener(this);
       tv_search.setOnClickListener(this);
       mlv_home.setOnItemClickListener(this);
        bn_banner.setOnBannerListener(this);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        homeController = new HomeController(getActivity());
        newsInfoList = homeController.findNewsListOrderByTimeDesc();
        bannerNewsList = homeController.findBannerNewsList();
        bannerImageList = new ArrayList<>();
        for (NewsInfo newsInfo : bannerNewsList) {
            bannerImageList.add(newsInfo.getImage());
        }
        bn_banner.setImages(bannerImageList);
        bn_banner.start();
        homeAdapter = new HomeAdapter(getActivity(), newsInfoList);
        mlv_home.setAdapter(homeAdapter);
    }

    private void initView() {
        bn_banner = fragment_home.findViewById(R.id.bn_banner);
        fl_search = fragment_home.findViewById(R.id.fl_search);
        fl_search.bringToFront();
        rl_search = fragment_home.findViewById(R.id.rl_search);
        iv_search = fragment_home.findViewById(R.id.iv_search);
        tv_search = fragment_home.findViewById(R.id.tv_search);
        mlv_home = fragment_home.findViewById(R.id.mlv_home);
        bn_banner.setImageLoader(new GlideImageLoader());
        bn_banner.isAutoPlay(true);
        bn_banner.setDelayTime(5000);
        bn_banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fl_search:
            case R.id.rl_search:
            case R.id.iv_search:
            case R.id.tv_search:
                if(user_id == -1){
                    Toast.makeText(getActivity(), "还没登录呢，不能使用搜索功能！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent search = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(search);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent news = new Intent(getActivity(), NewsActivity.class);
        news.putExtra("newsInfo",newsInfoList.get(position));
        getActivity().startActivity(news);
    }

    @Override
    public void OnBannerClick(int position) {
        Intent news = new Intent(getActivity(), NewsActivity.class);
        news.putExtra("newsInfo",bannerNewsList.get(position));
        getActivity().startActivity(news);
    }
}
