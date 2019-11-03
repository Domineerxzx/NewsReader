package com.domineer.triplebro.newsreader.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/2,12:20
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CommentInfo implements Serializable {

    private int _id;
    private int userId;
    private int newsId;
    private int videoId;
    private String commentContent;
    private String time;

    public CommentInfo() {
    }

    public CommentInfo(int _id, int userId, int newsId, int videoId, String commentContent, String time) {
        this._id = _id;
        this.userId = userId;
        this.newsId = newsId;
        this.videoId = videoId;
        this.commentContent = commentContent;
        this.time = time;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "_id=" + _id +
                ", userId=" + userId +
                ", newsId=" + newsId +
                ", videoId=" + videoId +
                ", commentContent='" + commentContent + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
