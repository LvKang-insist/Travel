package com.admin.work.main.home.search;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.util.storage.LattePreference;
import com.admin.work.main.home.HomeBean;
import com.admin.work.main.home.HomeItemFields;
import com.admin.work.main.home.HomeItemType;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class SearchDataConverter extends DataConverter {

    enum MODE {
        /**
         * Type
         */
        SEARCH_HISTORY,
        SEARCH_SONG
    }

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        //获取历史记录
        final String jsonStr = LattePreference.getAppData(TAG_SEARCH_HISTORY);
        if (!"".equals(jsonStr) && jsonStr != null) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String hostoryItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(HomeItemType.HOME_SEARCH)
                        .setField(MultipleFields.TEXT, hostoryItemText)
                        .build();
                ENTITLES.add(entity);
            }
        }
        return ENTITLES;
    }

    public ArrayList<MultipleItemEntity> convert(MODE mode) {
        switch (mode) {
            case SEARCH_HISTORY:
                //获取历史记录
                final String jsonStr = LattePreference.getAppData(TAG_SEARCH_HISTORY);
                if (!"".equals(jsonStr) && jsonStr != null) {
                    final JSONArray array = JSONArray.parseArray(jsonStr);
                    final int size = array.size();
                    for (int i = 0; i < size; i++) {
                        final String hostoryItemText = array.getString(i);
                        final MultipleItemEntity entity = MultipleItemEntity.builder()
                                .setItemType(HomeItemType.HOME_SEARCH)
                                .setField(MultipleFields.TEXT, hostoryItemText)
                                .build();
                        ENTITLES.add(entity);
                    }
                }
                return ENTITLES;
            case SEARCH_SONG:
                return parseData(HomeItemType.HOME_NETWORK_SEARCH);
            default:
        }
        return null;
    }

    private ArrayList<MultipleItemEntity> parseData(int type) {
        String json = getJsonData();
        if (json != null) {
            HomeBean bean = new Gson().fromJson(json, HomeBean.class);
            if (bean.getCode() == 200) {
                for (int i = 0; i < bean.getNewslist().size(); i++) {
                    MultipleItemEntity list = MultipleItemEntity.builder()
                            .setItemType(type)
                            .setField(HomeItemFields.BEAN, bean.getNewslist().get(i))
                            .build();
                    ENTITLES.add(list);
                }
            }
        } else {
            MultipleItemEntity list = MultipleItemEntity.builder()
                    .setItemType(type)
                    .setField(HomeItemFields.BEAN, Collections.emptyList())
                    .build();
            ENTITLES.add(list);
        }
        return ENTITLES;
    }

}
