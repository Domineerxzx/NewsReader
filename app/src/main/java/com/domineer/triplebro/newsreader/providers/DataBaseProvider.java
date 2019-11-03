package com.domineer.triplebro.newsreader.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.domineer.triplebro.newsreader.database.NewsReaderDataBase;
import com.domineer.triplebro.newsreader.models.AdminInfo;
import com.domineer.triplebro.newsreader.models.CollectInfo;
import com.domineer.triplebro.newsreader.models.CommentInfo;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.models.ReadInfo;
import com.domineer.triplebro.newsreader.models.SearchInfo;
import com.domineer.triplebro.newsreader.models.TypeInfo;
import com.domineer.triplebro.newsreader.models.UserInfo;
import com.domineer.triplebro.newsreader.models.VideoInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/15,23:26
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DataBaseProvider implements DataProvider {

    private Context context;
    private final NewsReaderDataBase newsReaderDataBase;

    public DataBaseProvider(Context context) {
        this.context = context;
        newsReaderDataBase = new NewsReaderDataBase(context);
    }


    public AdminInfo findAuthorInfo(int author) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor adminInfoCursor = db.query("adminInfo", new String[]{"nickname", "user_head"}, "_id = ?", new String[]{String.valueOf(author)}, null, null, null);
        AdminInfo adminInfo = new AdminInfo();
        if (adminInfoCursor != null && adminInfoCursor.getCount() > 0) {
            adminInfoCursor.moveToNext();
            adminInfo.set_id(author);
            adminInfo.setNickname(adminInfoCursor.getString(0));
            adminInfo.setUserHead(adminInfoCursor.getString(1));
        }
        if (adminInfoCursor != null) {
            adminInfoCursor.close();
        }
        db.close();
        return adminInfo;
    }

    public List<NewsInfo> findNewsListOrderByTimeDesc() {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor newsInfoCursor = db.query("newsInfo", null, null, null, null, null, "_id DESC");
        List<NewsInfo> newsInfoList = new ArrayList<>();
        if (newsInfoCursor != null && newsInfoCursor.getCount() > 0) {
            while (newsInfoCursor.moveToNext()) {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.set_id(newsInfoCursor.getInt(0));
                newsInfo.setTitle(newsInfoCursor.getString(1));
                newsInfo.setContent(newsInfoCursor.getString(2));
                newsInfo.setImage(newsInfoCursor.getString(4));
                newsInfo.setTime(newsInfoCursor.getString(5));
                newsInfo.setAuthor(newsInfoCursor.getInt(3));
                newsInfo.setTypeId(newsInfoCursor.getInt(6));
                newsInfoList.add(newsInfo);
            }
        }
        if (newsInfoCursor != null) {
            newsInfoCursor.close();
        }
        db.close();
        return newsInfoList;
    }

    public List<NewsInfo> findBannerNewsList() {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor newsInfoCursor = db.query("newsInfo", null, null, null, null, null, "_id DESC");
        List<NewsInfo> bannerNewsList = new ArrayList<>();
        if (newsInfoCursor != null && newsInfoCursor.getCount() > 0) {
            while (newsInfoCursor.moveToNext()) {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.set_id(newsInfoCursor.getInt(0));
                newsInfo.setTitle(newsInfoCursor.getString(1));
                newsInfo.setContent(newsInfoCursor.getString(2));
                newsInfo.setImage(newsInfoCursor.getString(4));
                newsInfo.setTime(newsInfoCursor.getString(5));
                newsInfo.setAuthor(newsInfoCursor.getInt(3));
                newsInfo.setTypeId(newsInfoCursor.getInt(6));
                bannerNewsList.add(newsInfo);
                if (bannerNewsList.size() >= 5) {
                    break;
                }
            }
        }
        if (newsInfoCursor != null) {
            newsInfoCursor.close();
        }
        db.close();
        return bannerNewsList;
    }

    public List<TypeInfo> findTypeInfoList() {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor typeInfoCursor = db.query("typeInfo", null, null, null, null, null, null);
        List<TypeInfo> typeInfoList = new ArrayList<>();
        if (typeInfoCursor != null && typeInfoCursor.getCount() > 0) {
            while (typeInfoCursor.moveToNext()) {
                TypeInfo typeInfo = new TypeInfo(typeInfoCursor.getInt(0), typeInfoCursor.getString(1), typeInfoCursor.getString(2));
                typeInfoList.add(typeInfo);
            }
        }
        if (typeInfoCursor != null) {
            typeInfoCursor.close();
        }
        db.close();
        return typeInfoList;
    }

    public List<NewsInfo> findNewsInfoListByTypeId(int id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor newsInfoCursor = db.query("newsInfo", null, "type_id = ?", new String[]{String.valueOf(id)}, null, null, "_id DESC");
        List<NewsInfo> newsInfoList = new ArrayList<>();
        if (newsInfoCursor != null && newsInfoCursor.getCount() > 0) {
            while (newsInfoCursor.moveToNext()) {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.set_id(newsInfoCursor.getInt(0));
                newsInfo.setTitle(newsInfoCursor.getString(1));
                newsInfo.setContent(newsInfoCursor.getString(2));
                newsInfo.setImage(newsInfoCursor.getString(4));
                newsInfo.setTime(newsInfoCursor.getString(5));
                newsInfo.setAuthor(newsInfoCursor.getInt(3));
                newsInfo.setTypeId(newsInfoCursor.getInt(6));
                newsInfoList.add(newsInfo);
            }
        }
        if (newsInfoCursor != null) {
            newsInfoCursor.close();
        }
        db.close();
        return newsInfoList;
    }

    public List<CommentInfo> findCommentInfoListByNewsId(int id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor commentInfoCursor = db.query("commentInfo", null, "news_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        List<CommentInfo> commentInfoList = new ArrayList<>();
        if (commentInfoCursor != null && commentInfoCursor.getCount() > 0) {
            while (commentInfoCursor.moveToNext()) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.set_id(commentInfoCursor.getInt(0));
                commentInfo.setUserId(commentInfoCursor.getInt(1));
                commentInfo.setNewsId(commentInfoCursor.getInt(2));
                commentInfo.setVideoId(commentInfoCursor.getInt(3));
                commentInfo.setCommentContent(commentInfoCursor.getString(4));
                commentInfo.setTime(commentInfoCursor.getString(5));
                commentInfoList.add(commentInfo);
            }
        }
        if (commentInfoCursor != null) {
            commentInfoCursor.close();
        }
        db.close();
        return commentInfoList;
    }

    public UserInfo findUserInfoById(int userId) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor userInfoCursor = db.query("userInfo", new String[]{"nickname", "user_head"}, "_id = ?", new String[]{String.valueOf(userId)}, null, null, null);
        UserInfo userInfo = new UserInfo();
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            userInfoCursor.moveToNext();
            userInfo.set_id(userId);
            userInfo.setNickname(userInfoCursor.getString(0));
            userInfo.setUserHead(userInfoCursor.getString(1));
        }
        if (userInfoCursor != null) {
            userInfoCursor.close();
        }
        db.close();
        return userInfo;
    }

    public long submitComment(int user_id, int news_id, String commentContent) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("news_id", news_id);
        contentValues.put("comment_content", commentContent);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        long timeStamp = System.currentTimeMillis();
        String time = simpleDateFormat.format(timeStamp);
        contentValues.put("time", time);
        long comment_result = db.insert("commentInfo", null, contentValues);
        db.close();
        return comment_result;
    }

    public long submitVideoComment(int user_id, int video_id, String commentContent) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("video_id", video_id);
        contentValues.put("comment_content", commentContent);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        long timeStamp = System.currentTimeMillis();
        String time = simpleDateFormat.format(timeStamp);
        contentValues.put("time", time);
        long comment_result = db.insert("commentInfo", null, contentValues);
        db.close();
        return comment_result;
    }

    public ReadInfo findReadInfoById(int id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor readInfoCursor = db.query("readInfo", null, "news_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        ReadInfo readInfo = new ReadInfo();
        if (readInfoCursor != null && readInfoCursor.getCount() > 0) {
            readInfoCursor.moveToNext();
            readInfo.set_id(readInfoCursor.getInt(0));
            readInfo.setNewsId(readInfoCursor.getInt(1));
            readInfo.setVideoId(readInfoCursor.getInt(2));
            readInfo.setReadTime(readInfoCursor.getInt(3));
        }
        if (readInfoCursor != null) {
            readInfoCursor.close();
        }
        db.close();
        return readInfo;
    }

    public void updateReadTime(ReadInfo readInfo) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("read_time", readInfo.getReadTime() + 1);
        db.update("readInfo", contentValues, "_id = ?", new String[]{String.valueOf(readInfo.get_id())});
        db.close();
    }

    public List<NewsInfo> findHotNewsInfo() {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor readInfoCursor = db.query("readInfo", null, null, null, null, null, "read_time DESC");
        List<ReadInfo> readInfoList = new ArrayList<>();
        if (readInfoCursor != null && readInfoCursor.getCount() > 0) {
            while (readInfoCursor.moveToNext()) {
                ReadInfo readInfo = new ReadInfo();
                readInfo.set_id(readInfoCursor.getInt(0));
                readInfo.setNewsId(readInfoCursor.getInt(1));
                readInfo.setVideoId(readInfoCursor.getInt(2));
                readInfo.setReadTime(readInfoCursor.getInt(3));
                readInfoList.add(readInfo);
            }
        }
        if (readInfoCursor != null) {
            readInfoCursor.close();
        }
        List<NewsInfo> newsInfoList = new ArrayList<>();
        for (ReadInfo readInfo : readInfoList) {
            Cursor newsInfoCursor = db.query("newsInfo", null, "_id = ?", new String[]{String.valueOf(readInfo.getNewsId())}, null, null, null);
            if (newsInfoCursor != null && newsInfoCursor.getCount() > 0) {
                newsInfoCursor.moveToNext();
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.set_id(newsInfoCursor.getInt(0));
                newsInfo.setTitle(newsInfoCursor.getString(1));
                newsInfo.setContent(newsInfoCursor.getString(2));
                newsInfo.setImage(newsInfoCursor.getString(4));
                newsInfo.setTime(newsInfoCursor.getString(5));
                newsInfo.setAuthor(newsInfoCursor.getInt(3));
                newsInfo.setTypeId(newsInfoCursor.getInt(6));
                newsInfoList.add(newsInfo);
                if (newsInfoList.size() == 30) {
                    break;
                }
            }
            if (newsInfoCursor != null) {
                newsInfoCursor.close();
            }
        }
        db.close();
        return newsInfoList;
    }

    public List<VideoInfo> findVideoInfoList() {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        List<VideoInfo> videoInfoList = new ArrayList<>();
        Cursor videoInfoCursor = db.query("videoInfo", null, null, null, null, null, "_id DESC");
        if (videoInfoCursor != null && videoInfoCursor.getCount() > 0) {
            while (videoInfoCursor.moveToNext()) {
                VideoInfo videoInfo = new VideoInfo();
                videoInfo.set_id(videoInfoCursor.getInt(0));
                videoInfo.setTitle(videoInfoCursor.getString(1));
                videoInfo.setVideoUrl(videoInfoCursor.getString(2));
                videoInfo.setAuthor(videoInfoCursor.getInt(3));
                videoInfo.setImage(videoInfoCursor.getString(4));
                videoInfo.setTime(videoInfoCursor.getString(5));
                videoInfo.setPlayTime(videoInfoCursor.getInt(6));
                videoInfo.setLikeTime(videoInfoCursor.getInt(7));
                videoInfoList.add(videoInfo);
            }
        }
        if (videoInfoCursor != null) {
            videoInfoCursor.close();
        }
        db.close();
        return videoInfoList;
    }

    public List<CommentInfo> findCommentInfoListByVideoId(int id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor commentInfoCursor = db.query("commentInfo", null, "video_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        List<CommentInfo> commentInfoList = new ArrayList<>();
        if (commentInfoCursor != null && commentInfoCursor.getCount() > 0) {
            while (commentInfoCursor.moveToNext()) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.set_id(commentInfoCursor.getInt(0));
                commentInfo.setUserId(commentInfoCursor.getInt(1));
                commentInfo.setNewsId(commentInfoCursor.getInt(2));
                commentInfo.setVideoId(commentInfoCursor.getInt(3));
                commentInfo.setCommentContent(commentInfoCursor.getString(4));
                commentInfo.setTime(commentInfoCursor.getString(5));
                commentInfoList.add(commentInfo);
            }
        }
        if (commentInfoCursor != null) {
            commentInfoCursor.close();
        }
        db.close();
        return commentInfoList;
    }

    public void updateVideoLike(int likeTime, int video_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("likeTime", likeTime + 1);
        db.update("videoInfo", contentValues, "_id = ?", new String[]{String.valueOf(video_id)});
        db.close();
    }

    public void updateVideoPlay(int playTime, int video_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("playTime", playTime + 1);
        db.update("videoInfo", contentValues, "_id = ?", new String[]{String.valueOf(video_id)});
        db.close();
    }

    public boolean getVideoIsCollect(int video_id, int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "user_id = ? and collect_video_id = ?", new String[]{String.valueOf(user_id), String.valueOf(video_id)}, null, null, null);
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            collectInfoCursor.close();
            db.close();
            return true;
        } else {
            if (collectInfoCursor != null) {
                collectInfoCursor.close();
            }
            db.close();
            return false;
        }
    }

    public void addVideoCollect(int video_id, int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("collect_video_id", video_id);
        db.insert("collectInfo", null, contentValues);
        db.close();
    }

    public void deleteVideoCollect(int video_id, int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        db.delete("collectInfo", "user_id = ? and collect_video_id = ?", new String[]{String.valueOf(user_id), String.valueOf(video_id)});
        db.close();
    }

    public boolean getNewsIsCollect(int news_id, int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "user_id = ? and collect_news_id = ?", new String[]{String.valueOf(user_id), String.valueOf(news_id)}, null, null, null);
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            collectInfoCursor.close();
            db.close();
            return true;
        } else {
            if (collectInfoCursor != null) {
                collectInfoCursor.close();
            }
            db.close();
            return false;
        }
    }

    public void addNewsCollect(int news_id, int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("collect_news_id", news_id);
        db.insert("collectInfo", null, contentValues);
        db.close();
    }

    public void deleteNewsCollect(int news_id, int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        db.delete("collectInfo", "user_id = ? and collect_news_id = ?", new String[]{String.valueOf(user_id), String.valueOf(news_id)});
        db.close();
    }

    public List<VideoInfo> findCollectVideoInfoList(int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "user_id = ? and collect_video_id is not null", new String[]{String.valueOf(user_id)}, null, null, null);
        List<CollectInfo> collectInfoList = new ArrayList<>();
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            while (collectInfoCursor.moveToNext()) {
                CollectInfo collectInfo = new CollectInfo();
                collectInfo.set_id(collectInfoCursor.getInt(0));
                collectInfo.setUser_id(collectInfoCursor.getInt(1));
                collectInfo.setCollect_news_id(collectInfoCursor.getInt(2));
                collectInfo.setCollect_video_id(collectInfoCursor.getInt(3));
                collectInfoList.add(collectInfo);
            }
        }
        if (collectInfoCursor != null) {
            collectInfoCursor.close();
        }
        List<VideoInfo> videoInfoList = new ArrayList<>();
        for (CollectInfo collectInfo : collectInfoList) {
            Cursor videoInfoCursor = db.query("videoInfo", null, "_id = ?", new String[]{String.valueOf(collectInfo.getCollect_video_id())}, null, null, null);
            if (videoInfoCursor != null && videoInfoCursor.getCount() > 0) {
                videoInfoCursor.moveToNext();
                VideoInfo videoInfo = new VideoInfo();
                videoInfo.set_id(videoInfoCursor.getInt(0));
                videoInfo.setTitle(videoInfoCursor.getString(1));
                videoInfo.setVideoUrl(videoInfoCursor.getString(2));
                videoInfo.setAuthor(videoInfoCursor.getInt(3));
                videoInfo.setImage(videoInfoCursor.getString(4));
                videoInfo.setTime(videoInfoCursor.getString(5));
                videoInfo.setPlayTime(videoInfoCursor.getInt(6));
                videoInfo.setLikeTime(videoInfoCursor.getInt(7));
                videoInfoList.add(videoInfo);
            }
            if (videoInfoCursor != null) {
                videoInfoCursor.close();
            }
        }
        db.close();
        return videoInfoList;
    }

    public List<NewsInfo> findCollectNewsInfoList(int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "user_id = ? and collect_news_id is not null", new String[]{String.valueOf(user_id)}, null, null, null);
        List<CollectInfo> collectInfoList = new ArrayList<>();
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            while (collectInfoCursor.moveToNext()) {
                CollectInfo collectInfo = new CollectInfo();
                collectInfo.set_id(collectInfoCursor.getInt(0));
                collectInfo.setUser_id(collectInfoCursor.getInt(1));
                collectInfo.setCollect_news_id(collectInfoCursor.getInt(2));
                collectInfo.setCollect_video_id(collectInfoCursor.getInt(3));
                collectInfoList.add(collectInfo);
            }
        }
        if (collectInfoCursor != null) {
            collectInfoCursor.close();
        }
        List<NewsInfo> newsInfoList = new ArrayList<>();
        for (CollectInfo collectInfo : collectInfoList) {
            Cursor newsInfoCursor = db.query("newsInfo", null, "_id = ?", new String[]{String.valueOf(collectInfo.getCollect_news_id())}, null, null, null);
            if (newsInfoCursor != null && newsInfoCursor.getCount() > 0) {
                newsInfoCursor.moveToNext();
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.set_id(newsInfoCursor.getInt(0));
                newsInfo.setTitle(newsInfoCursor.getString(1));
                newsInfo.setContent(newsInfoCursor.getString(2));
                newsInfo.setImage(newsInfoCursor.getString(4));
                newsInfo.setTime(newsInfoCursor.getString(5));
                newsInfo.setAuthor(newsInfoCursor.getInt(3));
                newsInfo.setTypeId(newsInfoCursor.getInt(6));
                newsInfoList.add(newsInfo);
            }
            if (newsInfoCursor != null) {
                newsInfoCursor.close();
            }
        }
        db.close();
        return newsInfoList;
    }

    public List<SearchInfo> getSearchHistoryById(int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor searchInfoCursor = db.query("searchInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, "_id DESC");
        List<SearchInfo> searchInfoList = new ArrayList<>();
        if(searchInfoCursor!= null && searchInfoCursor.getCount()>0){
            while (searchInfoCursor.moveToNext()){
                SearchInfo searchInfo = new SearchInfo();
                searchInfo.set_id(searchInfoCursor.getInt(0));
                searchInfo.setSearchContent(searchInfoCursor.getString(1));
                searchInfo.setSearchTime(searchInfoCursor.getString(2));
                searchInfo.setUser_id(searchInfoCursor.getInt(3));
                searchInfoList.add(searchInfo);
            }
        }
        if (searchInfoCursor != null) {
            searchInfoCursor.close();
        }
        db.close();
        return searchInfoList;
    }

    public List<NewsInfo> searchNewsInfoList(String searchContent) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        Cursor newsInfoCursor = db.query("newsInfo", null, "title like ?", new String[]{"%"+searchContent+"%"}, null, null, null);
        List<NewsInfo> newsInfoList = new ArrayList<>();
        if (newsInfoCursor != null && newsInfoCursor.getCount() > 0) {
            while (newsInfoCursor.moveToNext()) {
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.set_id(newsInfoCursor.getInt(0));
                newsInfo.setTitle(newsInfoCursor.getString(1));
                newsInfo.setContent(newsInfoCursor.getString(2));
                newsInfo.setImage(newsInfoCursor.getString(4));
                newsInfo.setTime(newsInfoCursor.getString(5));
                newsInfo.setAuthor(newsInfoCursor.getInt(3));
                newsInfo.setTypeId(newsInfoCursor.getInt(6));
                newsInfoList.add(newsInfo);
            }
        }
        if (newsInfoCursor != null) {
            newsInfoCursor.close();
        }
        db.close();
        return newsInfoList;
    }

    public void addSearchHistory(int user_id, String searchContent) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("searchContent",searchContent);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        long timeStamp = System.currentTimeMillis();
        String time = simpleDateFormat.format(timeStamp);
        contentValues.put("searchTime",time);
        contentValues.put("user_id",user_id);
        db.insert("searchInfo",null,contentValues);
        db.close();
    }

    public void deleteAllSearchInfo(int user_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        db.delete("searchInfo","user_id = ?",new String[]{String.valueOf(user_id)});
        db.close();
    }

    public void deleteSearchInfoById(int search_id) {
        SQLiteDatabase db = newsReaderDataBase.getWritableDatabase();
        db.delete("searchInfo","_id = ?",new String[]{String.valueOf(search_id)});
        db.close();
    }

    public List<UserInfo> findNotShutUpUserInfoList() {
        return null;
    }

    public List<UserInfo> findShutUpUserInfoList() {
        return null;
    }
}
