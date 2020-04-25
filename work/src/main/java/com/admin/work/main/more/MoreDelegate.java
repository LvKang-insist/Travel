package com.admin.work.main.more;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.work.R;
import com.admin.work.R2;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MoreDelegate extends BottomItemDelegate {


    @BindView(R2.id.more_recyclerview)
    RecyclerView mRecycler;
    private MoreRecyclerAdapter adapter;

    @BindView(R2.id.more_square_tabLayout)
    MagicIndicator frag_square_tabLayout;

    @BindView(R2.id.more_square_vp)
    ViewPager2 frag_square_vp;

    @Override
    public Object setLayout() {
        return R.layout.delegate_more;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //沉浸式状态栏
//        ImmersionBar.with(this)
////                .statusBarDarkFont(true)
////                .titleBar(rootView.findViewById(R.id.dele_more_layout))
////                .init();
        initRecycler();
    }

    private void initRecycler() {

        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        MoreRecyclerConverter converter = new MoreRecyclerConverter();
        adapter = new MoreRecyclerAdapter(converter.convert(), this);
        mRecycler.setAdapter(adapter);
        mRecycler.setNestedScrollingEnabled(false);

        List<String> list = new ArrayList<>();
        list.add("游记");
        list.add("美图");
        list.add("攻略");
        list.add("建言");
        resultWx(list);
    }

    void resultWx(List<String> data) {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setScrollPivotX(0.5f);  //滚动中心点
        commonNavigator.setEnablePivotScroll(false); //中心点滚动

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                TabTitleView titleView = new TabTitleView(context);
                titleView.setNormalColor(Color.BLACK);
                titleView.setSelectedColor(getContext().getResources().getColor(R.color.icon));
                titleView.setTextSize(18f);
                titleView.setText(data.get(index));
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frag_square_vp.setCurrentItem(index);
                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setLineWidth(dip2px(30f));
                indicator.setLineHeight(dip2px(4f));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.0f));
                indicator.setColors(getContext().getResources().getColor(R.color.icon));
                indicator.setRoundRadius(5f);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                return indicator;
            }
        });


        frag_square_tabLayout.setNavigator(commonNavigator); ;
        frag_square_vp.setAdapter(new SquVpTabAdapter(getChildFragmentManager(),getLifecycle(),data));
        ViewPage2Helpter.bind(frag_square_tabLayout, frag_square_vp);
    }

    int dip2px(Float dpValue) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
