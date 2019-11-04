package com.domineer.triplebro.newsreader.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.ShutUpActivity;
import com.domineer.triplebro.newsreader.controllers.ShutUpController;
import com.domineer.triplebro.newsreader.fragments.NotShutUpUserFragment;
import com.domineer.triplebro.newsreader.models.AdminInfo;
import com.domineer.triplebro.newsreader.models.NewsInfo;
import com.domineer.triplebro.newsreader.models.UserInfo;
import com.domineer.triplebro.newsreader.providers.DataBaseProvider;
import com.domineer.triplebro.newsreader.utils.dialogUtils.SingleChooseDialog;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,11:28
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class NotShutUpAdapter extends BaseAdapter {

    private Context context;
    private List<UserInfo> userInfoList;

    public NotShutUpAdapter(Context context, List<UserInfo> userInfoList) {
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_not_shut_up, null);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_shut_up_reason = convertView.findViewById(R.id.tv_shut_up_reason);
            viewHolder.tv_shut_up = convertView.findViewById(R.id.tv_shut_up);
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
        viewHolder.tv_shut_up_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleChooseDialog singleChooseDialog = new SingleChooseDialog();
                final String[] chooseItems = {"言论含有辱骂他人信息", "恶意刷屏","散播非法信息"};
                singleChooseDialog.show("禁言原因", chooseItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        viewHolder.tv_shut_up_reason.setText(chooseItems[which]);
                    }
                },((AppCompatActivity)context).getFragmentManager());
            }
        });
        viewHolder.tv_shut_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shutUpReason = viewHolder.tv_shut_up_reason.getText().toString();
                if(shutUpReason.length() == 0 || shutUpReason.equals("请选择禁言原因")){
                    Toast.makeText(context, "未选择禁言原因！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                ShutUpController shutUpController = new ShutUpController(context);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                long startTimeStamp = System.currentTimeMillis();
                long endTimeStamp = System.currentTimeMillis()+3*24*60*60*1000;
                String startTime = simpleDateFormat.format(startTimeStamp);
                String endTime = simpleDateFormat.format(endTimeStamp);
                shutUpController.shutUpUser(userInfoList.get(position).get_id(),startTime,endTime, shutUpReason);
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
        private TextView tv_shut_up;
    }
}
