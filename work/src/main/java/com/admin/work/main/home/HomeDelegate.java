package com.admin.work.main.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.net.rx.RxRequest;
import com.admin.work.R;
import com.admin.work.R2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.WeakHashMap;

import butterknife.BindView;

/**
 * Copyright (C)
 *
 * @file:
 * @author: 345
 * @Time: 2019/4/26 14:28
 * @description: 主页面
 */
@SuppressWarnings("AlibabaAvoidCommentBehindStatement")
public class HomeDelegate extends BottomItemDelegate {


    @BindView(R2.id.home_recyclerview)
    RecyclerView mRecyclerView;

    private HomeRecyclerViewAdapter adapter;

    @BindView(R2.id.refresh_layout)
     SmartRefreshLayout mRefresh;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        HomeConverter homeConverter = new HomeConverter();

        request(homeConverter);

        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                     request(homeConverter);
            }
        });
    }

    private void request(HomeConverter homeConverter) {
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put("key", "75b91370009876ca424a865e0007d935");
        map.put("num", 15);
        map.put("word", "河北");
        RxRequest.onGetRx(getContext(), "http://api.tianapi.com/travel/index", map, (flag, result) -> {
            if (flag) {
                homeConverter.setJsonData(result);
            }
            adapter = new HomeRecyclerViewAdapter(homeConverter.convert(), this);
            mRecyclerView.setAdapter(adapter);
            mRefresh.finishRefresh(true);
        });
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }
}
