package com.admin.work.main.home;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        ENTITLES.clear();

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

        String[] str = {"攻略", "推荐"};
        int[] image = {R.drawable.gonglue, R.drawable.tuijian};
        for (int i = 0; i < 2; i++) {
            MultipleItemEntity list = MultipleItemEntity.builder()
                    .setItemType(HomeItemType.HOME_LIST)
                    .setField(MultipleFields.TAG, true)
                    .setField(MultipleFields.SPAN_SIZE, 1)
                    .setField(MultipleFields.TEXT, str[i])
                    .setField(MultipleFields.IMAGE_URL, image[i])
                    .build();
            ENTITLES.add(list);
        }
        if (json != null) {
            HomeBean bean = new Gson().fromJson(json, HomeBean.class);
            if (bean.getCode() == 200) {
                for (int i = 0; i < bean.getNewslist().size(); i++) {
                    MultipleItemEntity list = MultipleItemEntity.builder()
                            .setItemType(HomeItemType.HOME_LIST)
                            .setField(MultipleFields.TAG, false)
                            .setField(MultipleFields.SPAN_SIZE, 1)
                            .setField(HomeItemFields.BEAN, bean.getNewslist().get(i))
                            .build();
                    ENTITLES.add(list);
                }
            }
        } else {
            MultipleItemEntity list = MultipleItemEntity.builder()
                    .setItemType(HomeItemType.HOME_LIST)
                    .setField(MultipleFields.TAG, false)
                    .setField(MultipleFields.SPAN_SIZE, 1)
                    .setField(HomeItemFields.BEAN, Collections.emptyList())
                    .build();
            ENTITLES.add(list);
        }


        return ENTITLES;
    }
}
