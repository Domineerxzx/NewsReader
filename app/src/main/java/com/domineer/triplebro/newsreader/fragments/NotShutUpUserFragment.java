package com.domineer.triplebro.newsreader.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.adapters.NotShutUpAdapter;
import com.domineer.triplebro.newsreader.controllers.ShutUpController;
import com.domineer.triplebro.newsreader.models.UserInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/4,1:57
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class NotShutUpUserFragment extends Fragment {

    private View fragment_not_shut_up;
    private ListView lv_not_shut_up;
    private ShutUpController shutUpController;
    private List<UserInfo> userInfoList;
    private NotShutUpAdapter notShutUpAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_not_shut_up = inflater.inflate(R.layout.fragment_not_shut_up, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_not_shut_up;
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
        lv_not_shut_up = (ListView) fragment_not_shut_up.findViewById(R.id.lv_not_shut_up);
    }

    private void initData() {
        shutUpController = new ShutUpController(getActivity());
        userInfoList = shutUpController.findNotShutUpUserInfoList();
        notShutUpAdapter = new NotShutUpAdapter(getActivity(), userInfoList);
        lv_not_shut_up.setAdapter(notShutUpAdapter);
    }

    private void setOnClickListener() {

    }

}
