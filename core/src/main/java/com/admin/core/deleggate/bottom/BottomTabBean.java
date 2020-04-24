package com.admin.core.deleggate.bottom;

/**
 * Copyright (C)
 *
 * @file: BottomTabBean
 * @author: 345
 * @Time: 2019/4/25 19:40
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
