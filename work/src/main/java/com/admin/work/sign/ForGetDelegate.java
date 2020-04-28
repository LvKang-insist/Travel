package com.admin.work.sign;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.RestCreator;
import com.admin.work.R;
import com.hjq.toast.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForGetDelegate extends LatteDelegate {

    private AppCompatEditText number;
    private AppCompatEditText passOne;
    private AppCompatButton btn;
    private AppCompatEditText mibao;

    @Override
    public Object setLayout() {
        return R.layout.delegate_forget;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        rootView.findViewById(R.id.delegate_forget_back).setOnClickListener(v -> getSupportDelegate().pop());
        AppCompatTextView title = rootView.findViewById(R.id.delegat_forget_title);
        number = rootView.findViewById(R.id.delegate_forget_number);
        passOne = rootView.findViewById(R.id.delegate_forget_password);
        btn = rootView.findViewById(R.id.delegate_forget_btn);
        mibao = rootView.findViewById(R.id.delegate_forget_mibao);

        check();
    }

    private void check() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = number.getText().toString();
                String pass = passOne.getText().toString();
                String mb = mibao.getText().toString();
                if (num.isEmpty()) {
                    ToastUtils.show("账号不能为空");
                }
                if (pass.isEmpty()) {
                    ToastUtils.show("密码不能为空");
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
                            .postRaw("http://192.168.43.80/Travel/account/amend.php", body)
                            .enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String result = response.body();
                                    try {
                                        JSONObject json = new JSONObject(result);
                                        String str = json.getString("result");
                                        if ("success".equals(str)) {
                                            ToastUtils.show("修改成功");
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


