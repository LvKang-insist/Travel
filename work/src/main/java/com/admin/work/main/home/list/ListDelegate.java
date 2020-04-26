package com.admin.work.main.home.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name Travel
 * @class name：com.admin.work.main.home
 * @time 2020/4/26 20:00
 * @description
 */
public class ListDelegate extends LatteDelegate {

    private RecyclerView recycler;
    String json;
    int[] image;

    public ListDelegate(String json, int[] image) {
        this.json = json;
        this.image = image;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(rootView.findViewById(R.id.delegate_home_list_conetent))
                .init();
        recycler = rootView.findViewById(R.id.delegate_home_list_recycler);

        AppCompatTextView title = rootView.findViewById(R.id.delegate_hoem_list_title);
        AppCompatImageView back = rootView.findViewById(R.id.delegate_home_list_back);
        back.setOnClickListener(v -> getSupportDelegate().pop());

        initRecycler();
        ListBean listBean = new Gson().fromJson(json, ListBean.class);
        title.setText(listBean.getTile());
    }

    private void initRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ListConverter converter = new ListConverter(image);
        converter.setJsonData(json);
        ListAdapter adapter = new ListAdapter(converter.convert(),this);
        recycler.setAdapter(adapter);
    }

}
