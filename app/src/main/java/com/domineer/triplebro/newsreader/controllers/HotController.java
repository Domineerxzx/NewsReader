package com.domineer.triplebro.newsreader.controllers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/3,4:14
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HotController {
    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public HotController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<NewsInfo> findHotNewsInfo() {
        List<NewsInfo> newsInfoList = dataBaseProvider.findHotNewsInfo();
        return newsInfoList;
    }
}
