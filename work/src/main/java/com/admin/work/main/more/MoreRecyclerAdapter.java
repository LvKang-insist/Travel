package com.admin.work.main.more;

import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.launcher.LauncherHolderCreator;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.bigkoo.convenientbanner.ConvenientBanner;

import java.util.List;

public class MoreRecyclerAdapter extends MultipleRecyclerAdapter {

    LatteDelegate mDelegate;

    protected MoreRecyclerAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        this.mDelegate = delegate;
        addItemType(MoreItemType.MORE_TYPE_ONE, R.layout.item_more_type_one);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case MoreItemType.MORE_TYPE_ONE:
                List<String> banner = entity.getField(MoreItemFields.BANNER);
                ConvenientBanner mConvenientBanner = holder.itemView.findViewById(R.id.banner_recycler_item);
                mConvenientBanner
                        .setPages(new LauncherHolderCreator(true), banner)
                        //添加 监听
                        .setOnItemClickListener(this)
                        //设置 循环 轮播
                        .setCanLoop(false);

                String text = entity.getField(MoreItemFields.TEXT);
                AppCompatTextView title = holder.itemView.findViewById(R.id.item_more_name);
                title.setText(text);
            default:
        }
    }

}
