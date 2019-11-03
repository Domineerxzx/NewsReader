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
import com.domineer.triplebro.newsreader.activities.VideoActivity;
import com.domineer.triplebro.newsreader.adapters.LiveAdapter;
import com.domineer.triplebro.newsreader.controllers.LiveController;
import com.domineer.triplebro.newsreader.models.VideoInfo;

import java.util.ArrayList;
import java.util.List;

public class LiveFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment_live;
    private ListView lv_live;
    private LiveController liveController;
    private List<VideoInfo> videoInfoList;
    private LiveAdapter liveAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_live = inflater.inflate(R.layout.fragment_live, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_live;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initView() {
        lv_live = (ListView) fragment_live.findViewById(R.id.lv_live);
    }

    private void initData() {
        liveController = new LiveController(getActivity());
        videoInfoList = liveController.findVideoInfoList();
        liveAdapter = new LiveAdapter(getActivity(), videoInfoList);
        lv_live.setAdapter(liveAdapter);
    }

    private void setOnClickListener() {
        lv_live.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent video = new Intent(getActivity(), VideoActivity.class);
        video.putExtra("videoInfo",videoInfoList.get(position));
        getActivity().startActivity(video);
    }
}
