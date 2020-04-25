package com.admin.work.main.my.list;

import com.admin.work.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.PER_ITEM, R.layout.arraw_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        if (helper.getItemViewType() == ListItemType.PER_ITEM) {
            helper.setText(R.id.tv_arrow_text, item.getText());
            helper.setText(R.id.tv_arrow_value, item.getValue());
        }
    }
}