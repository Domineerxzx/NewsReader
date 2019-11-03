package com.domineer.triplebro.newsreader.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/2,12:19
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class VideoInfo implements Serializable {

    private int _id;
    private String title;
    private String videoUrl;
    private int author;
    private String image;
    private String time;
    private int playTime;
    private int likeTime;

    public VideoInfo() {
    }

    public VideoInfo(int _id, String title, String videoUrl, int author, String image, String time, int playTime, int likeTime) {
        this._id = _id;
        this.title = title;
        this.videoUrl = videoUrl;
        this.author = author;
        this.image = image;
        this.time = time;
        this.playTime = playTime;
        this.likeTime = likeTime;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(int likeTime) {
        this.likeTime = likeTime;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", author=" + author +
                ", image='" + image + '\'' +
                ", time='" + time + '\'' +
                ", playTime=" + playTime +
                ", likeTime=" + likeTime +
                '}';
    }
}
