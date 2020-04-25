package com.admin.work.main.home.search;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.app.Latte;
import com.admin.core.net.rx.RxRequest;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.admin.work.main.home.HomeBean;
import com.admin.work.main.home.HomeItemFields;
import com.admin.work.main.home.HomeItemType;
import com.admin.work.web.AgentWebActivity;
import com.bumptech.glide.Glide;
import com.elvishew.xlog.XLog;

import java.util.List;
import java.util.WeakHashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends MultipleRecyclerAdapter {
    private SearchDelegate delegate;

    protected SearchAdapter(List<MultipleItemEntity> data, SearchDelegate searchDelegate) {
        super(data);
        this.delegate = searchDelegate;
        addItemType(HomeItemType.HOME_SEARCH, R.layout.item_search);
        addItemType(HomeItemType.HOME_NETWORK_SEARCH, R.layout.item_network_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case HomeItemType.HOME_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                tvSearchItem.setOnClickListener((view -> {
                    request(tvSearchItem.getText().toString());
                }));
                break;
            case HomeItemType.HOME_NETWORK_SEARCH:
                final HomeBean.NewslistBean bean = entity.getField(HomeItemFields.BEAN);
                CircleImageView image = holder.getView(R.id.item_search_image);
                Glide.with(Latte.getApplication())
                        .load(bean.getPicUrl())
                        .into(image);
                AppCompatTextView text = holder.getView(R.id.item_search_name);
                text.setText(bean.getTitle());
                holder.getView(R.id.item_search_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(holder.itemView.getContext(), AgentWebActivity.class);
                        intent.putExtra("link", bean.getUrl());
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }

    void request(String text) {
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put("key", "75b91370009876ca424a865e0007d935");
        map.put("num", 15);
        map.put("word", text);
        delegate.saveItem(text);
        RxRequest.onGetRx(delegate.getContext(), "http://api.tianapi.com/travel/index", map, (flag, result) -> {
            if (flag) {
                XLog.json(result);
                SearchDataConverter converter = new SearchDataConverter();
                converter.setJsonData(result);
                setNewData(converter.convert(SearchDataConverter.MODE.SEARCH_SONG));
            }
        });
    }
}