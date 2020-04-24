package com.admin.work.main.home;

import android.net.MacAddress;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.admin.core.app.AccountManager;
import com.admin.core.app.Latte;
import com.admin.core.app.MusicManager;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.rx.RxRequest;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.core.util.callback.CallBackType;
import com.admin.core.util.callback.CallbackManager;
import com.admin.core.util.callback.IGlobalCallback;
import com.admin.core.util.value.Resource;
import com.admin.work.R;

import com.admin.work.main.home.tab.TabLayoutDelegate;



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class HomeRecyclerViewAdapter extends MultipleRecyclerAdapter {

    private HomeDelegate mHomeDelegate;
    private IGlobalCallback callBack;

    protected HomeRecyclerViewAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        this.mHomeDelegate = (HomeDelegate) delegate;
        addItemType(HomeItemType.HOME_ACCOUNT, R.layout.item_home_account);
        addItemType(HomeItemType.HOME_ICON, R.layout.item_home_icon);
        addItemType(HomeItemType.HOME_SORT, R.layout.item_home_sort);
        addItemType(HomeItemType.HOME_TABLAYOUT, R.layout.item_home_tab_delegate);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case HomeItemType.HOME_ACCOUNT:

                break;
            case HomeItemType.HOME_ICON:
                break;
            case HomeItemType.HOME_SORT:
//
                break;
            case HomeItemType.HOME_TABLAYOUT:
                mHomeDelegate.getSupportDelegate()
                        .loadRootFragment(R.id.item_home_tab_delegate, TabLayoutDelegate.create());
                break;
            default:
        }
    }
}
