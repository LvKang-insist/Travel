package com.admin.work.main.home.list;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.core.ui.view.ViewHelper;
import com.admin.work.R;
import com.admin.work.main.home.HomeItemFields;
import com.admin.work.main.home.HomeItemType;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name Travel
 * @class name：com.admin.work.main.home.list
 * @time 2020/4/26 20:11
 * @description
 */
public class ListAdapter extends MultipleRecyclerAdapter {

    ListDelegate delegate;

    protected ListAdapter(List<MultipleItemEntity> data, ListDelegate delegate) {
        super(data);
        this.delegate = delegate;
        addItemType(HomeItemType.HOME_LIST_ADAPTER, R.layout.delegate_home_list_item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        if (holder.getItemViewType() == HomeItemType.HOME_LIST_ADAPTER) {
            ListBean.DataBean field = entity.getField(HomeItemFields.BEAN);
            int imageUrl = entity.getField(MultipleFields.IMAGE_URL);
            AppCompatImageView image = holder.getView(R.id.delegate_list_image);
            AppCompatTextView name = holder.getView(R.id.delegate_list_name);
            AppCompatTextView detail = holder.getView(R.id.delegate_list_detail);
            ViewHelper.setViewOutLine(image, 10, ViewHelper.RADIUS_ALL);
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(image);
            name.setText(field.getName());
            detail.setText(field.getDetail());

            holder.getView(R.id.delegate_list_layout).setOnClickListener(v -> delegate.getSupportDelegate().start(new ListContentDelegate(field.getName(), field.getStr())));
        }

    }
}
