package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.database.NewsReaderDataBase;
import com.domineer.triplebro.newsreader.properties.ProjectProperties;
import com.domineer.triplebro.newsreader.utils.dialogUtils.ChooseUserHeadDialogUtil;
import com.domineer.triplebro.newsreader.utils.imageUtils.RealPathFromUriUtils;

import java.io.File;

public class AdminInfoActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

    private ImageView iv_user_head;
    private LinearLayout ll_user_username;
    private LinearLayout ll_user_nickname;
    private TextView tv_nickname;
    private TextView tv_username;
    private RelativeLayout rl_user_head_large;
    private ImageView iv_user_head_large;
    private ImageView iv_close_user_head_large;
    private SharedPreferences adminInfo;
    private int admin_id;
    private String username;
    private String nickname;
    private String userHead;
    private File userHeadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);
        ll_user_username = (LinearLayout) findViewById(R.id.ll_user_username);
        ll_user_nickname = (LinearLayout) findViewById(R.id.ll_user_nickname);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        tv_username = (TextView) findViewById(R.id.tv_username);
        rl_user_head_large = (RelativeLayout) findViewById(R.id.rl_user_head_large);
        iv_user_head_large = (ImageView) findViewById(R.id.iv_user_head_large);
        iv_close_user_head_large = (ImageView) findViewById(R.id.iv_close_user_head_large);
    }

    private void initData() {
        adminInfo = getSharedPreferences("adminInfo", Context.MODE_PRIVATE);
        admin_id = adminInfo.getInt("admin_id", -1);
        username = adminInfo.getString("phone_number", "");
        nickname = adminInfo.getString("nickName", "");
        userHead = adminInfo.getString("userHead", "");
        tv_username.setText("ID：" + username);
        tv_nickname.setText(nickname);
        if (TextUtils.isEmpty(userHead)) {
            Glide.with(this).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        } else {
            Glide.with(this).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head);
        }
    }

    private void setOnClickListener() {
        iv_user_head.setOnClickListener(this);
        tv_nickname.setOnClickListener(this);
        tv_username.setOnClickListener(this);
        ll_user_nickname.setOnClickListener(this);

        ll_user_username.setOnClickListener(this);
        iv_user_head.setOnLongClickListener(this);
        rl_user_head_large.setOnClickListener(this);
        iv_user_head_large.setOnLongClickListener(this);
        iv_close_user_head_large.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_head:
            case R.id.ll_user_username:
            case R.id.ll_user_nickname:
            case R.id.tv_username:
            case R.id.tv_nickname:
                Toast.makeText(this, "长按头像可查看大头像或修改头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_close_user_head_large:
            case R.id.rl_user_head_large:
                rl_user_head_large.setVisibility(View.GONE);
                setClickableTrue();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_head:
                rl_user_head_large.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(userHead)) {
                    Glide.with(this).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                } else {
                    Glide.with(this).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                }
                setClickableFalse();
                break;
            case R.id.iv_user_head_large:
                long timeStamp = System.currentTimeMillis();
                SharedPreferences.Editor edit = adminInfo.edit();
                edit.putLong("timeStamp", timeStamp);
                edit.commit();
                ChooseUserHeadDialogUtil.showDialog(this, username, timeStamp);
                break;
        }
        return true;
    }

    private void setClickableFalse() {
        iv_user_head.setClickable(false);
        tv_nickname.setClickable(false);
        tv_username.setClickable(false);
        ll_user_username.setClickable(false);
        ll_user_nickname.setClickable(false);
    }

    private void setClickableTrue() {
        iv_user_head.setClickable(true);
        tv_nickname.setClickable(true);
        tv_username.setClickable(true);
        ll_user_nickname.setClickable(true);
        ll_user_username.setClickable(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        SharedPreferences.Editor edit = adminInfo.edit();
        NewsReaderDataBase myOpenHelper = new NewsReaderDataBase(this);
        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    userHeadFile = new File(RealPathFromUriUtils.getRealPathFromUri(this, data.getData()));
                    Glide.with(this).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                    contentValues.put("user_head", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    long timeStamp = adminInfo.getLong("timeStamp", -1);
                    userHeadFile = new File(getFilesDir() + File.separator + "images" + File.separator + username + timeStamp + ".jpg");
                    Glide.with(this).load(userHeadFile).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_user_head_large);
                    edit.putString("userHead", userHeadFile.getAbsolutePath());
                    contentValues.put("user_head", userHeadFile.getAbsolutePath());
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {
            edit.commit();
            writableDatabase.update("adminInfo", contentValues, "phone_number = ?", new String[]{username});
            writableDatabase.close();
        } else {
            Toast.makeText(this, "取消修改", Toast.LENGTH_SHORT).show();
        }
        initData();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
