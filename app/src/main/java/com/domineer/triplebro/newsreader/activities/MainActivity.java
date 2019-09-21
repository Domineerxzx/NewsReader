package com.domineer.triplebro.newsreader.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.fragments.BottomFragment;
import com.domineer.triplebro.newsreader.fragments.HomeFragment;

public class MainActivity extends Activity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, new HomeFragment());
        transaction.replace(R.id.fl_bottom, new BottomFragment());
        transaction.commit();
    }
}
