package com.admin.work.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;

public abstract class BaseWebAgentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bindView();
        init();
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
                if (event .getAction() == KeyEvent.ACTION_DOWN){
                    if (keyCode == KeyEvent.KEYCODE_BACK && mAgentWeb.getWebCreator().getWebView().canGoBack()) { // 表示按返回键时的操作
                        mAgentWeb.getWebCreator().getWebView().goBack() ;// 后退
                        // webView.goForward()//前进
                        return true; // 已处理
                    } else if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        moveTaskToBack(true)
                        finish();
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        getAgentWeb().getWebLifeCycle().onResume();
        super.onResume();

    }

    @Override
    protected void onPause() {
        getAgentWeb().getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        getAgentWeb().getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    abstract AgentWeb getAgentWeb();

    abstract int getLayout();

    abstract void bindView();
}
