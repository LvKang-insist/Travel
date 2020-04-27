package com.admin.work.main.home;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.admin.core.app.Latte;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.launcher.LauncherHolderCreator;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.core.ui.view.ViewHelper;
import com.admin.core.util.callback.IGlobalCallback;
import com.admin.work.R;
import com.admin.work.main.home.list.ListDelegate;
import com.admin.work.main.home.startegy.HomeStrategyDelegate;
import com.admin.work.web.AgentWebActivity;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                mConvenientBanner.startTurning(3000);
                break;
            case HomeItemType.HOME_LINE:
                LinearLayoutCompat layout1 = holder.itemView.findViewById(R.id.item_lishi_layout);
                LinearLayoutCompat layout2 = holder.itemView.findViewById(R.id.item_mingrne_layout);
                LinearLayoutCompat layout3 = holder.itemView.findViewById(R.id.item_meishi_layout);
                LinearLayoutCompat layout4 = holder.itemView.findViewById(R.id.item_home_feiyi_layout);
                layout1.setOnClickListener(v -> {

                    int[] image = {R.drawable.lishi_baoding, R.drawable.lishi_cangzhou, R.drawable.lishi_chengde,
                            R.drawable.lishi_handan, R.drawable.lishi_hengshui, R.drawable.lishi_lagnfang,
                            R.drawable.lishi_qignhaugndao, R.drawable.lishi_shijiazhuagn, R.drawable.lishi_tangshan,
                            R.drawable.lishi_xingtai, R.drawable.lishi_zhangjiakou};
                    String content = parseFile("home_lishi.json");
                    mHomeDelegate.getParentDelegate().getSupportDelegate().start(new ListDelegate(content, image));
                });
                layout2.setOnClickListener(v -> {
                    int[] image = {R.drawable.mingren_bianque, R.drawable.mingren_lidazhao, R.drawable.mingren_lianpo,
                            R.drawable.mingren_weizheng, R.drawable.mingren_zhaoyun, R.drawable.mingren_zhuchongzhi};
                    String content = parseFile("home_mingren.json");
                    mHomeDelegate.getParentDelegate().getSupportDelegate().start(new ListDelegate(content, image));
                });
                layout3.setOnClickListener(v -> {
                    int[] image = {R.drawable.meishi_chengde, R.drawable.meishi_guotie, R.drawable.meishi_huoguoji,
                            R.drawable.meishi_jingfen, R.drawable.meishi_jingsi, R.drawable.meishi_lvrou,
                            R.drawable.meishi_qizi, R.drawable.meishi_qinghuagndao, R.drawable.meishi_hexiang,
                            R.drawable.lishi_zhangjiakou};
                    String content = parseFile("home_meishi.json");
                    mHomeDelegate.getParentDelegate().getSupportDelegate().start(new ListDelegate(content, image));
                });
                layout4.setOnClickListener(v -> {
                    int[] image = {R.drawable.feiyi_boyan, R.drawable.feiyi_chengde, R.drawable.feiyi_hebeib,
                            R.drawable.feiyi_hebeig, R.drawable.feiyi_hebeiheng, R.drawable.feyi_jianzhi,
                            R.drawable.feiyi_mengcui, R.drawable.feiyi_zhili};
                    String content = parseFile("home_feiyi.json");
                    mHomeDelegate.getParentDelegate().getSupportDelegate().start(new ListDelegate(content, image));
                });

                break;
            case HomeItemType.HOME_LIST:
                AppCompatImageView image = holder.itemView.findViewById(R.id.item_hoem_list_image);
                AppCompatTextView time = holder.itemView.findViewById(R.id.item_hoem_list_time);
                AppCompatTextView detail = holder.itemView.findViewById(R.id.item_hoem_list_detail);

                boolean tag = entity.getField(MultipleFields.TAG);
                if (tag) {
                    time.setVisibility(View.GONE);
                    String text = entity.getField(MultipleFields.TEXT);
                    int iamgeId = entity.getField(MultipleFields.IMAGE_URL);
                    String json = null;
                    if (text.equals("攻略")) {
                        json = parseFile("home_gonglue.json");
                    }
                    detail.setText(text);
                    Glide.with(mHomeDelegate.getContext())
                            .load(iamgeId)
                            .into(image);
                    String finalJson = json;
                    holder.itemView.setOnClickListener(v -> mHomeDelegate.getParentDelegate().getSupportDelegate().start(new HomeStrategyDelegate(text, finalJson)));
                    return;
                }
                HomeBean.NewslistBean bean = entity.getField(HomeItemFields.BEAN);
                if (bean != null) {
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
