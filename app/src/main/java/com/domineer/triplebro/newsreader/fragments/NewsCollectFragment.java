package com.domineer.triplebro.newsreader.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.NewsActivity;
import com.domineer.triplebro.newsreader.adapters.HomeAdapter;
import com.domineer.triplebro.newsreader.controllers.CollectController;
import com.domineer.triplebro.newsreader.models.NewsInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/3,23:27
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class NewsCollectFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment_news_collect;
    private ListView lv_news;
    private CollectController collectController;
    private List<NewsInfo> newsInfoList;
    private HomeAdapter homeAdapter;
    private SharedPreferences userInfo;
    private int user_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_news_collect = inflater.inflate(R.layout.fragment_news_collect, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_news_collect;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initView() {
        lv_news = (ListView) fragment_news_collect.findViewById(R.id.lv_news);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        collectController = new CollectController(getActivity());
        newsInfoList = collectController.findCollectNewsInfoList(user_id);
        homeAdapter = new HomeAdapter(getActivity(), newsInfoList);
        lv_news.setAdapter(homeAdapter);
    }

    private void setOnClickListener() {
        lv_news.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent news = new Intent(getActivity(), NewsActivity.class);
        news.putExtra("newsInfo",newsInfoList.get(position));
        getActivity().startActivity(news);
    }
}
