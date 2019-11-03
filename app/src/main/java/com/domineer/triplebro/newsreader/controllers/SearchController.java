package com.domineer.triplebro.newsreader.controllers;

import android.content.Context;

import com.domineer.triplebro.newsreader.activities.SearchActivity;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.models.SearchInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/4,1:07
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class SearchController {

    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public SearchController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<SearchInfo> getSearchHistoryById(int user_id) {
        List<SearchInfo> searchInfoList = dataBaseProvider.getSearchHistoryById(user_id);
        return searchInfoList;
    }

    public List<NewsInfo> searchNewsInfoList(String searchContent) {
        List<NewsInfo> newsInfoList = dataBaseProvider.searchNewsInfoList(searchContent);
        return newsInfoList;
    }

    public void addSearchHistory(int user_id, String searchContent) {
        dataBaseProvider.addSearchHistory(user_id,searchContent);
    }

    public void deleteAllSearchInfo(int user_id) {
        dataBaseProvider.deleteAllSearchInfo(user_id);
    }

    public void deleteSearchInfoById(int search_id) {
        dataBaseProvider.deleteSearchInfoById(search_id);
    }
}
