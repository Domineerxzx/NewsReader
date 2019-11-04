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
import com.domineer.triplebro.newsreader.activities.VideoActivity;
import com.domineer.triplebro.newsreader.adapters.LiveAdapter;
import com.domineer.triplebro.newsreader.controllers.CollectController;
import com.domineer.triplebro.newsreader.models.VideoInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/3,23:27
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class VideoCollectFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment_video_collect;
    private ListView lv_video;
    private CollectController collectController;
    private List<VideoInfo> videoInfoList;
    private LiveAdapter liveAdapter;
    private SharedPreferences userInfo;
    private int user_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_video_collect = inflater.inflate(R.layout.fragment_video_collect, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_video_collect;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        lv_video = (ListView) fragment_video_collect.findViewById(R.id.lv_video);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        collectController = new CollectController(getActivity());
        videoInfoList = collectController.findCollectVideoInfoList(user_id);
        liveAdapter = new LiveAdapter(getActivity(), videoInfoList);
        lv_video.setAdapter(liveAdapter);
    }

    private void setOnClickListener() {
        lv_video.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent video = new Intent(getActivity(), VideoActivity.class);
        video.putExtra("videoInfo",videoInfoList.get(position));
        getActivity().startActivity(video);
    }
}
