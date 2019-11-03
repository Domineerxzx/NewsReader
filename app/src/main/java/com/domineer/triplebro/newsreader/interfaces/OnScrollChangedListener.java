package com.domineer.triplebro.newsreader.interfaces;


import com.domineer.triplebro.newsreader.views.MyScrollView;

public interface OnScrollChangedListener {

    void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy);
}
