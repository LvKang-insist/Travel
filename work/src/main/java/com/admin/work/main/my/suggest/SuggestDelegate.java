package com.admin.work.main.my.suggest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.elvishew.xlog.XLog;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;

public class SuggestDelegate extends LatteDelegate {

    private AppCompatEditText inputEdit;
    private AppCompatTextView mCount;
    private AppCompatEditText inputEmail;
    private AppCompatButton commit;

    @Override
    public Object setLayout() {
        return R.layout.delegate_my_suggest;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(rootView.findViewById(R.id.suggest_toolbar))
                .init();

        inputEdit = rootView.findViewById(R.id.ed_afb_problem);
        mCount = rootView.findViewById(R.id.tv_afb_current_text);
        inputEmail = rootView.findViewById(R.id.et_afb_phone_email);
        commit = rootView.findViewById(R.id.bt_afb_commit);
        init();
    }

    private void init() {
        inputEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length <= 500) {
                    mCount.setText(length + "/" + 500);
                } else {
                    ToastUtils.show("字数超出最大限制");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suggest = inputEdit.getText().toString();
                String email = inputEmail.getText().toString();
                XLog.e(suggest);
                XLog.e(email);
                ToastUtils.show("提交成功");
            }
        });
    }
}
