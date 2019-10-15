package com.domineer.triplebro.newsreader.controllers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.domineer.triplebro.newsreader.handlers.AdPictureHandler;
import com.domineer.triplebro.newsreader.services.NetworkConnectionService;

public class DataInitManager implements ServiceConnection {

    private Context context;
    private AdPictureHandler adPictureHandler;

    public DataInitManager(Context context, AdPictureHandler adPictureHandler) {
        this.context = context;
        this.adPictureHandler = adPictureHandler;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkConnectionService.MyBinder myBinder = (NetworkConnectionService.MyBinder) service;
        myBinder.getAdPicture(adPictureHandler);

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public void getAdPicture() {
        Intent intent = new Intent(context, NetworkConnectionService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }
}
