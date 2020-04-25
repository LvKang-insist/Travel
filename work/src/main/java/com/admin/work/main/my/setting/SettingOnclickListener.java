package com.admin.work.main.my.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.admin.core.app.AccountManager;
import com.admin.core.app.IUserChecker;

import com.admin.core.util.storage.PreferenceUtilsKt;
import com.admin.work.main.my.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hjq.toast.ToastUtils;

public class SettingOnclickListener extends SimpleClickListener {
    private Context context;
    SettingDelegate delegate;

    public SettingOnclickListener(Context context, SettingDelegate delegate) {
        this.context = context;
        this.delegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean list = (ListBean) baseQuickAdapter.getData().get(position);
        switch (list.getId()) {
            case 4:
                AlertDialog.Builder builder = new AlertDialog.Builder(delegate.getContext());
                builder.setTitle("警告");
                builder.setMessage("是否退出登录?");
                builder.setNegativeButton("否", null);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AccountManager.checkAccount(new IUserChecker() {
                            @Override
                            public void onSignIn(String SignState, String SignNumber) {

                            }

                            @Override
                            public void onNoSignIn() {
                                ToastUtils.show("退出登录成功");
                                PreferenceUtilsKt.putUserPhoto(null);
                                PreferenceUtilsKt.putUserName(null);
                            }
                        });

                    }
                });
                builder.show();
                break;
            default:
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
