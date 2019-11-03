package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.adapters.HomeAdapter;
import com.domineer.triplebro.newsreader.controllers.NewsListController;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.models.TypeInfo;

import java.util.List;

public class NewsListActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView tv_title;
    private TypeInfo typeInfo;
    private ImageView iv_close_news_list;
    private NewsListController newsListController;
    private ListView lv_news;
    private List<NewsInfo> newsInfoList;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initView();
        initDate();
        setOnClickListener();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_close_news_list = (ImageView) findViewById(R.id.iv_close_news_list);
        lv_news = (ListView) findViewById(R.id.lv_news);
    }

    private void initDate() {
        Intent intent = getIntent();
        typeInfo = (TypeInfo) intent.getSerializableExtra("typeInfo");
        tv_title.setText(typeInfo.getTypeName());
        newsListController = new NewsListController(this);
        newsInfoList = newsListController.findNewsInfoListByTypeId(typeInfo.get_id());
        homeAdapter = new HomeAdapter(this, newsInfoList);
        lv_news.setAdapter(homeAdapter);
    }

    private void setOnClickListener() {
        iv_close_news_list.setOnClickListener(this);
        lv_news.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_news_list:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent news = new Intent(this, NewsActivity.class);
        news.putExtra("newsInfo",newsInfoList.get(position));
        startActivity(news);
    }
}
