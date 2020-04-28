package com.admin.work.sign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.admin.core.app.Latte;
import com.admin.core.net.RestCreator;
import com.admin.core.util.storage.PreferenceUtilsKt;
import com.admin.work.R;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInDelegate extends AppCompatActivity implements IUiListener {


    private Tencent tencent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delegate_sign_in);

        findViewById(R.id.action_close).setOnClickListener(v -> finish());
        findViewById(R.id.action_login).setOnClickListener(v -> {
            login();
        });
    }


    public void login() {
        if (tencent == null) {
            tencent = Tencent.createInstance("101873518", Latte.getApplication());
        }
        tencent.login(this, "all", this);
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
            tencent.setAccessToken(accessToken, expiresIn);
            tencent.setOpenId(openId);
            QQToken qqToken = tencent.getQQToken();
            getUserInfo(qqToken, openId);
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


    private void getUserInfo(QQToken qqToken, String openId) {
        UserInfo userInfo = new UserInfo(Latte.getApplication(), qqToken);
        userInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject response = (JSONObject) o;
                XLog.e(response.toString());
                try {
                    String nickname = response.getString("nickname");
                    String figureurl2 = response.getString("figureurl_2");
                    save(nickname, figureurl2, openId);
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

    private void save(String nickname, String figureurl2, String openId) {
        JSONObject object = new JSONObject();
        try {
            object.put("name", nickname);
            object.put("photo", figureurl2);
            object.put("openId", openId);
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=urf-8"), object.toString());
            RestCreator.getRestService()
                    .postRaw("http://192.168.43.80/Travel/account/SignUpAccount.php", body)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            XLog.e(result);
                            PreferenceUtilsKt.putUserName(nickname);
                            PreferenceUtilsKt.putUserPhoto(figureurl2);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            ToastUtils.show("网络错误");
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
            //当QQ登录完成后，进行回调
            Tencent.onActivityResultData(requestCode, resultCode, data, this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
