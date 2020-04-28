package com.admin.work.main.more.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.gyf.immersionbar.ImmersionBar;

import java.util.List;


public class MoreListDelegate extends LatteDelegate {
    List<MoreBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list;
    String title;
    private RecyclerView recycler;
    List<Integer> imageList;

    public MoreListDelegate(List<MoreBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list, List<Integer> imageList, String title) {
        this.list = list;
        this.title = title;
        this.imageList = imageList;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_more_lsit;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        ImmersionBar.with(this)
                .titleBar(rootView.findViewById(R.id.delegate_more_list_toolbar))
                .statusBarDarkFont(true)
                .init();

        rootView.findViewById(R.id.delegate_more_list_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });
        AppCompatTextView title = rootView.findViewById(R.id.delegate_more_list_title);
        title.setText(this.title);

        recycler = rootView.findViewById(R.id.delegate_more_list_recycler);
        initRecycler();
    }

    private void initRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        MoreListConverter converter = new MoreListConverter();
        MoreListAdpater adpater = new MoreListAdpater(converter.convert(list,imageList), this);

        recycler.setAdapter(adpater);
    }
}
