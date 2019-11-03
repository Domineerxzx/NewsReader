package com.domineer.triplebro.newsreader.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.models.AdminInfo;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,11:28
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HotAdapter extends BaseAdapter {

    private Context context;
    private List<NewsInfo> newsInfoList;

    public HotAdapter(Context context, List<NewsInfo> newsInfoList) {
        this.context = context;
        this.newsInfoList = newsInfoList;
    }

    public void setNewsInfoList(List<NewsInfo> newsInfoList) {
        this.newsInfoList = newsInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return newsInfoList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_hot, null);
            viewHolder.tv_flag = convertView.findViewById(R.id.tv_flag);
            viewHolder.iv_news = convertView.findViewById(R.id.iv_news);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            viewHolder.tv_author = convertView.findViewById(R.id.tv_author);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.iv_author = convertView.findViewById(R.id.iv_author);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int flag = position+1;
        viewHolder.tv_flag.setText("TOP"+flag);
        if(position == 0  ){
            viewHolder.tv_flag.setTextColor(0xFFFFD700);
        }else if(position == 1){
            viewHolder.tv_flag.setTextColor(0xFFC0C0C0);
        }else if(position == 2){
            viewHolder.tv_flag.setTextColor(0xFFA78E44);
        }else{
            viewHolder.tv_flag.setTextColor(Color.BLACK);
        }
        viewHolder.iv_news.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.iv_author.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.tv_title.setText(newsInfoList.get(position).getTitle());
        viewHolder.tv_content.setText(newsInfoList.get(position).getContent());
        viewHolder.tv_time.setText(newsInfoList.get(position).getTime());
        String newsImage = newsInfoList.get(position).getImage();
        if (newsImage == null || newsImage.length() == 0) {
            Glide.with(context).load(R.drawable.image_default).into(viewHolder.iv_news);
        } else {
            Glide.with(context).load(newsImage).into(viewHolder.iv_news);
        }
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        AdminInfo adminInfo = dataBaseProvider.findAuthorInfo(newsInfoList.get(position).getAuthor());
        viewHolder.tv_author.setText(adminInfo.getNickname());
        String authorHead = adminInfo.getUserHead();
        if (authorHead == null || authorHead.length() == 0) {
            Glide.with(context).load(R.drawable.user_head_default).into(viewHolder.iv_author);
        } else {
            Glide.with(context).load(authorHead).into(viewHolder.iv_author);
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_flag;
        private ImageView iv_news;
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_author;
        private TextView tv_time;
        private ImageView iv_author;
    }
}
