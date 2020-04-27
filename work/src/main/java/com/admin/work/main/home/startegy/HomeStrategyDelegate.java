package com.admin.work.main.home.startegy;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.gyf.immersionbar.ImmersionBar;

public class HomeStrategyDelegate extends LatteDelegate {

    private RecyclerView recycler;

    String title;
    String json;

    public HomeStrategyDelegate(String title, String json) {
        this.title = title;
        this.json = json;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_startedgy;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(rootView.findViewById(R.id.delegate_home_staregy))
                .init();

        AppCompatImageView back = rootView.findViewById(R.id.delegate_staregy_back);
        back.setOnClickListener(v -> getSupportDelegate().pop());
        AppCompatTextView title = rootView.findViewById(R.id.delegate_staregy_title);

        title.setText(this.title);

        recycler = rootView.findViewById(R.id.delegate_staregy_recycler);

        initRecycler();
    }

    private void initRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        StartegyConverter converter = new StartegyConverter();
        converter.setJsonData(json);
        StartegyAdapter adapter = new StartegyAdapter(converter.convert(), this);

        recycler.setAdapter(adapter);
    }
}
