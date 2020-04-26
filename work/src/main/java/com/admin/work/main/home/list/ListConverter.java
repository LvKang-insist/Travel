package com.admin.work.main.home.list;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.R;
import com.admin.work.main.home.HomeBean;
import com.admin.work.main.home.HomeItemFields;
import com.admin.work.main.home.HomeItemType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListConverter extends DataConverter {

    int[] image;

    ListConverter(int[] image) {
        this.image = image;
    }

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        String json = getJsonData();

        ListBean listBean = new Gson().fromJson(json, ListBean.class);

        if (listBean.getData().size() == image.length) {
            for (int i = 0; i < image.length; i++) {
                MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(HomeItemType.HOME_LIST_ADAPTER)
                        .setField(HomeItemFields.BEAN, listBean.getData().get(i))
                        .setField(MultipleFields.IMAGE_URL, image[i])
                        .build();
                ENTITLES.add(entity);
            }
        }

        return ENTITLES;
    }
}
