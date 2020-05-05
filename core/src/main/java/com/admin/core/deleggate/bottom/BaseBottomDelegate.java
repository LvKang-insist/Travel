package com.admin.core.deleggate.bottom;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.admin.core.R;
import com.admin.core.R2;
import com.admin.core.deleggate.LatteDelegate;
import com.elvishew.xlog.XLog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Copyright (C)
 *
 * @file: BaseBottomDelegate
 * @description: 对所有的键值对进行管理，也就是 碎片和tab，这是一个抽象类。
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    /**
     * 存储所有的子 Fragment
     */
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    /**
     * 存储所有的子 TabBean
     */
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

    /**
     * 存储 Fragment和TabBean 的映射
     */
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**
     * 当前Fragment 的位置
     */
    private int mCurrentDelegate = 0;
    /**
     * 进入程序展示 的Fragment
     */
    private int mIndexDelegate = 0;
    /**
     * Tab 的颜色
     */
    private int mClickedColor = Color.RED;

    LinearLayoutCompat mBottomBar;
    /**
     * Bottom 的标志
     */
    boolean isBottomAnim = false;
    /**
     * 线
     */
    @BindView(R2.id.bottom_view)
    View mView = null;

    private int[] iconTrue = {R.drawable.icon_home_true, R.drawable.icon_mdd_true, R.drawable.icon_service_true, R.drawable.icon_my_true};
    private int[] iconfalse = {R.drawable.icon_home_false, R.drawable.icon_mdd_false, R.drawable.icon_service_false, R.drawable.icon_my_false};


    /**
     * @param builder 要添加的 映射
     * @return 返回值为 LinkedHashMap
     */
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract int setIndexDelegate();

    /**
     * 该注解表示 这必须是一个颜色
     */
    @ColorInt
    public abstract int setClickedColor();

    public abstract void onPlayer(LinearLayout mLinearLayout);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        //拿到工厂类的实例
        final ItemBuilder builder = ItemBuilder.builder();
        //获取 添加完成的键值对
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        //将 键值对 保存在ITEMS 中
        ITEMS.putAll(items);
        //拿到键和值
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        mBottomBar = rootView.findViewById(R.id.bottom_bar);
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            //第一个参数 布局，第二个参数 为给第一个参数加载的布局 设置一个父布局
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            //返回指定的视图
            XLog.e(mBottomBar.toString());
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个 item的点击事件 和标记
            item.setTag(i);
            item.setOnClickListener(this);
            //拿到 item 的第一个和 第二个子布局
            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);

            //获取 集合中对应的 Tab
            final BottomTabBean bean = TAB_BEANS.get(i);

            //初始化 tab 数据
            itemIcon.setImageResource(iconfalse[i]);
            itemTitle.setText(bean.getTitle());
            //判断是否是 当前显示
            if (i == mIndexDelegate) {
                itemIcon.setImageResource(iconTrue[i]);
                itemTitle.setTextColor(mClickedColor);
            }
        }
        //返回一个数组，里边是fragment的。注意fragment 是继承 supportFragment 的，所以这里的集合是这个类型
        //fragmentation 需要我们这样做
        final ISupportFragment[] delegateArry = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        //加载多个同级根fragment,位于当前fragment中(也就是说加载的fragment是子fragment)，
        // 并显示其中一个，第二个参数为要显示的fragment
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArry);
        mCurrentDelegate = mIndexDelegate;


    }

    /**
     * 重置所有颜色
     */
    private void resetColor() {
        //拿到 底部tab的子布局的size
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            itemIcon.setImageResource(iconfalse[i]);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
        itemIcon.setImageResource(iconTrue[tag]);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);

        //第一次参数 为要显示的，第二个则是要隐藏的
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        // 记住当前显示 的下标，注意顺序
        mCurrentDelegate = tag;
    }


}
