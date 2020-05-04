package com.admin.work.main.more;

import android.content.res.AssetManager;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.app.Latte;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.admin.work.main.more.list.MoreBean;
import com.admin.work.main.more.list.MoreListDelegate;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MoreRecyclerAdapter extends MultipleRecyclerAdapter {

    LatteDelegate mDelegate;
    List<MoreBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist;
    int[] images = {R.drawable.m_1, R.drawable.m_2, R.drawable.m_3, R.drawable.m_4,
            R.drawable.m_5, R.drawable.m_6, R.drawable.m_7, R.drawable.m_8,
            R.drawable.m_9, R.drawable.m_10, R.drawable.m_11, R.drawable.m_12,
            R.drawable.m_13, R.drawable.m_14, R.drawable.m_15, R.drawable.m_16};
    List<Integer> imageList = new ArrayList<>();

    public MoreRecyclerAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        this.mDelegate = delegate;
        addItemType(MoreItemType.MORE_TYPE_ONE, R.layout.item_more_type_one);

        String content = parseFile("mudidi.json");
        MoreBean moreBean = new Gson().fromJson(content, MoreBean.class);
        contentlist = moreBean.getShowapi_res_body().getPagebean().getContentlist();
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case MoreItemType.MORE_TYPE_ONE:
                String text = entity.getField(MoreItemFields.TEXT);
                int imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                AppCompatImageView image = holder.getView(R.id.item_more_image);
                AppCompatTextView title = holder.itemView.findViewById(R.id.item_more_name);
                title.setText(text);
                Glide.with(mDelegate.getContext())
                        .load(imageUrl)
                        .into(image);

                int tag = entity.getField(MultipleFields.TAG);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageList.clear();
                        List<MoreBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = new ArrayList<>();
                        if (tag == 0) {
                            addOne();
                            for (int i = 0; i < 4; i++) {
                                list.add(contentlist.get(i));
                            }
                            for (int i = 11; i < 13; i++) {
                                list.add(contentlist.get(i));
                            }
                            for (int i = 4; i > 0; i--) {
                                list.add(contentlist.get(i));
                            }
                            mDelegate.getParentDelegate().getSupportDelegate().start(new MoreListDelegate(list,imageList, text));
                        } else if (tag == 1) {
                            addTwo();
                            for (int i = 10; i < 20; i++) {
                                list.add(contentlist.get(i));
                            }
                            mDelegate.getParentDelegate().getSupportDelegate().start(new MoreListDelegate(list, imageList, text));
                        } else {
                            addThree();
                            for (int i = 4; i >= 0; i--) {
                                list.add(contentlist.get(i));
                            }
                            for (int i = 15; i < 20; i++) {
                                list.add(contentlist.get(i));
                            }
                            mDelegate.getParentDelegate().getSupportDelegate().start(new MoreListDelegate(list, imageList, text));
                        }
                    }
                });
            default:
        }
    }

    void addOne() {
        imageList.add(images[0]);
        imageList.add(images[6]);
        imageList.add(images[7]);
        imageList.add(images[5]);
        imageList.add(images[12]);
        imageList.add(images[15]);
        imageList.add(images[14]);
        imageList.add(images[8]);
        imageList.add(images[9]);
        imageList.add(images[7]);
    }

    void addTwo() {
        imageList.add(images[12]);
        imageList.add(images[6]);
        imageList.add(images[3]);
        imageList.add(images[14]);
        imageList.add(images[12]);
        imageList.add(images[13]);
        imageList.add(images[14]);
        imageList.add(images[15]);
        imageList.add(images[8]);
        imageList.add(images[10]);
    }

    void addThree() {
        imageList.add(images[12]);
        imageList.add(images[2]);
        imageList.add(images[6]);
        imageList.add(images[0]);
        imageList.add(images[13]);
        imageList.add(images[11]);
        imageList.add(images[12]);
        imageList.add(images[13]);
        imageList.add(images[7]);
        imageList.add(images[5]);
    }

    private String parseFile(String fileName) {
        AssetManager assets = Latte.getApplication().getAssets();
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();
        try {
            is = assets.open(fileName);
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {

            }
        }

        return builder.toString();
    }

}
