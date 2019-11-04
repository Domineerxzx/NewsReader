package com.domineer.triplebro.newsreader.controllers;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.domineer.triplebro.newsreader.models.UserInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/4,2:16
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ShutUpController {
    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public ShutUpController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<UserInfo> findNotShutUpUserInfoList() {
        List<UserInfo> userInfoList = dataBaseProvider.findNotShutUpUserInfoList();
        return userInfoList;
    }

    public List<UserInfo> findShutUpUserInfoList() {
        List<UserInfo> userInfoList = dataBaseProvider.findShutUpUserInfoList();
        return userInfoList;
    }

    public void shutUpUser(int id, String startTime, String endTime, String shutUpReason) {
        dataBaseProvider.shutUpUser(id,startTime,endTime,shutUpReason);
    }

    public void cancelShutUpUser(int id) {
        dataBaseProvider.cancelShutUpUser(id);
    }
}
