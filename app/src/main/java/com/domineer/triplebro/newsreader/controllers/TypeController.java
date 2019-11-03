package com.domineer.triplebro.newsreader.controllers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.newsreader.models.TypeInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,13:38
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class TypeController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public TypeController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<TypeInfo> findTypeInfoList() {
        List<TypeInfo> typeInfoList = dataBaseProvider.findTypeInfoList();
        return typeInfoList;
    }
}
