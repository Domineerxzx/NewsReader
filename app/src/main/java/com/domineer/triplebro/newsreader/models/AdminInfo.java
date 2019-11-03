package com.domineer.triplebro.newsreader.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/2,12:18
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AdminInfo implements Serializable {

    private int _id;
    private String nickname;
    private String userName;
    private String password;
    private String userHead;

    public AdminInfo() {
    }

    public AdminInfo(int _id, String nickname, String userName, String password, String userHead) {
        this._id = _id;
        this.nickname = nickname;
        this.userName = userName;
        this.password = password;
        this.userHead = userHead;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "_id=" + _id +
                ", nickname='" + nickname + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userHead='" + userHead + '\'' +
                '}';
    }
}
