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
import com.domineer.triplebro.newsreader.models.VideoInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author Domineer
 * @data 2019/11/2,11:28
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class LiveAdapter extends BaseAdapter {

    private Context context;
    private List<VideoInfo> videoInfoList;
    private AdminInfo authorInfo;

    public LiveAdapter(Context context, List<VideoInfo> videoInfoList) {
        this.context = context;
        this.videoInfoList = videoInfoList;
    }

    public void setVideoInfoList(List<VideoInfo> videoInfoList) {
        this.videoInfoList = videoInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return videoInfoList.size();
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
            convertView = View.inflate(context, R.layout.item_live, null);
            viewHolder.iv_live = convertView.findViewById(R.id.iv_live);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_author = convertView.findViewById(R.id.tv_author);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.tv_playTime = convertView.findViewById(R.id.tv_playTime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(videoInfoList.get(position).getTitle());
        viewHolder.tv_time.setText(videoInfoList.get(position).getTime());
        viewHolder.tv_playTime.setText("播放次数：" + videoInfoList.get(position).getPlayTime());
        int author = videoInfoList.get(position).getAuthor();
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        authorInfo = dataBaseProvider.findAuthorInfo(author);
        viewHolder.tv_author.setText("作者："+authorInfo.getNickname());
        String videoImage = videoInfoList.get(position).getImage();
        if (videoImage != null && videoImage.length() != 0) {
            Glide.with(context).load(videoImage).into(viewHolder.iv_live);
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_live;
        private TextView tv_title;
        private TextView tv_time;
        private TextView tv_author;
        private TextView tv_playTime;
    }
}
