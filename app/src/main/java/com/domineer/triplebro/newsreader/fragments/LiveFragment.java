package com.domineer.triplebro.newsreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domineer.triplebro.newsreader.R;

public class LiveFragment extends Fragment {

    private View fragment_live;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_live = inflater.inflate(R.layout.fragment_live, null);
        return fragment_live;
    }
}
