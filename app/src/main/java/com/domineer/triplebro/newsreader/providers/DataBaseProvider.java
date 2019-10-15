package com.domineer.triplebro.newsreader.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.domineer.triplebro.newsreader.database.NewsReaderDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/15,23:26
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DataBaseProvider implements DataProvider {

    /*private Context context;
    private final NewsReaderDataBase newsReaderDataBase;

    public DataBaseProvider(Context context) {
        this.context = context;
        NewsReaderDataBase = new NewsReaderDataBase(context);
    }

    @Override
    public UserInfo queryUserInfoById(int userId) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        Cursor userInfoCursor = db.query("userInfo", null, "_id = ?", new String[]{String.valueOf(userId)}, null, null, null);
        UserInfo userInfo = new UserInfo();
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            while (userInfoCursor.moveToNext()) {
                userInfo.set_id(userInfoCursor.getInt(0));
                userInfo.setPhoneNumber(userInfoCursor.getString(1));
                userInfo.setPassword(userInfoCursor.getString(2));
                userInfo.setNickname(userInfoCursor.getString(3));
                userInfo.setUserHead(userInfoCursor.getString(4));
                userInfo.setIsShutUp(userInfoCursor.getInt(5));
            }
        }
        if (userInfoCursor != null) {
            userInfoCursor.close();
        }
        db.close();
        return userInfo;
    }

    @Override
    public List<IssueInfo> getAllIssueInfoList() {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        Cursor issueInfoCursor = db.query("issueInfo", null, null, null, null, null, "_id desc");
        if (issueInfoCursor != null && issueInfoCursor.getCount() > 0) {
            while (issueInfoCursor.moveToNext()) {
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.set_id(issueInfoCursor.getInt(0));
                issueInfo.setUserId(issueInfoCursor.getInt(1));
                issueInfo.setIssueContent(issueInfoCursor.getString(2));
                issueInfo.setIssueTime(issueInfoCursor.getString(3));
                issueInfoList.add(issueInfo);
            }
        }
        if (issueInfoCursor != null) {
            issueInfoCursor.close();
        }
        db.close();
        return issueInfoList;
    }

    @Override
    public List<IssueInfo> getIssueInfoListByType(String type) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        Cursor issueInfoCursor = db.query("issueInfo", null, "issue_content like ?", new String[]{"%" + type + "%"}, null, null, "_id desc");
        if (issueInfoCursor != null && issueInfoCursor.getCount() > 0) {
            while (issueInfoCursor.moveToNext()) {
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.set_id(issueInfoCursor.getInt(0));
                issueInfo.setUserId(issueInfoCursor.getInt(1));
                issueInfo.setIssueContent(issueInfoCursor.getString(2));
                issueInfo.setIssueTime(issueInfoCursor.getString(3));
                issueInfoList.add(issueInfo);
            }
        }
        if (issueInfoCursor != null) {
            issueInfoCursor.close();
        }
        db.close();
        return issueInfoList;
    }

    @Override
    public List<List<IssueImageInfo>> getIssueImageInfoListByIssueInfoList(List<IssueInfo> issueInfoList) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<List<IssueImageInfo>> issueImageInfoList = new ArrayList<>();
        for (IssueInfo issueInfo : issueInfoList) {
            List<IssueImageInfo> issueImageInfos = new ArrayList<>();
            Cursor issueImageInfoCursor = db.query("issueImageInfo", null, "issue_id = ?", new String[]{String.valueOf(issueInfo.get_id())}, null, null, null);
            if (issueImageInfoCursor != null && issueImageInfoCursor.getCount() > 0) {
                while (issueImageInfoCursor.moveToNext()) {
                    IssueImageInfo issueImageInfo = new IssueImageInfo();
                    issueImageInfo.set_id(issueImageInfoCursor.getInt(0));
                    issueImageInfo.setIssueId(issueImageInfoCursor.getInt(1));
                    issueImageInfo.setIssueImage(issueImageInfoCursor.getString(2));
                    issueImageInfos.add(issueImageInfo);
                }
            }
            if (issueImageInfoCursor != null) {
                issueImageInfoCursor.close();
            }
            issueImageInfoList.add(issueImageInfos);
        }
        db.close();
        return issueImageInfoList;
    }

    public void addCare(int id, int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cared_user_id", id);
        contentValues.put("user_id", user_id);
        db.insert("careInfo", null, contentValues);
    }

    public boolean queryIsCared(int id, int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        Cursor careInfoCursor = db.query("careInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        if (careInfoCursor != null && careInfoCursor.getCount() > 0) {
            while (careInfoCursor.moveToNext()) {
                if (careInfoCursor.getInt(1) == id) {
                    careInfoCursor.close();
                    db.close();
                    return true;
                }
            }
            careInfoCursor.close();
            db.close();
            return false;
        } else {
            if (careInfoCursor != null) {
                careInfoCursor.close();
            }
            db.close();
            return false;
        }
    }

    public void deleteCare(int id, int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        db.delete("careInfo", "user_id = ? and cared_user_id = ?", new String[]{String.valueOf(user_id), String.valueOf(id)});
    }

    public List<UserInfo> queryAllUserInfoListByUserId(int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<Integer> careUserIdList = new ArrayList<>();
        Cursor caredIdInfoCursor = db.query("careInfo", new String[]{"cared_user_id"}, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        if (caredIdInfoCursor != null && caredIdInfoCursor.getCount() > 0) {
            while (caredIdInfoCursor.moveToNext()) {
                careUserIdList.add(caredIdInfoCursor.getInt(0));
            }
        }
        if (caredIdInfoCursor != null) {
            caredIdInfoCursor.close();
        }
        db.close();
        HotManager hotManager = new HotManager(context);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (Integer integer : careUserIdList) {
            UserInfo userInfo = hotManager.queryUserInfoById(integer);
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    public List<IssueInfo> getIssueInfoListByUserId(int userId) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        Cursor issueInfoCursor = db.query("issueInfo", null, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, "_id desc");
        if (issueInfoCursor != null && issueInfoCursor.getCount() > 0) {
            while (issueInfoCursor.moveToNext()) {
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.set_id(issueInfoCursor.getInt(0));
                issueInfo.setUserId(issueInfoCursor.getInt(1));
                issueInfo.setIssueContent(issueInfoCursor.getString(2));
                issueInfo.setIssueTime(issueInfoCursor.getString(3));
                issueInfoList.add(issueInfo);
            }
        }
        if (issueInfoCursor != null) {
            issueInfoCursor.close();
        }
        db.close();
        return issueInfoList;
    }

    public List<UserInfo> getChatUserInfoList(int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<UserInfo> chatUserInfoList = new ArrayList<>();
        List<Integer> chatUserIdList = new ArrayList<>();
        Cursor chatUserInfoCursor = db.query(true, "chatInfo", new String[]{"user_id_two", "user_id_one"}, "user_id_one = ? or user_id_one = ?", new String[]{String.valueOf(user_id), String.valueOf(user_id)}, null, null, null, null);
        if (chatUserInfoCursor != null && chatUserInfoCursor.getCount() > 0) {
            while (chatUserInfoCursor.moveToNext()) {
                int user_id_chat = chatUserInfoCursor.getInt(0);
                if (user_id != user_id_chat) {
                    chatUserIdList.add(Integer.valueOf(user_id_chat));
                }
            }
        }
        if (chatUserInfoCursor != null) {
            chatUserInfoCursor.close();
        }
        db.close();
        for (Integer userId : chatUserIdList) {
            UserInfo userInfo = queryUserInfoById(userId);
            chatUserInfoList.add(userInfo);
        }
        return chatUserInfoList;
    }

    public ChatInfo getChatInfo(int id, int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        ChatInfo chatInfo = new ChatInfo();
        Cursor chatInfoCursor = db.query("chatInfo", null, "user_id_one = ? or user_id_two = ?", new String[]{String.valueOf(user_id), String.valueOf(user_id)}, null, null, "_id DESC");
        if (chatInfoCursor != null && chatInfoCursor.getCount() > 0) {
            while (chatInfoCursor.moveToNext()) {
                if (chatInfoCursor.getInt(1) == id || chatInfoCursor.getInt(2) == id) {
                    chatInfo.set_id(chatInfoCursor.getInt(0));
                    chatInfo.setUserIdOne(chatInfoCursor.getInt(1));
                    chatInfo.setUserIdTwo(chatInfoCursor.getInt(2));
                    chatInfo.setChatContent(chatInfoCursor.getString(3));
                    chatInfo.setTime(chatInfoCursor.getString(4));
                    return chatInfo;
                }
            }
        }
        if (chatInfoCursor != null) {
            chatInfoCursor.close();
        }
        db.close();
        return new ChatInfo();
    }

    public List<ChatInfo> getChatInfoList(int id, int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<ChatInfo> chatInfoList = new ArrayList<>();
        Cursor chatInfoCursor = db.query("chatInfo", null, "user_id_one = ? or user_id_two = ?", new String[]{String.valueOf(user_id), String.valueOf(user_id)}, null, null, null);
        if (chatInfoCursor != null && chatInfoCursor.getCount() > 0) {
            while (chatInfoCursor.moveToNext()) {
                if (chatInfoCursor.getInt(1) == id || chatInfoCursor.getInt(2) == id) {
                    ChatInfo chatInfo = new ChatInfo();
                    chatInfo.set_id(chatInfoCursor.getInt(0));
                    chatInfo.setUserIdOne(chatInfoCursor.getInt(1));
                    chatInfo.setUserIdTwo(chatInfoCursor.getInt(2));
                    chatInfo.setChatContent(chatInfoCursor.getString(3));
                    chatInfo.setTime(chatInfoCursor.getString(4));
                    chatInfoList.add(chatInfo);
                }
            }
        }
        if (chatInfoCursor != null) {
            chatInfoCursor.close();
        }
        db.close();
        return chatInfoList;
    }

    public void addChatInfo(int id, int user_id, String chat_content, String time) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id_one", user_id);
        contentValues.put("user_id_two", id);
        contentValues.put("chat_content", chat_content);
        contentValues.put("time", time);
        db.insert("chatInfo", null, contentValues);
    }

    public List<UserInfo> getAllUserInfoList() {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<UserInfo> userInfoList = new ArrayList<>();
        Cursor userInfoCursor = db.query("userInfo", null, null, null, null, null, null);
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            while (userInfoCursor.moveToNext()) {
                UserInfo userInfo = new UserInfo();
                userInfo.set_id(userInfoCursor.getInt(0));
                userInfo.setPhoneNumber(userInfoCursor.getString(1));
                userInfo.setPassword(userInfoCursor.getString(2));
                userInfo.setNickname(userInfoCursor.getString(3));
                userInfo.setUserHead(userInfoCursor.getString(4));
                userInfo.setIsShutUp(userInfoCursor.getInt(5));
                userInfoList.add(userInfo);
            }
        }
        if (userInfoCursor != null) {
            userInfoCursor.close();
        }
        db.close();
        return userInfoList;
    }

    public void updateUserShutUpInfo(int id, int isShutUp) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_shut_up", isShutUp);
        db.update("userInfo", contentValues, "_id = ?", new String[]{String.valueOf(id)});

    }

    public List<SearchHistoryInfo> getSearchHistoryById(int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<SearchHistoryInfo> searchHistoryInfoList = new ArrayList<>();
        Cursor searchHistoryInfoCursor = db.query("searchHistoryInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        if (searchHistoryInfoCursor != null && searchHistoryInfoCursor.getCount() > 0) {
            while (searchHistoryInfoCursor.moveToNext()) {
                SearchHistoryInfo searchHistoryInfo = new SearchHistoryInfo();
                searchHistoryInfo.set_id(searchHistoryInfoCursor.getInt(0));
                searchHistoryInfo.setUserId(searchHistoryInfoCursor.getInt(1));
                searchHistoryInfo.setSearchContent(searchHistoryInfoCursor.getString(2));
                searchHistoryInfo.setSearchCount(searchHistoryInfoCursor.getInt(3));
                searchHistoryInfoList.add(searchHistoryInfo);
            }
        }
        if (searchHistoryInfoCursor != null) {
            searchHistoryInfoCursor.close();
        }
        db.close();
        return searchHistoryInfoList;
    }

    public void deleteSearchInfoById(int id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        db.delete("searchHistoryInfo", "_id = ?", new String[]{String.valueOf(id)});
    }

    public void deleteAllSearchInfo(int user_id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        db.delete("searchHistoryInfo", "user_id = ?", new String[]{String.valueOf(user_id)});
    }

    public List<IssueInfo> searchIssueInfoList(String search) {
        List<IssueInfo> issueInfoList = new ArrayList<>();
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        Cursor issueInfoCursor = db.query("issueInfo", null, "issue_content like ?", new String[]{"%" + search + "%"}, null, null, null);
        if (issueInfoCursor != null && issueInfoCursor.getCount() > 0) {
            while (issueInfoCursor.moveToNext()) {
                IssueInfo issueInfo = new IssueInfo();
                issueInfo.set_id(issueInfoCursor.getInt(0));
                issueInfo.setUserId(issueInfoCursor.getInt(1));
                issueInfo.setIssueContent(issueInfoCursor.getString(2));
                issueInfo.setIssueTime(issueInfoCursor.getString(3));
                issueInfoList.add(issueInfo);
            }
        }
        if (issueInfoCursor != null) {
            issueInfoCursor.close();
        }
        db.close();
        return issueInfoList;
    }

    public List<List<IssueImageInfo>> searchIssueImageInfoList(List<IssueInfo> searchIssueInfoList) {
        List<List<IssueImageInfo>> issueImageInfoListByIssueInfoList = getIssueImageInfoListByIssueInfoList(searchIssueInfoList);
        return issueImageInfoListByIssueInfoList;
    }

    public void addSearchHistory(int user_id, String search) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("search_content", search);
        contentValues.put("search_count", 1);
        db.insert("searchHistoryInfo", null, contentValues);
    }

    public List<IssueImageInfo> getIssueImageInfoListByIssueInfo(IssueInfo issueInfo) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<IssueImageInfo> issueImageInfoList = new ArrayList<>();
        Cursor issueImageInfoCursor = db.query("issueImageInfo", null, "issue_id = ?", new String[]{String.valueOf(issueInfo.get_id())}, null, null, null);
        if (issueImageInfoCursor != null && issueImageInfoCursor.getCount() > 0) {
            while (issueImageInfoCursor.moveToNext()) {
                IssueImageInfo issueImageInfo = new IssueImageInfo();
                issueImageInfo.set_id(issueImageInfoCursor.getInt(0));
                issueImageInfo.setIssueId(issueImageInfoCursor.getInt(1));
                issueImageInfo.setIssueImage(issueImageInfoCursor.getString(2));
                issueImageInfoList.add(issueImageInfo);
            }
        }
        if (issueImageInfoCursor != null) {
            issueImageInfoCursor.close();
        }
        db.close();
        return issueImageInfoList;
    }

    public List<CommentInfo> getCommentInfoList(int id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<CommentInfo> commentInfoList = new ArrayList<>();
        Cursor commentInfoCursor = db.query("commentInfo", null, "issue_id = ?", new String[]{String.valueOf(id)}, null, null, "_id desc");
        if (commentInfoCursor != null && commentInfoCursor.getCount() > 0) {
            while (commentInfoCursor.moveToNext()) {
                if (commentInfoCursor.getInt(5) == 0) {
                    CommentInfo commentInfo = new CommentInfo();
                    commentInfo.set_id(commentInfoCursor.getInt(0));
                    commentInfo.setUserId(commentInfoCursor.getInt(1));
                    commentInfo.setIssueId(commentInfoCursor.getInt(2));
                    commentInfo.setCommentContent(commentInfoCursor.getString(3));
                    commentInfo.setTime(commentInfoCursor.getString(4));
                    commentInfo.setCommentId(commentInfoCursor.getInt(5));
                    commentInfoList.add(commentInfo);
                }
            }
        }
        if (commentInfoCursor != null) {
            commentInfoCursor.close();
        }
        db.close();
        return commentInfoList;
    }

    public List<CommentInfo> getCommentInCommentInfoList(int issueId, int id) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        List<CommentInfo> commentInCommentInfoList = new ArrayList<>();
        Cursor commentInfoCursor = db.query("commentInfo", null, "issue_id = ? and comment_id = ?", new String[]{String.valueOf(issueId), String.valueOf(id)}, null, null, "_id desc");
        if (commentInfoCursor != null && commentInfoCursor.getCount() > 0) {
            while (commentInfoCursor.moveToNext()) {
                CommentInfo commentInfo = new CommentInfo();
                commentInfo.set_id(commentInfoCursor.getInt(0));
                commentInfo.setUserId(commentInfoCursor.getInt(1));
                commentInfo.setIssueId(commentInfoCursor.getInt(2));
                commentInfo.setCommentContent(commentInfoCursor.getString(3));
                commentInfo.setTime(commentInfoCursor.getString(4));
                commentInfo.setCommentId(commentInfoCursor.getInt(5));
                commentInCommentInfoList.add(commentInfo);
            }
        }
        if (commentInfoCursor != null) {
            commentInfoCursor.close();
        }
        db.close();
        return commentInCommentInfoList;
    }

    public void addCommentInfo(ContentValues contentValues) {
        SQLiteDatabase db = NewsReaderDataBase.getWritableDatabase();
        db.insert("commentInfo",null,contentValues);
    }*/
}
