package com.domineer.triplebro.newsreader.controllers;

import android.content.Context;

import com.domineer.triplebro.newsreader.activities.SubmitNewsActivity;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.Map;

/**
 * @author Domineer
 * @data 2019/11/4,10:27
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class SubmitNewsController {
    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public SubmitNewsController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public void submitNews(int admin_id, String newsTitle, String newsContent, String image, int newsType) {
        dataBaseProvider.submitNews(admin_id,newsTitle,newsContent,image,newsType);
    }

    public Map<String, Integer> findTypeInfoMap() {
        Map<String, Integer> typeMap = dataBaseProvider.findTypeInfoMap();
        return typeMap;
    }
}
