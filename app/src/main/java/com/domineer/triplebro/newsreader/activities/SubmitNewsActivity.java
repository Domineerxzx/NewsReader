package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.controllers.SubmitNewsController;
import com.domineer.triplebro.newsreader.database.NewsReaderDataBase;
import com.domineer.triplebro.newsreader.properties.ProjectProperties;
import com.domineer.triplebro.newsreader.utils.dialogUtils.ChooseNewsImageDialogUtil;
import com.domineer.triplebro.newsreader.utils.dialogUtils.ChooseUserHeadDialogUtil;
import com.domineer.triplebro.newsreader.utils.dialogUtils.SingleChooseDialog;
import com.domineer.triplebro.newsreader.utils.imageUtils.RealPathFromUriUtils;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class SubmitNewsActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_submit_news;
    private EditText et_news_title;
    private EditText et_news_content;
    private TextView tv_submit_news;
    private ImageView iv_news_image;
    private LinearLayout ll_delete_image;
    private ImageView iv_delete_image;
    private SubmitNewsController submitNewsController;
    private SharedPreferences adminInfo;
    private String username;
    private File newsImageFile;
    private int admin_id;
    private TextView tv_news_type;
    private Map<String, Integer> typeMap;
    private Set<String> typeNameSet;
    private String[] typeNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_news);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_submit_news = (ImageView) findViewById(R.id.iv_close_submit_news);
        et_news_title = (EditText) findViewById(R.id.et_news_title);
        et_news_content = (EditText) findViewById(R.id.et_news_content);
        iv_news_image = (ImageView) findViewById(R.id.iv_news_image);
        ll_delete_image = (LinearLayout) findViewById(R.id.ll_delete_image);
        iv_delete_image = (ImageView) findViewById(R.id.iv_delete_image);
        tv_submit_news = (TextView) findViewById(R.id.tv_submit_news);
        tv_news_type = (TextView) findViewById(R.id.tv_news_type);
        iv_news_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void initData() {
        adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
        username = adminInfo.getString("phone_number","");
        admin_id = adminInfo.getInt("admin_id", -1);
        submitNewsController = new SubmitNewsController(this);
        typeMap = submitNewsController.findTypeInfoMap();
        typeNameSet = typeMap.keySet();
        typeNames = new String[typeNameSet.size()];
        int i = 0;
        for (String typeName : typeNameSet) {
            typeNames[i] = typeName;
            i++;
        }
        Glide.with(this).load(R.drawable.submit).into(iv_news_image);
    }

    private void setOnClickListener() {
        iv_close_submit_news.setOnClickListener(this);
        tv_submit_news.setOnClickListener(this);
        iv_news_image.setOnClickListener(this);
        iv_delete_image.setOnClickListener(this);
        ll_delete_image.setOnClickListener(this);
        tv_news_type.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_submit_news:
                finish();
                break;
            case R.id.tv_news_type:
                SingleChooseDialog singleChooseDialog = new SingleChooseDialog();
                singleChooseDialog.show("新闻类别", typeNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        tv_news_type.setText(typeNames[which]);
                    }
                },getFragmentManager());
                break;
            case R.id.tv_submit_news:
                String newsTitle = et_news_title.getText().toString();
                String newsContent = et_news_content.getText().toString();
                String image = newsImageFile.getAbsolutePath();
                String typeName = tv_news_type.getText().toString();
                if(typeName.equals("请选择新闻类别")){
                    Toast.makeText(this, "新闻类别不能为空！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                int newsTypeId = typeMap.get(typeName);
                if(newsTitle.length() == 0){
                    Toast.makeText(this, "新闻标题不能为空！！！", Toast.LENGTH_SHORT).show();
                    return;
                }else if(newsContent.length() == 0){
                    Toast.makeText(this, "新闻内容不能为空！！！", Toast.LENGTH_SHORT).show();
                    return;
                }else if(image.length() == 0){
                    Toast.makeText(this, "新闻图片不能为空！！！", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    submitNewsController.submitNews(admin_id,newsTitle,newsContent,image,newsTypeId);
                    et_news_title.setText("");
                    et_news_content.setText("");
                    tv_news_type.setText("请选择新闻类别");
                    Glide.with(this).load(R.drawable.submit).into(iv_news_image);
                }
                break;
            case R.id.iv_news_image:
                long timeStamp = System.currentTimeMillis();
                ChooseNewsImageDialogUtil.showDialog(this, username, timeStamp);
                break;
            case R.id.iv_delete_image:
            case R.id.ll_delete_image:
                Glide.with(this).load(R.drawable.image_default).into(iv_news_image);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    newsImageFile = new File(RealPathFromUriUtils.getRealPathFromUri(this, data.getData()));
                    Glide.with(this).load(newsImageFile).into(iv_news_image);
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    long timeStamp = adminInfo.getLong("timeStamp", -1);
                    newsImageFile = new File(getFilesDir() + File.separator + "images" + File.separator + username + timeStamp + ".jpg");
                    Glide.with(this).load(newsImageFile).into(iv_news_image);
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (!isCheck) {
            Toast.makeText(this, "取消发布", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
