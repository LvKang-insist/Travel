package com.admin.work.main.home.startegy;

import android.os.Build;
import android.view.LayoutInflater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.admin.work.main.home.HomeItemFields;
import com.admin.work.main.home.HomeItemType;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name Travel
 * @class name：com.admin.work.main.home.list
 * @time 2020/4/26 20:11
 * @description
 */
public class StartegyAdapter extends MultipleRecyclerAdapter {

    HomeStrategyDelegate delegate;

    LayoutInflater inflater;

    protected StartegyAdapter(List<MultipleItemEntity> data, HomeStrategyDelegate delegate) {
        super(data);
        this.delegate = delegate;
        addItemType(HomeItemType.HOME_STARTEGY, R.layout.item_home_startegy);
        inflater = LayoutInflater.from(delegate.getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        LinearLayoutCompat layout = holder.getView(R.id.item_home_startegy_layout);
        layout.removeAllViews();
        AppCompatTextView title = (AppCompatTextView) inflater.inflate(R.layout.item_home_startegy_title, layout, false);
        layout.addView(title);
        if (holder.getItemViewType() == HomeItemType.HOME_STARTEGY) {
            StartegyBean.DataBean bean = entity.getField(HomeItemFields.BEAN);
            title.setText(bean.getTitle());
            for (int i = 0; i < bean.getDetail().size(); i++) {
                List<StartegyBean.DataBean.DetailBean> detail = bean.getDetail();
                LinearLayoutCompat view = (LinearLayoutCompat) inflater.inflate(R.layout.item_day, layout, false);
                AppCompatTextView day = view.findViewById(R.id.item_home_startegy_name);
                AppCompatTextView content = view.findViewById(R.id.item_home_startegy_detail);
                day.setText(detail.get(i).getName());
                content.setText(detail.get(i).getData());
                layout.addView(view);
            }
        }

    }
}
