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
import com.domineer.triplebro.newsreader.controllers.LiveController;
import com.domineer.triplebro.newsreader.models.AdminInfo;
import com.domineer.triplebro.newsreader.models.CommentInfo;
import com.domineer.triplebro.newsreader.models.UserInfo;
import com.domineer.triplebro.newsreader.models.VideoInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoInfo videoInfo;
    private JCVideoPlayerStandard vp_live;
    private TextView tv_playTime;
    private TextView tv_time;
    private TextView tv_author;
    private TextView tv_video_title;
    private TextView tv_like;
    private TextView tv_clear;
    private TextView tv_submit_comment;
    private TextView tv_tip;
    private TextView tv_tip_inside;
    private EditText et_comment;
    private ImageView iv_collect;
    private ImageView iv_like;
    private SharedPreferences userInfo;
    private int user_id;
    private RelativeLayout rl_comment;
    private RelativeLayout rl_comment_inside;
    private LiveController liveController;
    private ListView lv_comment;
    private CommentAdapter commentAdapter;
    private List<CommentInfo> commentInfoList;
    private ImageView iv_close_video;
    private boolean isCollect;
    private int isShutUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_video = (ImageView) findViewById(R.id.iv_close_video);
        vp_live = (JCVideoPlayerStandard) findViewById(R.id.vp_live);
        tv_playTime = (TextView) findViewById(R.id.tv_playTime);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_video_title = (TextView) findViewById(R.id.tv_video_title);
        tv_like = (TextView) findViewById(R.id.tv_like);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_submit_comment = (TextView) findViewById(R.id.tv_submit_comment);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        tv_tip_inside = (TextView) findViewById(R.id.tv_tip_inside);
        et_comment = (EditText) findViewById(R.id.et_comment);
        iv_collect = (ImageView) findViewById(R.id.iv_collect);
        iv_like = (ImageView) findViewById(R.id.iv_like);
        rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);
        rl_comment_inside = (RelativeLayout) findViewById(R.id.rl_comment_inside);
        lv_comment = (ListView) findViewById(R.id.lv_comment);
    }

    private void initData() {
        Intent intent = getIntent();
        videoInfo = (VideoInfo) intent.getSerializableExtra("videoInfo");
        liveController = new LiveController(this);
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        isShutUp = userInfo.getInt("isShutUp", -1);
        isCollect = liveController.getVideoIsCollect(videoInfo.get_id(),user_id);
        liveController.updateVideoPlay(videoInfo.get_id(),videoInfo.getPlayTime());
        if(user_id == -1){
            tv_tip.setVisibility(View.VISIBLE);
            rl_comment.setVisibility(View.GONE);
        }else{
            tv_tip.setVisibility(View.GONE);
            rl_comment.setVisibility(View.VISIBLE);
            commentInfoList = liveController.findCommentInfoListByVideoId(videoInfo.get_id());
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
        tv_video_title.setText(videoInfo.getTitle());
        int playTime = videoInfo.getPlayTime()+1;
        tv_playTime.setText("播放次数：" + playTime);
        tv_like.setText(String.valueOf(videoInfo.getLikeTime()));
        tv_time.setText(videoInfo.getTime());
        DataBaseProvider dataBaseProvider = new DataBaseProvider(this);
        AdminInfo authorInfo = dataBaseProvider.findAuthorInfo(videoInfo.getAuthor());
        tv_author.setText("作者："+authorInfo.getNickname());
        if(isCollect){
            iv_collect.setBackgroundResource(R.mipmap.collection_click);
        }
        boolean setUp = vp_live.setUp(videoInfo.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            String image = videoInfo.getImage();
            if (image != null && image.length() != 0) {
                Glide.with(this).load(image).into(vp_live.thumbImageView);
            }
        }
    }

    private void setOnClickListener() {
        iv_close_video.setOnClickListener(this);
        tv_submit_comment.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
        iv_collect.setOnClickListener(this);
        iv_like.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_video:
                finish();
                break;
            case R.id.tv_submit_comment:
                if(isShutUp == 1){
                    UserInfo userInfo = liveController.findUserInfo(user_id);
                    Toast.makeText(this, "禁言原因："+userInfo.getShutUpReason()+"禁言时间："+userInfo.getShutUpStartTime()+" - "+userInfo.getShutUpEndTime()+"您已被禁言，请联系管理员解禁！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String commentContent = et_comment.getText().toString().trim();
                if(commentContent.length() != 0){
                    long comment_result = liveController.submitComment(user_id, videoInfo.get_id(), commentContent);
                    if (comment_result >0){
                        commentInfoList = liveController.findCommentInfoListByVideoId(videoInfo.get_id());
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
            case R.id.iv_like:
                tv_like.setText(String.valueOf(videoInfo.getLikeTime()+1));
                liveController.updateVideoLike(videoInfo.getLikeTime(),videoInfo.get_id());
                videoInfo.setLikeTime(videoInfo.getLikeTime()+1);
                break;
            case R.id.iv_collect:
                if(user_id == -1){
                    Toast.makeText(this, "还没登录呢，不能管理收藏！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isCollect){
                    isCollect = false;
                    iv_collect.setBackgroundResource(R.mipmap.collection);
                    liveController.deleteVideoCollect(user_id,videoInfo.get_id());
                }else{
                    isCollect = true;
                    iv_collect.setBackgroundResource(R.mipmap.collection_click);
                    liveController.addVideoCollect(user_id,videoInfo.get_id());
                }
                break;
        }
    }
}
