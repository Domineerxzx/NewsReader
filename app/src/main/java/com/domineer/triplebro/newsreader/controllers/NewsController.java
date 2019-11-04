package com.domineer.triplebro.newsreader.controllers;

import android.content.Context;

import com.domineer.triplebro.newsreader.activities.NewsActivity;
import com.domineer.triplebro.newsreader.models.CommentInfo;
import com.domineer.triplebro.newsreader.models.UserInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,14:42
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class NewsController {

    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public NewsController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }


    public List<CommentInfo> findCommentInfoListByNewsId(int id) {
        List<CommentInfo> commentInfoList = dataBaseProvider.findCommentInfoListByNewsId(id);
        return commentInfoList;
    }

    public long submitComment(int user_id, int news_id, String commentContent) {
        long comment_result = dataBaseProvider.submitComment(user_id, news_id, commentContent);
        return comment_result;
    }

    public boolean getIsCollect(int news_id, int user_id) {
        boolean isCollect = dataBaseProvider.getNewsIsCollect(news_id, user_id);
        return isCollect;
    }

    public void deleteNewsCollect(int user_id, int news_id) {
        dataBaseProvider.deleteNewsCollect(news_id,user_id);
    }

    public void addNewsCollect(int user_id, int news_id) {
        dataBaseProvider.addNewsCollect(news_id,user_id);
    }

    public UserInfo findUserInfo(int user_id) {
        UserInfo userInfo = dataBaseProvider.findUserInfo(user_id);
        return userInfo;
    }
}
