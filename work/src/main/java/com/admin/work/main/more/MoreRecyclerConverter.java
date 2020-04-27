package com.admin.work.main.more;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.work.R;

import java.util.ArrayList;
import java.util.List;


public class MoreRecyclerConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        List<Integer> image = new ArrayList<>();
        image.add(R.drawable.mdd_shanshui);
        image.add(R.drawable.mdd_xiandai);
        image.add(R.drawable.mdd_wenhua);


        String[] title = {"山水之乐", "现代气息", "文化之韵"};

        for (int i = 0; i < title.length; i++) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(MoreItemType.MORE_TYPE_ONE)
                    .setField(MultipleFields.IMAGE_URL, image.get(i))
                    .setField(MultipleFields.TAG, i)
                    .setField(MoreItemFields.TEXT, title[i])
                    .build();
            ENTITLES.add(entity);
        }
        return ENTITLES;
    }

}
