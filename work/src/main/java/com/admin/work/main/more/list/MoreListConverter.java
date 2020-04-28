package com.admin.work.main.more.list;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.main.more.MoreItemFields;
import com.admin.work.main.more.MoreItemType;

import java.util.ArrayList;
import java.util.List;


public class MoreListConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        return ENTITLES;
    }


    public ArrayList<MultipleItemEntity> convert(List<MoreBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list, List<Integer> imageList) {

        for (int i = 0; i < list.size(); i++) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(MoreItemType.MORE_LIST)
                    .setField(MoreItemFields.BEAN, list.get(i))
                    .setField(MultipleFields.IMAGE_URL, imageList.get(i))
                    .build();
            ENTITLES.add(entity);
        }

        return ENTITLES;
    }
}
