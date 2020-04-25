package com.admin.work.web;

import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.admin.work.R;
import com.elvishew.xlog.XLog;
import com.gyf.immersionbar.ImmersionBar;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;

public class AgentWebActivity extends BaseWebAgentActivity {


    LinearLayoutCompat content;

    AppCompatImageView mShare;

    AppCompatImageView mBack;

    AppCompatTextView mTitle;

    String link;

    private AgentWeb mAgentWeb;

    @Override
    int getLayout() {
        return R.layout.home_web_activity;
    }

    @Override
    void bindView() {

        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(findViewById(R.id.frag_agent_toolbar))
                .init();

        link = getIntent().getStringExtra("link");
        XLog.e(link);
        mBack = findViewById(R.id.frag_agent_back);
        content = findViewById(R.id.frag_agent_linear);
        mShare = findViewById(R.id.frag_agent_share);
        mTitle = findViewById(R.id.frag_agent_title);

        mBack.setOnClickListener(v -> {
            if (mAgentWeb.getWebCreator().getWebView().canGoBack()) {
                mAgentWeb.getWebCreator().getWebView().goBack();
            } else {
                finish();
            }
        });

        initWeb();
    }

    @Override
    AgentWeb getAgentWeb() {
        return mAgentWeb;
    }

    private void  initWeb(){
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(
                        content, new LinearLayout.LayoutParams(-1, -1)
                )
                //设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
//            .useDefaultIndicator(-1, 3)
                .setCustomIndicator(new CoolIndicatorLayout(this))
                //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        mTitle.setText(title);
                    }
                })
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                //打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .createAgentWeb()
                .ready()
                .go(link);

    }
}
