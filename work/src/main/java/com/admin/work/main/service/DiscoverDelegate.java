package com.admin.work.main.service;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.work.R;
import com.admin.work.share.ShareDialog;
import com.admin.work.web.CoolIndicatorLayout;
import com.elvishew.xlog.XLog;
import com.gyf.immersionbar.ImmersionBar;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


/**
 * Copyright (C)
 *
 * @file: DiscoverDelegate
 * @author: 345
 * @Time: 2019/5/4 15:17
 * @description:
 */
public class DiscoverDelegate extends BottomItemDelegate {


    LinearLayoutCompat content;

    AppCompatImageView mShare;

    AppCompatImageView mBack;

    AppCompatTextView mTitle;

    String link = "https://www.ctrip.com/?sid=155950&allianceid=4897&ouid=key&keywordid=134598181845&bd_vid=9140245204797640826&ds_rl=1284915&gclid=CMnY4sX5hOkCFQ9svAodPXgAiQ&gclsrc=ds";

    private AgentWeb mAgentWeb;

    SmartRefreshLayout mRefresh;

    boolean isRefresh = false;

    @Override
    public Object setLayout() {
        return R.layout.delegata_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .titleBar(rootView.findViewById(R.id.frag_agent_toolbar))
                .init();

        XLog.e(link);
        mBack = rootView.findViewById(R.id.frag_agent_back);
        content = rootView.findViewById(R.id.frag_agent_linear);
        mShare = rootView.findViewById(R.id.frag_agent_share);
        mTitle = rootView.findViewById(R.id.frag_agent_title);

        mRefresh = rootView.findViewById(R.id.refresh_layout);

        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAgentWeb.getWebCreator().getWebView().reload();
                mRefresh.finishRefresh();
            }
        });

        initWeb();
        init();

        initShare();
    }

    private void initShare() {
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mAgentWeb.getWebCreator().getWebView().getUrl();
                ShareDialog dialog = new ShareDialog(getContext());
                dialog.setShareContent(url);
                dialog.setShareItemClickListener(v1 -> dialog.cancel());
                dialog.show();
            }
        });
    }

    AgentWeb getAgentWeb() {
        return mAgentWeb;
    }

    @Override
    public void onSupportVisible() {
        if (!isRefresh) {
            mAgentWeb.getWebCreator().getWebView().reload();
            isRefresh = true;
        }
        super.onSupportVisible();
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    private void initWeb() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(
                        content, new LinearLayout.LayoutParams(-1, -1)
                )
                //设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
//            .useDefaultIndicator(-1, 3)
                .setCustomIndicator(new CoolIndicatorLayout(getContext()))
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


    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        AgentWeb mAgentWeb = getAgentWeb();
        AgentWebConfig.debug();

        // AgentWeb 没有把WebView的功能全面覆盖 ，所以某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        mAgentWeb.getWebCreator().getWebView().setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        mAgentWeb.getWebCreator().getWebView().getSettings().setJavaScriptEnabled(true);
        //优先使用网络
        mAgentWeb.getWebCreator().getWebView().getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //将图片调整到适合webView的大小
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        //支持内容重新布局
        mAgentWeb.getWebCreator().getWebView().getSettings().
                setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ;
        //支持自动加载图片
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadsImagesAutomatically(true);
        //当webView调用requestFocus时为webView设置节点
        mAgentWeb.getWebCreator().getWebView().getSettings().setNeedInitialFocus(true);
        //自适应屏幕
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadWithOverviewMode(true);
        //开启DOM storage API功能（HTML5 提供的一种标准的接口，主要将键值对存储在本地，在页面加载完毕后可以通过 javascript 来操作这些数据。）
        mAgentWeb.getWebCreator().getWebView().getSettings().setDomStorageEnabled(true);
        //支持缩放
        mAgentWeb.getWebCreator().getWebView().getSettings().setBuiltInZoomControls(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setSupportZoom(true);

        //允许webView对文件的操作
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccess(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowUniversalAccessFromFileURLs(true);

        mAgentWeb.getWebCreator().getWebView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mAgentWeb.getWebCreator().getWebView().canGoBack()) { // 表示按返回键时的操作
                        mAgentWeb.getWebCreator().getWebView().goBack();// 后退
                        // webView.goForward()//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        getAgentWeb().getWebLifeCycle().onResume();
        super.onResume();

    }

    @Override
    public void onPause() {
        getAgentWeb().getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    public void onDestroy() {
        getAgentWeb().getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
