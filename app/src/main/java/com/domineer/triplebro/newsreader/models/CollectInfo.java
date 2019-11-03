package com.domineer.triplebro.newsreader.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/2,12:19
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CollectInfo implements Serializable {

    private int _id;
    private int user_id;
    private int collect_news_id;
    private int collect_video_id;

    public CollectInfo() {
    }

    public CollectInfo(int _id, int user_id, int collect_news_id, int collect_video_id) {
        this._id = _id;
        this.user_id = user_id;
        this.collect_news_id = collect_news_id;
        this.collect_video_id = collect_video_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCollect_news_id() {
        return collect_news_id;
    }

    public void setCollect_news_id(int collect_news_id) {
        this.collect_news_id = collect_news_id;
    }

    public int getCollect_video_id() {
        return collect_video_id;
    }

    public void setCollect_video_id(int collect_video_id) {
        this.collect_video_id = collect_video_id;
    }

    @Override
    public String toString() {
        return "CollectInfo{" +
                "_id=" + _id +
                ", user_id=" + user_id +
                ", collect_news_id=" + collect_news_id +
                ", collect_video_id=" + collect_video_id +
                '}';
    }
}
