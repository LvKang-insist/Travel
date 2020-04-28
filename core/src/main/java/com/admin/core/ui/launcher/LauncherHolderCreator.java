package com.admin.core.ui.launcher;

import android.view.View;

import com.admin.core.R;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;


/**
 * Copyright (C)
 *
 * @file: LauncherHolderCreator
 * @description: ${DESCRIPTION}
 */
public class LauncherHolderCreator implements CBViewHolderCreator {

    boolean isCir = false;

    public LauncherHolderCreator() {
    }

    /**
     * 是否开启圆角
     *
     * @param isCir
     */
    public  LauncherHolderCreator(boolean isCir) {
        this.isCir = isCir;
    }

    /**
     * 加载 布局
     */
    @Override
    public Holder createHolder(View itemView) {
        LauncherHolder holder = new LauncherHolder(itemView);
        holder.setCir(isCir);
        return holder;
    }

    @Override
    public int getLayoutId() {
        //设置 加载的布局
        return R.layout.launcher_icon;
    }
}
