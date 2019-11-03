package com.domineer.triplebro.newsreader.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/2,11:29
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class NewsInfo implements Serializable {

    private int _id;
    private String title;
    private String content;
    private String image;
    private int author;
    private String time;
    private int typeId;

    public NewsInfo() {
    }

    public NewsInfo(int _id, String title, String content, String image, int author, String time, int typeId) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.author = author;
        this.time = time;
        this.typeId = typeId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", author=" + author +
                ", time='" + time + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
