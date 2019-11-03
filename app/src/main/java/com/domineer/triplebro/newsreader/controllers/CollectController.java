package com.domineer.triplebro.newsreader.controllers;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.models.VideoInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/3,23:35
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CollectController {
    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public CollectController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<VideoInfo> findCollectVideoInfoList(int user_id) {
        List<VideoInfo> videoInfoList = dataBaseProvider.findCollectVideoInfoList(user_id);
        return videoInfoList;
    }

    public List<NewsInfo> findCollectNewsInfoList(int user_id) {
        List<NewsInfo> newsInfoList = dataBaseProvider.findCollectNewsInfoList(user_id);
        return newsInfoList;
    }
}
