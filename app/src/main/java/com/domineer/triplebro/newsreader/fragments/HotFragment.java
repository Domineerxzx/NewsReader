package com.domineer.triplebro.newsreader.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.NewsActivity;
import com.domineer.triplebro.newsreader.adapters.HotAdapter;
import com.domineer.triplebro.newsreader.controllers.HotController;
import com.domineer.triplebro.newsreader.models.NewsInfo;

import java.util.List;

public class HotFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment_hot;
    private ListView lv_hot;
    private HotController hotController;
    private List<NewsInfo> newsInfoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_hot = inflater.inflate(R.layout.fragment_hot, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_hot;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initView() {
        lv_hot = (ListView) fragment_hot.findViewById(R.id.lv_hot);
    }

    private void initData() {
        hotController = new HotController(getActivity());
        newsInfoList = hotController.findHotNewsInfo();
        lv_hot.setAdapter(new HotAdapter(getActivity(),newsInfoList));
    }

    private void setOnClickListener() {
        lv_hot.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent news = new Intent(getActivity(), NewsActivity.class);
        news.putExtra("newsInfo",newsInfoList.get(position));
        getActivity().startActivity(news);
    }
}
