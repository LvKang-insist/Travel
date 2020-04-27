package com.admin.work.main.more.list;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.admin.work.main.more.MoreItemFields;
import com.admin.work.main.more.MoreItemType;
import com.hjq.toast.ToastUtils;

import java.util.List;

/**
 * @author 345 QQ:1831712732
 * @name Travel
 * @class name：com.admin.work.main.more.list
 * @time 2020/4/27 22:01
 * @description
 */
public class MoreListAdpater extends MultipleRecyclerAdapter {

    MoreListDelegate delegate;

    protected MoreListAdpater(List<MultipleItemEntity> data, MoreListDelegate delegate) {
        super(data);
        addItemType(MoreItemType.MORE_LIST, R.layout.item_more_list);
        this.delegate = delegate;
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        AppCompatImageView image = holder.getView(R.id.item_more_list_image);
        AppCompatTextView name = holder.getView(R.id.item_more_list_name);
        AppCompatTextView address = holder.getView(R.id.item_more_list_address);
        AppCompatTextView coupon = holder.getView(R.id.item_more_list_coupon);
        AppCompatTextView content = holder.getView(R.id.item_more_list_content);

        image.setBackgroundColor(Color.BLUE);

        MoreBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean data = entity.getField(MoreItemFields.BEAN);
        if (data.getAddress() != null && !data.getAddress().isEmpty()) {
            address.setText(data.getAddress());
        }
        if (data.getName() != null && !data.getName().isEmpty()) {
            name.setText(data.getName());
        }
        if (data.getCoupon() != null && !data.getCoupon().isEmpty()) {
            coupon.setText(data.getCoupon());
        }

        if (data.getSummary() != null && !data.getSummary().isEmpty()) {
            content.setText(data.getSummary());
        } else {
            content.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaoDeMap(data.getLocation().getLat(),data.getLocation().getLon(),data.getName());
            }
        });
    }

    /**
     * 打开高德地图（公交出行，起点位置使用地图当前位置）
     * <p>
     * t = 0（驾车）= 1（公交）= 2（步行）= 3（骑行）= 4（火车）= 5（长途客车）
     *  @param dlat  终点纬度
     * @param dlon  终点经度
     * @param dname 终点名称
     */
    private void openGaoDeMap(String dlat, String dlon, String dname) {
        if (checkMapAppsIsExist(delegate.getContext(), "com.autonavi.minimap")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.autonavi.minimap");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("androidamap://route?sourceApplication=" + R.string.app_name
                    + "&sname=我的位置&dlat=" + dlat
                    + "&dlon=" + dlon
                    + "&dname=" + dname
                    + "&dev=0&m=0&t=1"));
            delegate.startActivity(intent);
        } else {
            ToastUtils.show("高德地图未安装");
        }
    }

    /**
     * 检测地图应用是否安装
     *
     * @param context
     * @param packagename
     * @return
     */
    public boolean checkMapAppsIsExist(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = delegate.getContext().getPackageManager().getPackageInfo(packagename, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }

}
