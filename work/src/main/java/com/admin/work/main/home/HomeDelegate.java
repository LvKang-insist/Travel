package com.admin.work.main.home;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.work.main.home.setting.SettingDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import butterknife.BindView;
import butterknife.OnClick;

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


    @BindView(R2.id.toolbar_textview)
    AppCompatTextView mToolbar_Text;
    @BindView(R2.id.toolbar_icon)
    AppCompatImageView mToolbar_Icon;
    @BindView(R2.id.home_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R2.id.et_search_view)
    AppCompatEditText editText;
    private HomeRecyclerViewAdapter adapter;


    @OnClick(R2.id.toolbar_icon)
    void onSettingClick() {
        getParentDelegate().getSupportDelegate().start(new SettingDelegate());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }
}
