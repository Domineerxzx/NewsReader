package com.domineer.triplebro.newsreader.controllers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.newsreader.models.CommentInfo;
import com.domineer.triplebro.newsreader.models.VideoInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/3,11:40
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class LiveController {
    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public LiveController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<VideoInfo> findVideoInfoList() {
        List<VideoInfo> videoInfoList = dataBaseProvider.findVideoInfoList();
        return videoInfoList;
    }

    public List<CommentInfo> findCommentInfoListByVideoId(int id) {
        List<CommentInfo> commentInfoList = dataBaseProvider.findCommentInfoListByVideoId(id);
        return commentInfoList;
    }

    public long submitComment(int user_id, int video_id, String commentContent) {
        long comment_result = dataBaseProvider.submitVideoComment(user_id, video_id, commentContent);
        return comment_result;
    }

    public void updateVideoLike(int likeTime, int video_id) {
        dataBaseProvider.updateVideoLike(likeTime,video_id);
    }

    public void updateVideoPlay(int video_id, int playTime) {
        dataBaseProvider.updateVideoPlay(playTime,video_id);
    }

    public boolean getVideoIsCollect(int video_id, int user_id) {
        boolean isCollect = dataBaseProvider.getVideoIsCollect(video_id,user_id);
        return isCollect;
    }

    public void deleteVideoCollect(int user_id, int video_id) {
        dataBaseProvider.deleteVideoCollect(video_id,user_id);
    }

    public void addVideoCollect(int user_id, int video_id) {
        dataBaseProvider.addVideoCollect(video_id,user_id);
    }
}
