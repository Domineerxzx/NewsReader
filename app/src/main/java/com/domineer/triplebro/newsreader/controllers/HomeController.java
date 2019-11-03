package com.domineer.triplebro.newsreader.controllers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,12:41
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HomeController {

    private Context context;

    public HomeController(Context context) {
        this.context = context;
    }


    public List<NewsInfo> findNewsListOrderByTimeDesc() {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<NewsInfo> newsInfoList = dataBaseProvider.findNewsListOrderByTimeDesc();
        return newsInfoList;
    }

    public List<NewsInfo> findBannerNewsList() {
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        List<NewsInfo> bannerNewsList = dataBaseProvider.findBannerNewsList();
        return bannerNewsList;
    }
}
