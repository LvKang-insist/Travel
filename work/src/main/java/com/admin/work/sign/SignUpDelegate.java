package com.admin.work.sign;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.RestClient;
import com.admin.core.net.RestCreator;
import com.admin.core.util.storage.LattePreference;
import com.admin.work.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.hjq.toast.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpDelegate extends LatteDelegate {


    private AppCompatEditText number;
    private AppCompatEditText passOne;
    private AppCompatEditText passTwo;
    private AppCompatButton btn;
    private AppCompatEditText mibao;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        rootView.findViewById(R.id.delegate_home_list_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });

        number = rootView.findViewById(R.id.delegate_sign_up_number);
        passOne = rootView.findViewById(R.id.delegate_sign_up_password);
        passTwo = rootView.findViewById(R.id.delegate_sign_up_true_pass);
        btn = rootView.findViewById(R.id.delegate_sign_up_btn);
        mibao = rootView.findViewById(R.id.delegate_sign_up_mibao);
        init();
    }

    private void init() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = number.getText().toString();
                String pass = passOne.getText().toString();

                String mb = mibao.getText().toString();
                if (!passOne.getText().toString().equals(passTwo.getText().toString())) {
                    ToastUtils.show("密码不一致");
                    return;
                }
                if (num.isEmpty()){
                    ToastUtils.show("账号不能为空");
                }
                if (mb.isEmpty()) {
                    ToastUtils.show("密保不能为空");
                }

                JSONObject object = new JSONObject();
                try {
                    object.put("name", num);
                    object.put("password", pass);
                    object.put("encrypted", mb);
                    RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=urf-8"), object.toString());

                    RestCreator.getRestService()
                            .postRaw("http://192.168.43.80/Travel/account/account.php", body)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String result = response.body();
                                    try {
                                        JSONObject json = new JSONObject(result);
                                        String str = json.getString("result");
                                        if ("success".equals(str)) {
                                            ToastUtils.show("注册成功");
                                            getSupportDelegate().pop();
                                        } else {
                                            ToastUtils.show(str);
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
        });
    }
}