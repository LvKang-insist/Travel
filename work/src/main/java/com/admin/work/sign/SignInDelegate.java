package com.admin.work.sign;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.app.Latte;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.RestCreator;
import com.admin.core.util.storage.LattePreference;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInDelegate extends LatteDelegate {


    private Tencent tencent;
    private AppCompatTextView startUp;
    private AppCompatEditText num;
    private AppCompatEditText pass;
    private AppCompatButton btn;
    private CheckBox check;
    private AppCompatTextView wangji;
    private AppCompatTextView youke;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        rootView.findViewById(R.id.action_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });
        startUp = rootView.findViewById(R.id.delegate_sign_in_up);

        num = rootView.findViewById(R.id.delegate_sign_in_number);
        pass = rootView.findViewById(R.id.delegate_sign_in_password);
        btn = rootView.findViewById(R.id.delegate_sign_up_btn);
        check = rootView.findViewById(R.id.delegate_sign_in_check);
        wangji = rootView.findViewById(R.id.delegate_sign_in_wangji);
        youke = rootView.findViewById(R.id.delegate_sign_in_youke);

        onClick();

        if (LattePreference.getAppFlag("check")) {
            check.setChecked(true);
            String n = LattePreference.getAppData("name");
            String p = LattePreference.getAppData("pass");
            pass.setText(p);
            num.setText(n);
        }
    }

    private void onClick() {
        startUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().start(new SignUpDelegate());
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = num.getText().toString();
                String password = pass.getText().toString();
                if (name.isEmpty()) {
                    ToastUtils.show("账号不允许为空");
                    return;
                } else if (password.isEmpty()) {
                    ToastUtils.show("密码不允许为空");
                    return;
                }
                if (check.isChecked()) {
                    LattePreference.setAppData("pass", password);
                    LattePreference.setAppFlag("check", true);
                }
                login(name, password);
            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LattePreference.setAppFlag("check", isChecked);
            }
        });

        wangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().start(new ForGetDelegate());
            }
        });

        youke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("admin","888");
            }
        });
    }


    private void login(String num, String password) {
        JSONObject object = new JSONObject();
        try {
            object.put("name", num);
            object.put("password", password);
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=urf-8"), object.toString());
            RestCreator.getRestService()
                    .postRaw("http://192.168.43.80/Travel/account/signInAccount.php", body)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            try {
                                JSONObject json = new JSONObject(result);
                                String res = json.getString("result");
                                if ("success".equals(res)) {
                                    ToastUtils.show("登录成功");
                                    LattePreference.setAppData("name", num);
                                    LattePreference.setAppFlag("isSign", true);
                                    getSupportDelegate().pop();
                                } else {
                                    ToastUtils.show(res);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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

}
