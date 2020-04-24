package com.admin.frame;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.admin.core.activitys.ProxyActivity;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.loader.ILauncherListener;
import com.admin.core.ui.loader.OnLauncherFinishTag;
import com.admin.work.main.EcBottomDelegate;
import com.admin.work.sign.SignInDelegate;
import com.gyf.immersionbar.ImmersionBar;

public class ExampleActivity extends ProxyActivity   {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }


}
