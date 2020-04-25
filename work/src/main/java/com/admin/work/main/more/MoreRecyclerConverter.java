package com.admin.work.main.more;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.R;

import java.util.ArrayList;
import java.util.List;

public class MoreRecyclerConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        List<Integer> banners = new ArrayList<>();
        banners.add(R.drawable.banner_1);
        banners.add(R.drawable.banner_2);
        banners.add(R.drawable.banner_3);
        banners.add(R.drawable.banner_4);
        banners.add(R.drawable.banner_5);

        String[] title = {"热门推荐", "热门目的地", "专题推荐", "最新活动", "精选路线"};

        for (String s : title) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(MoreItemType.MORE_TYPE_ONE)
                    .setField(MoreItemFields.TEXT, s)
                    .setField(MoreItemFields.BANNER, banners)
                    .build();
            ENTITLES.add(entity);
        }
        return ENTITLES;
    }

}
