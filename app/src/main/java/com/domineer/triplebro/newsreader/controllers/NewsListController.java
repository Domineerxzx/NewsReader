package com.domineer.triplebro.newsreader.controllers;

import android.content.Context;

import com.domineer.triplebro.newsreader.activities.NewsListActivity;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,14:10
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class NewsListController {

    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public NewsListController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }


    public List<NewsInfo> findNewsInfoListByTypeId(int id) {
        List<NewsInfo> newsInfoList = dataBaseProvider.findNewsInfoListByTypeId(id);
        return newsInfoList;
    }
}
