package com.admin.core.deleggate.bottom;

/**
 * Copyright (C)
 *
 * @file: BottomTabBean
 * @description: 导航栏 的Tab
 */
public final class BottomTabBean {

    /**
     * 文字
     */
    private final CharSequence TITLE;

    public BottomTabBean( CharSequence TITLE) {

        this.TITLE = TITLE;
    }



    public CharSequence getTitle() {
        return TITLE;
    }
}
