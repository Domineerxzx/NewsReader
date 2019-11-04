package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.adapters.CommentAdapter;
import com.domineer.triplebro.newsreader.controllers.NewsController;
import com.domineer.triplebro.newsreader.models.AdminInfo;
import com.domineer.triplebro.newsreader.models.CommentInfo;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.models.ReadInfo;
import com.domineer.triplebro.newsreader.models.UserInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

public class NewsActivity extends Activity implements View.OnClickListener {

    private TextView tv_news_title;
    private TextView tv_time;
    private TextView tv_author;
    private TextView tv_content;
    private TextView tv_clear;
    private TextView tv_submit_comment;
    private ImageView iv_news;
    private EditText et_comment;
    private ImageView iv_close_news;
    private ListView lv_comment;
    private NewsInfo newsInfo;
    private NewsController newsController;
    private AdminInfo adminInfo;
    private int user_id;
    private SharedPreferences userInfo;
    private RelativeLayout rl_comment;
    private TextView tv_tip;
    private List<CommentInfo> commentInfoList;
    private TextView tv_tip_inside;
    private CommentAdapter commentAdapter;
    private TextView tv_read_time;
    private ReadInfo readInfo;
    private ImageView iv_collect;
    private boolean isCollect;
    private int isShutUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_news_title = (TextView) findViewById(R.id.tv_news_title);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_submit_comment = (TextView) findViewById(R.id.tv_submit_comment);
        iv_news = (ImageView) findViewById(R.id.iv_news);
        et_comment = (EditText) findViewById(R.id.et_comment);
        iv_close_news = (ImageView) findViewById(R.id.iv_close_news);
        lv_comment = (ListView) findViewById(R.id.lv_comment);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        tv_tip_inside = (TextView) findViewById(R.id.tv_tip_inside);
        tv_read_time = (TextView) findViewById(R.id.tv_read_time);
        iv_collect = (ImageView) findViewById(R.id.iv_collect);
    }

    private void initData() {
        newsController = new NewsController(this);
        DataBaseProvider dataBaseProvider = new DataBaseProvider(this);
        Intent intent = getIntent();
        newsInfo = (NewsInfo) intent.getSerializableExtra("newsInfo");
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        isShutUp = userInfo.getInt("isShutUp", -1);
        isCollect = newsController.getIsCollect(newsInfo.get_id(),user_id);
        if(user_id == -1){
            tv_tip.setVisibility(View.VISIBLE);
            rl_comment.setVisibility(View.GONE);
        }else{
            tv_tip.setVisibility(View.GONE);
            rl_comment.setVisibility(View.VISIBLE);
            commentInfoList = newsController.findCommentInfoListByNewsId(newsInfo.get_id());
            if(commentInfoList.size() == 0){
                tv_tip_inside.setVisibility(View.VISIBLE);
                lv_comment.setVisibility(View.GONE);
            }else{
                tv_tip_inside.setVisibility(View.GONE);
                lv_comment.setVisibility(View.VISIBLE);
                commentAdapter = new CommentAdapter(this, commentInfoList);
                lv_comment.setAdapter(commentAdapter);
            }
        }
        adminInfo = dataBaseProvider.findAuthorInfo(newsInfo.getAuthor());
        readInfo = dataBaseProvider.findReadInfoById(newsInfo.get_id());
        String newsInfoImage = newsInfo.getImage();
        if(newsInfoImage == null || newsInfoImage.length() == 0){
            Glide.with(this).load(R.drawable.image_default).into(iv_news);
        }else{
            Glide.with(this).load(newsInfoImage).into(iv_news);
        }
        tv_news_title.setText(newsInfo.getTitle());
        tv_content.setText(newsInfo.getContent());
        tv_time.setText(newsInfo.getTime());
        tv_author.setText("作者："+adminInfo.getNickname());
        int readTime = readInfo.getReadTime() + 1;
        tv_read_time.setText("阅读次数："+readTime);
        dataBaseProvider.updateReadTime(readInfo);
        if(isCollect){
            iv_collect.setBackgroundResource(R.mipmap.collection_click);
        }
    }

    private void setOnClickListener() {
        iv_close_news.setOnClickListener(this);
        tv_submit_comment.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
        iv_collect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_news:
                finish();
                break;
            case R.id.tv_submit_comment:
                if(isShutUp == 1){
                    UserInfo userInfo = newsController.findUserInfo(user_id);
                    Toast.makeText(this, "禁言原因："+userInfo.getShutUpReason()+"禁言时间："+userInfo.getShutUpStartTime()+" - "+userInfo.getShutUpEndTime()+"您已被禁言，请联系管理员解禁！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String commentContent = et_comment.getText().toString().trim();
                if(commentContent.length() != 0){
                    long comment_result = newsController.submitComment(user_id, newsInfo.get_id(), commentContent);
                    if (comment_result >0){
                        commentInfoList = newsController.findCommentInfoListByNewsId(newsInfo.get_id());
                        commentAdapter.setCommentInfoList(commentInfoList);
                        et_comment.setText("");
                    }else {
                        Toast.makeText(this, "评论失败，请联系开发人员！！！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    Toast.makeText(this, "评论内容不能为空！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.tv_clear:
                et_comment.setText("");
                break;
            case R.id.iv_collect:
                if(user_id == -1){
                    Toast.makeText(this, "还没登录呢，不能管理收藏！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isCollect){
                    isCollect = false;
                    iv_collect.setBackgroundResource(R.mipmap.collection);
                    newsController.deleteNewsCollect(user_id,newsInfo.get_id());
                }else{
                    isCollect = true;
                    iv_collect.setBackgroundResource(R.mipmap.collection_click);
                    newsController.addNewsCollect(user_id,newsInfo.get_id());
                }
                break;
        }
    }
}
