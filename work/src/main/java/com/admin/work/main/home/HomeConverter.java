package com.admin.work.main.home;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        List<Integer> banners = new ArrayList<>();
        banners.add(R.drawable.banner_1);
        banners.add(R.drawable.banner_2);
        banners.add(R.drawable.banner_3);
        banners.add(R.drawable.banner_4);
        banners.add(R.drawable.banner_5);

        MultipleItemEntity banner = MultipleItemEntity.builder()
                .setItemType(HomeItemType.HOME_BANNER)
                .setField(MultipleFields.SPAN_SIZE, 2)
                .setField(MultipleFields.BANNERS, banners)
                .build();

        ENTITLES.add(banner);


        MultipleItemEntity HOME_LINE = MultipleItemEntity.builder()
                .setItemType(HomeItemType.HOME_LINE)
                .setField(MultipleFields.SPAN_SIZE, 2)
                .build();
        ENTITLES.add(HOME_LINE);


        String json = getJsonData();
        HomeBean bean = new Gson().fromJson(json, HomeBean.class);
        if (bean.getCode() == 200) {
            for (int i = 0; i < bean.getNewslist().size(); i++) {
                MultipleItemEntity list = MultipleItemEntity.builder()
                        .setItemType(HomeItemType.HOME_LIST)
                        .setField(MultipleFields.SPAN_SIZE, 1)
                        .setField(HomeItemFields.BEAN, bean.getNewslist().get(i))
                        .build();
                ENTITLES.add(list);
            }
        }


        return ENTITLES;
    }
}
