package com.domineer.triplebro.newsreader.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/2,12:20
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class SearchInfo implements Serializable {

    private int _id;
    private String searchContent;
    private String searchTime;
    private int user_id;

    public SearchInfo() {
    }

    public SearchInfo(int _id, String searchContent, String searchTime, int user_id) {
        this._id = _id;
        this.searchContent = searchContent;
        this.searchTime = searchTime;
        this.user_id = user_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SearchInfo{" +
                "_id=" + _id +
                ", searchContent='" + searchContent + '\'' +
                ", searchTime='" + searchTime + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
