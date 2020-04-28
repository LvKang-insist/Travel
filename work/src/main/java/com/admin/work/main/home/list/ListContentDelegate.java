package com.admin.work.main.home.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.gyf.immersionbar.ImmersionBar;


public class ListContentDelegate extends LatteDelegate {

    private String title;
    private String content;

    public  ListContentDelegate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(rootView.findViewById(R.id.delegate_home_list_conetent))
                .init();

        rootView.findViewById(R.id.delegate_hoem_list_content_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });

        AppCompatTextView title = rootView.findViewById(R.id.delegate_hoem_list_content_title);
        AppCompatTextView content = rootView.findViewById(R.id.delegate_hoem_list_content_tv);

        title.setText(this.title);
        content.setText(this.content);
    }
}
