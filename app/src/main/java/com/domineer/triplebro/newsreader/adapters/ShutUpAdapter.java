package com.domineer.triplebro.newsreader.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.ShutUpActivity;
import com.domineer.triplebro.newsreader.controllers.ShutUpController;
import com.domineer.triplebro.newsreader.models.UserInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,11:28
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ShutUpAdapter extends BaseAdapter {

    private Context context;
    private List<UserInfo> userInfoList;

    public ShutUpAdapter(Context context, List<UserInfo> userInfoList) {
        this.context = context;
        this.userInfoList = userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return userInfoList.size();
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
            convertView = View.inflate(context, R.layout.item_shut_up, null);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_shut_up_reason = convertView.findViewById(R.id.tv_shut_up_reason);
            viewHolder.tv_shut_time = convertView.findViewById(R.id.tv_shut_time);
            viewHolder.tv_cancel_shut_up = convertView.findViewById(R.id.tv_cancel_shut_up);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String userHead = userInfoList.get(position).getUserHead();
        if(userHead != null && userHead.length()>0){
            Glide.with(context).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }else{
            Glide.with(context).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }
        viewHolder.tv_nickname.setText(userInfoList.get(position).getNickname());
        viewHolder.tv_shut_up_reason.setText(userInfoList.get(position).getShutUpReason());
        viewHolder.tv_shut_time.setText("禁言时间："+userInfoList.get(position).getShutUpStartTime()+" - "+ userInfoList.get(position).getShutUpEndTime());
        viewHolder.tv_cancel_shut_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShutUpController shutUpController = new ShutUpController(context);
                shutUpController.cancelShutUpUser(userInfoList.get(position).get_id());
                userInfoList.remove(position);
                setUserInfoList(userInfoList);
                ((ShutUpActivity)context).finish();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_user_head;
        private TextView tv_nickname;
        private TextView tv_shut_up_reason;
        private TextView tv_shut_time;
        private TextView tv_cancel_shut_up;
    }
}
