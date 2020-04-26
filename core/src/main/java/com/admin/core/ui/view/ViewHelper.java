package com.admin.core.ui.view;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.RequiresApi;

import com.admin.core.R;

public class ViewHelper {


    public static final int RADIUS_ALL = 0;
    public static final int  RADIUS_LEFT = 1;
    public static final int RADIUS_TOP = 2;
    public static final int RADIUS_RIGHT = 3;
    public static final int RADIUS_BOTTOM = 4;


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("Recycle")
    public static void setViewOutLine(
            View view, AttributeSet attributeSet, int defStyleAttr, int defStyleRes) {
        TypedArray array = view.getContext().obtainStyledAttributes(
                attributeSet, R.styleable.ViewOutLineStrategy, defStyleAttr, defStyleRes
        );
        int radius =
                array.getDimensionPixelOffset(R.styleable.ViewOutLineStrategy_radius, 0);
        int radiusSide =
                array.getIndex(R.styleable.ViewOutLineStrategy_radiusSide);
        array.recycle();

        setViewOutLine(view, radius, radiusSide);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setViewOutLine(View view, int radius, int radiusSide) {
        if (radius <= 0) {
            return;
        }
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int width = view.getWidth();
                int height = view.getHeight();
                if (width <= 0 || height <= 0) return;
                if (radiusSide != RADIUS_ALL) {
                    int left = 0;
                    int right = width;
                    int top = 0;
                    int bottom = height;
                    switch (radiusSide) {
                        case RADIUS_LEFT:
                            right += radius;
                            break;
                        case RADIUS_TOP:
                            bottom += radius;
                            break;
                        case RADIUS_RIGHT:
                            left -= radius;
                            break;
                        case RADIUS_BOTTOM:
                            top -= radius;
                            break;
                        default:
                    }
                    outline.setRoundRect(left, top, right, bottom, radius);
                } else {
                    if (radius > 0) {
                        outline.setRoundRect(0, 0, width, height, radius);
                    }
                }
            }
        });
        view.setClipToOutline(radius > 0);
        view.invalidate();
    }

}
