package com.domineer.triplebro.newsreader.models;

/**
 * @author Domineer
 * @data 2019/11/3,4:24
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ReadInfo {

    private int _id;
    private int newsId;
    private int videoId;
    private int readTime;

    public ReadInfo() {
    }

    public ReadInfo(int _id, int newsId, int videoId, int readTime) {
        this._id = _id;
        this.newsId = newsId;
        this.videoId = videoId;
        this.readTime = readTime;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    @Override
    public String toString() {
        return "ReadInfo{" +
                "_id=" + _id +
                ", newsId=" + newsId +
                ", videoId=" + videoId +
                ", readTime=" + readTime +
                '}';
    }
}
