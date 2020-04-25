package com.admin.work.main.home;

import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.launcher.LauncherHolderCreator;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.core.ui.view.ViewHelper;
import com.admin.core.util.callback.IGlobalCallback;
import com.admin.work.R;
import com.admin.work.web.AgentWebActivity;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;

import java.util.List;


public class HomeRecyclerViewAdapter extends MultipleRecyclerAdapter {

    private HomeDelegate mHomeDelegate;
    private IGlobalCallback callBack;

    protected HomeRecyclerViewAdapter(List<MultipleItemEntity> data, LatteDelegate delegate) {
        super(data);
        this.mHomeDelegate = (HomeDelegate) delegate;
        addItemType(HomeItemType.HOME_BANNER, R.layout.item_multiple_banner);
        addItemType(HomeItemType.HOME_LIST, R.layout.item_home_list);
        addItemType(HomeItemType.HOME_LINE, R.layout.item_home_iv_tv);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case HomeItemType.HOME_BANNER:
                List<String> banner = entity.getField(MultipleFields.BANNERS);
                ConvenientBanner mConvenientBanner = holder.itemView.findViewById(R.id.banner_recycler_item);

                mConvenientBanner
                        .setPages(new LauncherHolderCreator(), banner)
                        //设置 下面的 圆圈
                        .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                        //设置 圆圈的 位置
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        //添加 监听
                        .setOnItemClickListener(this)
                        //设置 循环 轮播
                        .setCanLoop(false);
                break;
            case HomeItemType.HOME_LINE:
                LinearLayoutCompat layout1 = holder.itemView.findViewById(R.id.item_lishi_layout);
                LinearLayoutCompat layout2 = holder.itemView.findViewById(R.id.item_mingrne_layout);
                LinearLayoutCompat layout3 = holder.itemView.findViewById(R.id.item_meishi_layout);
                LinearLayoutCompat layout4 = holder.itemView.findViewById(R.id.item_home_feiyi_layout);
                Intent line_intent = new Intent(holder.itemView.getContext(), AgentWebActivity.class);
                layout1.setOnClickListener(v -> {
                    line_intent.putExtra("link", "https://baike.baidu.com/item/%E6%B2%B3%E5%8C%97%E5%8E%86%E5%8F%B2/2417072?fr=aladdin");
                    holder.itemView.getContext().startActivity(line_intent);
                });
                layout2.setOnClickListener(v -> {
                    line_intent.putExtra("link", "http://ren.bytravel.cn/Celebrity/index111.html");
                    holder.itemView.getContext().startActivity(line_intent);
                });
                layout3.setOnClickListener(v -> {
                    line_intent.putExtra("link", "https://baijiahao.baidu.com/s?id=1644897734657156190&wfr=spider&for=pc");
                    holder.itemView.getContext().startActivity(line_intent);
                });
                layout4.setOnClickListener(v -> {
                    line_intent.putExtra("link", "http://www.hebfwzwhyc.cn/");
                    holder.itemView.getContext().startActivity(line_intent);
                });

                break;
            case HomeItemType.HOME_LIST:
                HomeBean.NewslistBean bean = entity.getField(HomeItemFields.BEAN);
                if (bean != null) {
                    AppCompatImageView image = holder.itemView.findViewById(R.id.item_hoem_list_image);
                    AppCompatTextView time = holder.itemView.findViewById(R.id.item_hoem_list_time);
                    AppCompatTextView detail = holder.itemView.findViewById(R.id.item_hoem_list_detail);
                    Glide.with(holder.itemView.getContext())
                            .load(bean.getPicUrl())
                            .into(image);
                    ViewHelper.setViewOutLine(image, 10, ViewHelper.RADIUS_ALL);
                    time.setText(bean.getCtime());
                    detail.setText(bean.getTitle());
                    holder.itemView.setOnClickListener(v -> {
                        Intent intent = new Intent(holder.itemView.getContext(), AgentWebActivity.class);
                        intent.putExtra("link", bean.getUrl());
                        holder.itemView.getContext().startActivity(intent);
                    });
                }
                break;
            default:
        }
    }
}
