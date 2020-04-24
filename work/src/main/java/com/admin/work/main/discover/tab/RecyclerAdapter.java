package com.admin.work.main.discover.tab;

import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.bumptech.glide.Glide;

import java.util.List;




@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class RecyclerAdapter extends MultipleRecyclerAdapter {

    LatteDelegate mTabDelegate;
    JzViewOutlineProvider provider = new JzViewOutlineProvider(20);

    protected RecyclerAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        mTabDelegate = delegate;
        addItemType(DiscoverItemType.DISCOVER_VIDEO, R.layout.item_dis_tab_video);
        addItemType(DiscoverItemType.DISCOVER_RECYCLER, R.layout.item_recycler);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case DiscoverItemType.DISCOVER_VIDEO:

                break;
            case DiscoverItemType.DISCOVER_RECYCLER:
                break;
            default:
        }
    }
}
