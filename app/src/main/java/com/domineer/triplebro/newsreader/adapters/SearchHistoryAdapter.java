package com.domineer.triplebro.newsreader.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.controllers.SearchController;
import com.domineer.triplebro.newsreader.models.SearchInfo;

import java.util.List;

public class SearchHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<SearchInfo> searchInfoList;
    private TextView tv_no_history;
    private ListView lv_history;

    public SearchHistoryAdapter(Context context, List<SearchInfo> searchInfoList, TextView tv_no_history, ListView lv_history) {
        this.context = context;
        this.searchInfoList = searchInfoList;
        this.tv_no_history = tv_no_history;
        this.lv_history = lv_history;
    }

    public void setSearchInfoList(List<SearchInfo> searchInfoList) {
        this.searchInfoList = searchInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return searchInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_search_history, null);
            viewHolder.tv_history = convertView.findViewById(R.id.tv_history);
            viewHolder.iv_delete_history = convertView.findViewById(R.id.iv_delete_history);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_history.setText(searchInfoList.get(position).getSearchContent());
        viewHolder.iv_delete_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchController searchController = new SearchController(context);
                searchController.deleteSearchInfoById(searchInfoList.get(position).get_id());
                searchInfoList.remove(position);
                setSearchInfoList(searchInfoList);
                if(searchInfoList.size() == 0){
                    lv_history.setVisibility(View.GONE);
                    tv_no_history.setVisibility(View.VISIBLE);
                }else{
                    lv_history.setVisibility(View.VISIBLE);
                    tv_no_history.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_history;
        private ImageView iv_delete_history;
    }
}
