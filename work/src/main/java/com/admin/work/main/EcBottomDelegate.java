package com.admin.work.main;

import android.graphics.Color;
import android.os.Build;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.admin.core.deleggate.bottom.BaseBottomDelegate;
import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.deleggate.bottom.BottomTabBean;
import com.admin.core.deleggate.bottom.ItemBuilder;
import com.admin.work.main.home.HomeDelegate;
import com.admin.work.main.more.MoreDelegate;
import com.admin.work.main.my.MyDelegate;
import com.admin.work.main.service.DiscoverDelegate;

import java.util.LinkedHashMap;


@SuppressWarnings("MapOrSetKeyShouldOverrideHashCodeEquals")
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("主页"), new HomeDelegate());
        items.put(new BottomTabBean("目的地"), new MoreDelegate());
        items.put(new BottomTabBean("服务"), new DiscoverDelegate());
        items.put(new BottomTabBean("我的"), new MyDelegate());
        return builder.addItem(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int setClickedColor() {
        return Color.parseColor("#000000");
    }

    @Override
    public void onPlayer(LinearLayout mLinearLayout) {
    }

}
