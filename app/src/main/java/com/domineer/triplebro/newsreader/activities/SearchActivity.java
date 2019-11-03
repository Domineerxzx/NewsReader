package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.adapters.HomeAdapter;
import com.domineer.triplebro.newsreader.adapters.SearchHistoryAdapter;
import com.domineer.triplebro.newsreader.controllers.HomeController;
import com.domineer.triplebro.newsreader.controllers.SearchController;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.models.SearchInfo;
import com.domineer.triplebro.newsreader.utils.dialogUtils.TwoButtonDialog;
import com.domineer.triplebro.newsreader.views.MyListView;

import java.util.List;

public class SearchActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MyListView lv_history;
    private RecyclerView rv_recommend_search;
    private ListView lv_search_result;
    private ImageView iv_close_search;
    private SearchHistoryAdapter searchHistoryAdapter;
    private EditText et_search;
    private SearchController searchController;
    private RelativeLayout rl_history;
    private RelativeLayout rl_no_search;
    private SharedPreferences searchHistory;
    private TextView tv_no_history;
    private TextView tv_clear_history;
    private SharedPreferences userInfo;
    private int user_id;
    private List<SearchInfo> searchInfoList;
    private List<NewsInfo> newsInfoList;
    private HomeAdapter searchResultAdapter;
    private TextView tv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        iv_close_search.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        lv_history.setOnItemClickListener(this);
        tv_clear_history.setOnClickListener(this);
        lv_search_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent news = new Intent(SearchActivity.this, NewsActivity.class);
                news.putExtra("newsInfo",newsInfoList.get(position));
                startActivity(news);
            }
        });
    }

    private void initData() {
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        searchController = new SearchController(this);
        searchInfoList = searchController.getSearchHistoryById(user_id);
        if(searchInfoList.size() == 0){
            tv_no_history.setVisibility(View.VISIBLE);
            lv_history.setVisibility(View.GONE);
        }else{
            tv_no_history.setVisibility(View.GONE);
            lv_history.setVisibility(View.VISIBLE);
        }
        searchHistoryAdapter = new SearchHistoryAdapter(this, searchInfoList,tv_no_history,lv_history);
        lv_history.setAdapter(searchHistoryAdapter);
    }

    private void initView() {
        lv_history = (MyListView) findViewById(R.id.lv_history);
        lv_search_result = (ListView) findViewById(R.id.lv_search_result);
        iv_close_search = (ImageView) findViewById(R.id.iv_close_search);
        et_search = (EditText) findViewById(R.id.et_search);
        rl_history = (RelativeLayout) findViewById(R.id.rl_history);
        rl_no_search = (RelativeLayout) findViewById(R.id.rl_no_search);
        tv_no_history = (TextView) findViewById(R.id.tv_no_history);
        tv_clear_history = (TextView) findViewById(R.id.tv_clear_history);
        tv_search = (TextView) findViewById(R.id.tv_search);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_search:
                finish();
                break;
            case R.id.tv_search:
                String searchContent = et_search.getText().toString().trim();
                if (searchContent.length() > 0) {
                    newsInfoList = searchController.searchNewsInfoList(searchContent);
                    if (newsInfoList.size() != 0) {
                        rl_history.setVisibility(View.GONE);
                        lv_search_result.setVisibility(View.VISIBLE);
                        rl_no_search.setVisibility(View.GONE);
                        searchResultAdapter = new HomeAdapter(this, newsInfoList);
                        lv_search_result.setAdapter(searchResultAdapter);
                        tv_no_history.setVisibility(View.GONE);
                        lv_history.setVisibility(View.VISIBLE);
                        searchController.addSearchHistory(user_id,searchContent);
                    } else {
                        rl_history.setVisibility(View.GONE);
                        lv_search_result.setVisibility(View.GONE);
                        rl_no_search.setVisibility(View.VISIBLE);
                    }
                } else {
                    rl_history.setVisibility(View.VISIBLE);
                    lv_search_result.setVisibility(View.GONE);
                    rl_no_search.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_clear_history:
                TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
                twoButtonDialog.show("清空搜索历史记录", "是否确认清空搜索历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchController.deleteAllSearchInfo(user_id);
                        tv_no_history.setVisibility(View.VISIBLE);
                        lv_history.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },getFragmentManager());
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        et_search.setText(searchInfoList.get(position).getSearchContent());
    }
}
