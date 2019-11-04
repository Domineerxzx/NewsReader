package com.domineer.triplebro.newsreader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * @author Domineer
 * @data 2019/8/11,23:39
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class NewsReaderDataBase extends SQLiteOpenHelper {
    public NewsReaderDataBase(@Nullable Context context) {
        super(context, "NewsReaderDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户表
        db.execSQL("create table userInfo(_id Integer primary key autoincrement,phone_number varchar(20),password varchar(20)" +
                ",nickname varchar(20),user_head varchar(200),is_shut_up Integer,shut_up_reason varchar(200),shut_up_start_time varchar(200)," +
                "shut_up_end_time varchar(200))");

        //管理员表
        db.execSQL("create table adminInfo(_id Integer primary key autoincrement,phone_number varchar(20),password varchar(20),nickname varchar(20),user_head varchar(200))");

        //分类信息表
        db.execSQL("create table typeInfo(_id Integer primary key autoincrement,typeName varchar(200),typeImage varchar(200))");

        //新闻信息表
        db.execSQL("create table newsInfo(_id Integer primary key autoincrement,title varchar(50),content varchar(20000),author_id number,image varchar(200),time varchar(200),type_id number,FOREIGN KEY (type_id) REFERENCES typeInfo(_id),FOREIGN KEY (author_id) REFERENCES adminInfo(_id))");

        //视频信息表
        db.execSQL("create table videoInfo(_id Integer primary key autoincrement,title varchar(50),videoUrl varchar(200),author varcahr(30),image varchar(200),time varchar(200),playTime number,likeTime number)");

        //搜索表
        db.execSQL("create table searchInfo(_id Integer primary key autoincrement,searchContent varchar(2000),searchTime varchar(200),user_id number,FOREIGN KEY (user_id) REFERENCES userInfo(_id))");

        //收藏表
        db.execSQL("create table collectInfo(_id Integer primary key autoincrement,user_id number,collect_news_Id number,collect_video_id number,FOREIGN KEY (user_id) REFERENCES userInfo(_id),FOREIGN KEY (collect_news_id) REFERENCES newsInfo(_id),FOREIGN KEY (collect_video_id) REFERENCES videoInfo(_id))");

        //评论表
        db.execSQL("create table commentInfo(_id Integer primary key autoincrement,user_id number,news_id number,video_id number,comment_content varchar(2000),time varchar(100)," +
                "FOREIGN KEY (user_id) REFERENCES userInfo(_id)" +
                ",FOREIGN KEY (news_id) REFERENCES newsInfo(_id)" +
                ",FOREIGN KEY (video_id) REFERENCES videoInfo(_id))");

        //阅读表
        db.execSQL("create table readInfo(_id Integer primary key autoincrement,news_id number,video_id number,read_time number" +
                ",FOREIGN KEY (news_id) REFERENCES newsInfo(_id)" +
                ",FOREIGN KEY (video_id) REFERENCES videoInfo(_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints 开启外键约束
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }
}
