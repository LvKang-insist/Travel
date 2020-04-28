package com.admin.work.main.my.setting;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.my.list.ListAdapter;
import com.admin.work.main.my.list.ListBean;
import com.admin.work.main.my.list.ListItemType;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SettingDelegate extends LatteDelegate {


    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    private ListAdapter adapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_setting;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(rootView.findViewById(R.id.setting_toolbar))
                .init();

        rootView.findViewById(R.id.delegate_home_list_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setting();
    }

    private void setting() {

        ListBean forget = new ListBean.Builder()
                .setmItemType(ListItemType.PER_ITEM)
                .setmId(5)
                .setmText("修改密码")
                .build();

        ListBean about = new ListBean.Builder()
                .setmItemType(ListItemType.PER_ITEM)
                .setmId(4)
                .setmText("退出登录")
                .build();


        List<ListBean> list = new ArrayList<>();
        list.add(forget);
        list.add(about);

        adapter = new ListAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingOnclickListener(getContext(), this));
    }

    @Override
    public boolean onBackPressedSupport() {
        getSupportDelegate().pop();
        return true;
    }
}
