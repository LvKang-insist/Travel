package com.admin.work.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.admin.core.app.Latte;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.rx.RxRequest;
import com.admin.core.util.value.Resource;
import com.admin.work.R;
import com.admin.work.R2;
import com.elvishew.xlog.XLog;
import com.google.android.material.textfield.TextInputEditText;
import com.hjq.toast.ToastUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Copyright (C)
 *
 * @file: SignInDelegate
 * @author: 345
 * @Time: 2019/4/22 14:34
 * @description: 登录界面
 */
public class SignInDelegate extends LatteDelegate implements IUiListener {


    @BindView(R2.id.action_close)
    AppCompatImageView mClose = null;

    @BindView(R2.id.action_login)
    AppCompatButton mLogin = null;

    private Tencent tencent;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mClose.setOnClickListener(v -> getSupportDelegate().pop());
        mLogin.setOnClickListener(v -> {
            login();
        });
    }

    public void login() {
        if (tencent == null) {
            tencent = Tencent.createInstance("101873518", Latte.getApplication());
        }
        tencent.login(getActivity(), "all", this);
    }

    /**
     * 进行qq登录
     */
    @Override
    public void onComplete(Object o) {
        JSONObject response = (JSONObject) o;
        try {
            String openId = response.getString("openid");
            String accessToken = response.getString("access_token");
            String expiresIn = response.getString("expires_in");
            long expiresTime = response.getLong("expires_time");
            tencent.setAccessToken(accessToken, expiresIn);
            tencent.setOpenId(openId);
            QQToken qqToken = tencent.getQQToken();
            getUserInfo(qqToken, expiresTime, openId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {
        ToastUtils.show("登陆失败");
    }

    @Override
    public void onCancel() {
        ToastUtils.show("登陆取消");
    }


    private void getUserInfo(QQToken qqToken, long expires_time, String openid) {
        UserInfo userInfo = new UserInfo(Latte.getApplication(), qqToken);
        userInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject response = (JSONObject) o;

                try {
                    String nickname = response.getString("nickname");
                    String figureurl_2 = response.getString("figureurl_2");

                    XLog.json(response.toString());
//                    save(nickname, figureurl_2, openid, expires_time);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(Latte.getApplication(), "登录失败:reason" + uiError.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(Latte.getApplication(), "登录取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
