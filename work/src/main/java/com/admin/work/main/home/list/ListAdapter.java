package com.admin.work.main.home.list;

import android.content.SharedPreferences;

import com.admin.work.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.PER_ITEM:

                break;
            case ListItemType.SWITCH_ITEM:

                break;
            default:
                break;
        }
    }
}