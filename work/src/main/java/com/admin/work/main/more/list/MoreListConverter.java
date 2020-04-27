package com.admin.work.main.more.list;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.main.more.MoreItemFields;
import com.admin.work.main.more.MoreItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author 345 QQ:1831712732
 * @name Travel
 * @class name：com.admin.work.main.more.list
 * @time 2020/4/27 22:01
 * @description
 */
public class MoreListConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        return ENTITLES;
    }


    public ArrayList<MultipleItemEntity> convert(List<MoreBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {

        for (int i = 0; i < list.size(); i++) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(MoreItemType.MORE_LIST)
                    .setField(MoreItemFields.BEAN, list.get(i))
                    .build();
            ENTITLES.add(entity);
        }

        return ENTITLES;
    }
}
