package com.domineer.triplebro.newsreader.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.NewsActivity;
import com.domineer.triplebro.newsreader.models.CommentInfo;
import com.domineer.triplebro.newsreader.models.UserInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,16:44
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentInfo> commentInfoList;

    public CommentAdapter(Context context, List<CommentInfo> commentInfoList) {
        this.context = context;
        this.commentInfoList = commentInfoList;
    }

    public void setCommentInfoList(List<CommentInfo> commentInfoList) {
        this.commentInfoList = commentInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return commentInfoList.size();
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
            convertView = View.inflate(context, R.layout.item_comment, null);
            viewHolder.iv_comment = convertView.findViewById(R.id.iv_comment);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_comment = convertView.findViewById(R.id.tv_comment);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_comment.setText(commentInfoList.get(position).getCommentContent());
        viewHolder.tv_time.setText(commentInfoList.get(position).getTime());
        int userId = commentInfoList.get(position).getUserId();
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        UserInfo userInfo = dataBaseProvider.findUserInfoById(userId);
        viewHolder.tv_nickname.setText(userInfo.getNickname());
        String userHead = userInfo.getUserHead();
        if(userHead == null || userHead.length() == 0){
            Glide.with(context).load(R.drawable.user_head_default).into(viewHolder.iv_comment);
        }else{
            Glide.with(context).load(userHead).into(viewHolder.iv_comment);
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_comment;
        private TextView tv_nickname;
        private TextView tv_time;
        private TextView tv_comment;
    }
}
