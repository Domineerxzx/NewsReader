package com.domineer.triplebro.newsreader.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.newsreader.activities.MainActivity;
import com.domineer.triplebro.newsreader.activities.SplashActivity;
import com.domineer.triplebro.newsreader.controllers.DataInitManager;
import com.domineer.triplebro.newsreader.properties.ProjectProperties;

public class AdPictureHandler extends Handler {

    private Context context;
    private ImageView iv_ad;
    private DataInitManager dataInitManager;

    public AdPictureHandler(Context context, ImageView iv_ad) {
        this.context = context;
        this.iv_ad = iv_ad;
    }

    public DataInitManager getDataInitManager() {
        return dataInitManager;
    }

    public void setDataInitManager(DataInitManager dataInitManager) {
        this.dataInitManager = dataInitManager;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case ProjectProperties.AD_PICTURE:
                String adPicture = (String) msg.obj;
                Glide.with(context).load(adPicture).into(iv_ad);
                break;
            case ProjectProperties.SKIP:
                if(dataInitManager == null){
                    break;
                }else{
                    context.unbindService(dataInitManager);
                    Intent main = new Intent(context, MainActivity.class);
                    context.startActivity(main);
                    ((SplashActivity) context).finish();
                    break;
                }

        }
    }
}
