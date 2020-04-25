package com.admin.work.main.more;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.hjq.toast.ToastUtils;

public class TabDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.more_tab_delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        View test = rootView.findViewById(R.id.tab_layout);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("tab");
            }
        });
    }
}
