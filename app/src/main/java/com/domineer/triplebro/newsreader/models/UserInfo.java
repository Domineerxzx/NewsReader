package com.domineer.triplebro.newsreader.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/2,12:18
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class UserInfo implements Serializable {

    private int _id;
    private String phone_number;
    private String password;
    private String nickname;
    private String userHead;
    private int isShutUp;
    private String shutUpReason;
    private String shutUpStartTime;
    private String shutUpEndTime;

    public UserInfo() {
    }

    public UserInfo(int _id, String phone_number, String password, String nickname, String userHead, int isShutUp, String shutUpReason, String shutUpStartTime, String shutUpEndTime) {
        this._id = _id;
        this.phone_number = phone_number;
        this.password = password;
        this.nickname = nickname;
        this.userHead = userHead;
        this.isShutUp = isShutUp;
        this.shutUpReason = shutUpReason;
        this.shutUpStartTime = shutUpStartTime;
        this.shutUpEndTime = shutUpEndTime;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public int getIsShutUp() {
        return isShutUp;
    }

    public void setIsShutUp(int isShutUp) {
        this.isShutUp = isShutUp;
    }

    public String getShutUpReason() {
        return shutUpReason;
    }

    public void setShutUpReason(String shutUpReason) {
        this.shutUpReason = shutUpReason;
    }

    public String getShutUpStartTime() {
        return shutUpStartTime;
    }

    public void setShutUpStartTime(String shutUpStartTime) {
        this.shutUpStartTime = shutUpStartTime;
    }

    public String getShutUpEndTime() {
        return shutUpEndTime;
    }

    public void setShutUpEndTime(String shutUpEndTime) {
        this.shutUpEndTime = shutUpEndTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", phone_number='" + phone_number + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userHead='" + userHead + '\'' +
                ", isShutUp=" + isShutUp +
                ", shutUpReason='" + shutUpReason + '\'' +
                ", shutUpStartTime='" + shutUpStartTime + '\'' +
                ", shutUpEndTime='" + shutUpEndTime + '\'' +
                '}';
    }
}
