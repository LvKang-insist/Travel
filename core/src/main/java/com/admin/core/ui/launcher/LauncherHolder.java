package com.admin.core.ui.launcher;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;

import com.admin.core.R;
import com.admin.core.ui.view.ViewHelper;
import com.bigkoo.convenientbanner.holder.Holder;


/**
 * Copyright (C)
 *
 * @file: LauncherHolder
 * @author: 345
 * @Time: 2019/4/21 15:15
 * @description: ${DESCRIPTION}
 */
public class LauncherHolder extends Holder<Integer> {

    private AppCompatImageView mImageView ;
    private boolean isCir = false;

    public LauncherHolder(View itemView) {
        super(itemView);
    }

    void setCir(boolean flag){
        this.isCir = flag;
    }

    /**
     * 加载布局
     */
    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.launcher_image);
    }

    /**
     * 绑定数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void updateUI(Integer data) {
        mImageView.setBackgroundResource(data);
        if (isCir){
            ViewHelper.setViewOutLine(mImageView, 10, ViewHelper.RADIUS_ALL);
        }

    }
}
