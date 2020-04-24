package com.admin.frame;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.admin.core.app.Latte;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.facebook.stetho.Stetho;
import com.hjq.toast.ToastUtils;


public class ExampleApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate() {
        super.onCreate();


        Latte.init(this)

                .withHost("http://192.168.2.140:80/Frame_Api/")
                .configure();
        //log 和 Tost 工具
        XLog.init(new LogConfiguration.Builder().t().tag("345").build());
        ToastUtils.init(this);

        initStetho();
    }

    /**
     * 查看数据库
     */
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
