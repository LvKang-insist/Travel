package com.admin.work.main.more;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.home.search.SearchDelegate;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;

public class MoreDelegate extends BottomItemDelegate {


    @BindView(R2.id.more_recyclerview)
    RecyclerView mRecycler;
    MoreRecyclerAdapter adapter;


    @Override
    public Object setLayout() {
        return R.layout.delegate_more;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //沉浸式状态栏
        initRecycler();

        rootView.findViewById(R.id.activity_search_toolbar).setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new SearchDelegate()));
        rootView.findViewById(R.id.activity_search_edit).setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new SearchDelegate()));
    }

    private void initRecycler() {

        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        MoreRecyclerConverter converter = new MoreRecyclerConverter();
        adapter = new MoreRecyclerAdapter(converter.convert(), this);
        mRecycler.setAdapter(adapter);
    }
}
