package com.domineer.triplebro.newsreader.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domineer.triplebro.newsreader.R;

public class BottomFragment extends Fragment implements View.OnClickListener {

    private View fragment_bottom;
    private LinearLayout ll_home;
    private LinearLayout ll_type;
    private LinearLayout ll_hot;
    private LinearLayout ll_live;
    private LinearLayout ll_myself;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Button bt_home;
    private Button bt_type;
    private Button bt_hot;
    private Button bt_live;
    private Button bt_myself;
    private TextView tv_home;
    private TextView tv_type;
    private TextView tv_hot;
    private TextView tv_live;
    private TextView tv_myself;

    private Button lastFunctionButton;
    private TextView lastFunctionTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_bottom = inflater.inflate(R.layout.fragment_bottom, null);
        initView();
        setOnClick();
        fragmentManager = getActivity().getFragmentManager();
        return fragment_bottom;
    }

    private void initView() {
        ll_home = fragment_bottom.findViewById(R.id.ll_home);
        ll_type = fragment_bottom.findViewById(R.id.ll_type);
        ll_hot = fragment_bottom.findViewById(R.id.ll_hot);
        ll_live = fragment_bottom.findViewById(R.id.ll_live);
        ll_myself = fragment_bottom.findViewById(R.id.ll_myself);
        bt_home = fragment_bottom.findViewById(R.id.bt_home);
        bt_type = fragment_bottom.findViewById(R.id.bt_type);
        bt_hot = fragment_bottom.findViewById(R.id.bt_hot);
        bt_live = fragment_bottom.findViewById(R.id.bt_live);
        bt_myself = fragment_bottom.findViewById(R.id.bt_myself);
        tv_home = fragment_bottom.findViewById(R.id.tv_home);
        tv_type = fragment_bottom.findViewById(R.id.tv_type);
        tv_hot = fragment_bottom.findViewById(R.id.tv_hot);
        tv_live = fragment_bottom.findViewById(R.id.tv_live);
        tv_myself = fragment_bottom.findViewById(R.id.tv_myself);

        if (lastFunctionTextView == null) {
            lastFunctionTextView = tv_home;
        }
        if (lastFunctionButton == null) {
            lastFunctionButton = bt_home;
        }

    }

    public void setOnClick() {

        ll_home.setOnClickListener(this);
        ll_type.setOnClickListener(this);
        ll_hot.setOnClickListener(this);
        ll_live.setOnClickListener(this);
        ll_myself.setOnClickListener(this);
        bt_home.setOnClickListener(this);
        bt_type.setOnClickListener(this);
        bt_hot.setOnClickListener(this);
        bt_live.setOnClickListener(this);
        bt_myself.setOnClickListener(this);
        tv_home.setOnClickListener(this);
        tv_type.setOnClickListener(this);
        tv_hot.setOnClickListener(this);
        tv_live.setOnClickListener(this);
        tv_myself.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
            case R.id.bt_home:
            case R.id.tv_home:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new HomeFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_home);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_home.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_home;
                break;
            case R.id.ll_type:
            case R.id.bt_type:
            case R.id.tv_type:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new TypeFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_type);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_type.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_type;
                break;
            case R.id.ll_hot:
            case R.id.bt_hot:
            case R.id.tv_hot:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new HotFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_hot);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_hot.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_hot;
                break;
            case R.id.ll_live:
            case R.id.bt_live:
            case R.id.tv_live:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new LiveFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_live);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_live.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_live;
                break;
            case R.id.ll_myself:
            case R.id.bt_myself:
            case R.id.tv_myself:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new MyselfFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_myself);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_myself.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_myself;
                break;
        }
    }

    private void changeImageForButton(Button lastFunctionButton, Button onClickButton) {
        switch (lastFunctionButton.getId()) {
            case R.id.bt_home:
                lastFunctionButton.setBackgroundResource(R.mipmap.home_unclick);
                break;
            case R.id.bt_type:
                lastFunctionButton.setBackgroundResource(R.mipmap.type_unclick);
                break;
            case R.id.bt_hot:
                lastFunctionButton.setBackgroundResource(R.mipmap.hot_unclick);
                break;
            case R.id.bt_live:
                lastFunctionButton.setBackgroundResource(R.mipmap.live_unclick);
                break;
            case R.id.bt_myself:
                lastFunctionButton.setBackgroundResource(R.mipmap.myself_unclick);
                break;
        }
        switch (onClickButton.getId()) {
            case R.id.bt_home:
                onClickButton.setBackgroundResource(R.mipmap.home_click);
                break;
            case R.id.bt_type:
                onClickButton.setBackgroundResource(R.mipmap.type_click);
                break;
            case R.id.bt_hot:
                onClickButton.setBackgroundResource(R.mipmap.hot_click);
                break;
            case R.id.bt_live:
                onClickButton.setBackgroundResource(R.mipmap.live_click);
                break;
            case R.id.bt_myself:
                onClickButton.setBackgroundResource(R.mipmap.myself_click);
                break;
        }
    this.lastFunctionButton =onClickButton;
    }

}
