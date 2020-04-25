package com.admin.work.main.discover;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.net.rx.RxRequest;
import com.admin.core.ui.view.ForbidSlideViewPager;
import com.admin.core.util.value.Resource;
import com.admin.work.R;
import com.admin.work.R2;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;


/**
 * Copyright (C)
 *
 * @file: DiscoverDelegate
 * @author: 345
 * @Time: 2019/5/4 15:17
 * @description:
 */
public class DiscoverDelegate extends BottomItemDelegate {

//http://www.haoservice.com/docs/50

    AppCompatImageView mToolbar_Icon = null;
    @BindView(R2.id.discover_viewpager)
    ForbidSlideViewPager mViewpager = null;
    @BindView(R2.id.discover_tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.discover_viewpager_banner)
    ViewPager mViewPagerBanner = null;
//    @BindView(R2.id.et_search_view)
//    AppCompatEditText editText;
    private PageBannerAdapter adapter;
    private PagerAdapter pagerAdapter;

    @Override
    public Object setLayout() {
        return R.layout.delegata_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setToolbar();
        setBanner();
        setViewPager();
        setTabLayout();
    }


    private void setTabLayout() {
        //地下 线的颜色
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.app_tab));
        //字的颜色
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        //设置ViewPager
        mTabLayout.setupWithViewPager(mViewpager);
    }

    private void setViewPager() {
        List<String> list = new ArrayList<>();
        list.add("推荐");
        list.add("视频");
        pagerAdapter = new PageAdapter(getChildFragmentManager(), list);
        mViewpager.setAdapter(pagerAdapter);
    }

    private void setBanner() {
        ArrayList<String> list = new ArrayList<>();
        RxRequest.onGetRx(getContext(), Resource.getString(R.string.banner_qq),
                new WeakHashMap<>(), (flag, result) -> {
                    if (flag) {
                        JSONObject json = JSON.parseObject(result);
                        int code = json.getInteger("code");
                        if (code == 200) {
                            JSONArray array = json.getJSONArray("data");
                            for (int i = 0; i < array.size(); i++) {
                                JSONObject ob = array.getJSONObject(i);
                                ob = ob.getJSONObject("pic_info");
                                list.add(ob.getString("url"));
                            }
                            adapter = new PageBannerAdapter(getChildFragmentManager(), list);
                            mViewPagerBanner.setAdapter(adapter);
                            mViewPagerBanner.setPageMargin(30);
                        }
                    }
                });
    }

    private void setToolbar() {

    }
}
