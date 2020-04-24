package com.admin.work.main.MyDelegate;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.home.setting.SettingDelegate;
import com.admin.work.sign.SignInDelegate;

import butterknife.BindView;

/**
 * @author 345 QQ:1831712732
 * @name ImitateQQMusic
 * @class name：com.admin.work.main.MyDelegate
 * @time 2020/4/24 23:29
 * @descriptionZ 我的页面
 */

public class MyDelegate extends BottomItemDelegate {


    @BindView(R2.id.avatar)
    AppCompatImageView mAvatarImage = null;

    @BindView(R2.id.name)
    AppCompatTextView mTextName = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_my;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAvatarImage.setOnClickListener(v -> startLogin());
        mTextName.setOnClickListener(v -> startLogin());
    }


    public void startLogin() {
        getParentDelegate().getSupportDelegate().start(new SignInDelegate());
    }
}
