package com.domineer.triplebro.newsreader.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.NewsListActivity;
import com.domineer.triplebro.newsreader.adapters.TypeAdapter;
import com.domineer.triplebro.newsreader.controllers.TypeController;
import com.domineer.triplebro.newsreader.interfaces.OnItemClickListener;
import com.domineer.triplebro.newsreader.models.TypeInfo;

import java.util.List;

public class TypeFragment extends Fragment implements OnItemClickListener {

    private View fragment_type;
    private RecyclerView rv_type;
    private TypeController typeController;
    private List<TypeInfo> typeInfoList;
    private TypeAdapter typeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_type = inflater.inflate(R.layout.fragment_type, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_type;
    }

    private void initView() {
        rv_type = (RecyclerView) fragment_type.findViewById(R.id.rv_type);
    }

    private void initData() {
        typeController = new TypeController(getActivity());
        typeInfoList = typeController.findTypeInfoList();
        rv_type.setLayoutManager(new GridLayoutManager(getActivity(),3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        typeAdapter = new TypeAdapter(getActivity(),typeInfoList);
        rv_type.setAdapter(typeAdapter);
    }

    private void setOnClickListener() {

        typeAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent newsList = new Intent(getActivity(), NewsListActivity.class);
        newsList.putExtra("typeInfo",typeInfoList.get(position));
        getActivity().startActivity(newsList);
    }

    @Override
    public void onItemLongClick(View view) {

    }
}
