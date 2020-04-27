package com.admin.work.main.home.startegy;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.main.home.HomeItemFields;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.admin.work.main.home.HomeItemType.HOME_STARTEGY;

public class StartegyConverter extends DataConverter {


    @Override
    public ArrayList<MultipleItemEntity> convert() {


        StartegyBean bean = new Gson().fromJson(getJsonData(), StartegyBean.class);

        for (int i = 0; i < bean.getData().size(); i++) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(HOME_STARTEGY)
                    .setField(HomeItemFields.BEAN, bean.getData().get(i))
                    .build();
            ENTITLES.add(entity);
        }

        return ENTITLES;
    }
}
