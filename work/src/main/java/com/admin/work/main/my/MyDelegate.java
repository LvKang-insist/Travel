package com.admin.work.main.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.util.storage.PreferenceUtilsKt;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.my.setting.SettingDelegate;
import com.admin.work.main.my.suggest.SuggestDelegate;
import com.admin.work.sign.SignInDelegate;
import com.bumptech.glide.Glide;

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
        isLogin();
        mAvatarImage.setOnClickListener(v -> startLogin());
        mTextName.setOnClickListener(v -> startLogin());

        rootView.findViewById(R.id.delegate_my_suggest).setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new SuggestDelegate()));
        rootView.findViewById(R.id.delegate_my_setting).setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new SettingDelegate()));
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        isLogin();
    }

    public void startLogin() {
        if (isLogin()) {
            return;
        }
        getContext().startActivity(new Intent(getContext(), SignInDelegate.class));
    }


    boolean isLogin() {
        String photo = PreferenceUtilsKt.getUserPhoto();
        String name = PreferenceUtilsKt.getUserName();
        if (photo != null && name != null) {
            Glide.with(getContext())
                    .load(photo)
                    .into(mAvatarImage);
            mTextName.setText(name);
            return true;
        } else {
            Glide.with(getContext())
                    .load(R.drawable.jilaizhi)
                    .into(mAvatarImage);
            mTextName.setText("未登录");
        }
        return false;
    }
}
