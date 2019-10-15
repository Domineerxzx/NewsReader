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
                ",nickname varchar(20),user_head varchar(200),is_shut_up Integer)");

        //管理员表
        db.execSQL("create table adminInfo(_id Integer primary key autoincrement,phone_number varchar(20),password varchar(20))");

//        //发布信息表
//        db.execSQL("create table issueInfo(_id Integer primary key autoincrement,user_id Integer," +
//                "issue_content varchar(500),issue_time varchar(100),FOREIGN KEY (user_id) REFERENCES userInfo(_id))");
//
//        //发布图片表
//        db.execSQL("create table issueImageInfo(_id Integer primary key autoincrement,issue_id Integer,issue_image varchar(200)," +
//                "FOREIGN KEY (issue_id) REFERENCES issueInfo(_id))");
//
//        //关注表
//        db.execSQL("create table careInfo(_id Integer primary key autoincrement,cared_user_id Integer,user_id Integer" +
//                ",FOREIGN KEY (cared_user_id) REFERENCES userInfo(_id)," +
//                "FOREIGN KEY (user_id) REFERENCES userInfo(_id))");
//
//        //聊天表
//        db.execSQL("create table chatInfo(_id Integer primary key autoincrement,user_id_one Integer,user_id_two Integer,chat_content varchar(200),time varchar(100)" +
//                ",FOREIGN KEY (user_id_one) REFERENCES userInfo(_id)" +
//                ",FOREIGN KEY (user_id_two) REFERENCES userInfo(_id))");
//
//        //搜索历史表
//        db.execSQL("create table searchHistoryInfo(_id Integer primary key autoincrement,user_id Integer,search_content varchar(100),search_count Integer" +
//                ",FOREIGN KEY (user_id) REFERENCES userInfo(_id))");
//
//        //评论表
//        db.execSQL("create table commentInfo(_id Integer primary key autoincrement,user_id Integer,issue_id Integer,comment_content varchar(2000),time varchar(100),comment_id Integer" +
//                ",FOREIGN KEY (user_id) REFERENCES userInfo(_id)" +
//                ",FOREIGN KEY (issue_id) REFERENCES issueInfo(_id))");

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
