package com.admin.core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Copyright (C)
 *
 * @file: LatteViewFinderView
 * @description: ${DESCRIPTION}
 */
public class LatteViewFinderView extends ViewFinderView {
    public LatteViewFinderView(Context context) {
        this(context,null);
    }

    public LatteViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        //让扫描框 变成正方形
        mSquareViewFinder = true;
        //色湖之扫描框的颜色
        mBorderPaint.setColor(Color.RED);
        mLaserPaint.setColor(Color.RED);
    }
}
