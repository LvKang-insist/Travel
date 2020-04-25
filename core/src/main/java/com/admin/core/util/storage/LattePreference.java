package com.admin.core.util.storage;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.admin.core.app.Latte;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Copyright (C)
 *
 * @file: LattePreference
 * @author: 345
 * @Time: 2019/4/21 11:25
 * @description: 提示：
 * Activity.getPreferences(int mode) 生成Activity名.xml 用于Activity内部存储
 * PreferenceManager.getDefaultsharedPreferences(Content) 生成 包名_preferences.xml
 * Context.getSharedPreferences(String name,int mode) 生成name.xml
 */
@SuppressLint("ApplySharedPref")
public class LattePreference {

    private static String USER = "user";
    private static String DEFAULT = "default";


    private static SharedPreferences getAppPreference(String s) {
        return  Latte.getApplication().getSharedPreferences(s, 0);
    }

    private static SharedPreferences.Editor getAppPreferenceEdit(String s) {
        return Latte.getApplication().getSharedPreferences(s, 0)
                .edit();
    }


    public static void setAppFlag(String key, boolean flag) {
        getAppPreferenceEdit(USER)
                .putBoolean(key, flag)
                .commit();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference(USER).getBoolean(key, false);
    }

    public static void setAppData(String key, String flag) {
        getAppPreferenceEdit(USER)
                .putString(key, flag)
                .commit();
    }

    public static String getAppData(String key) {
        return getAppPreference(USER).getString(key, null);
    }


    public static void setAppInteger(String key, int value) {
        getAppPreferenceEdit(USER)
                .putInt(key, value)
                .commit();
    }

    public static int getAppInteger(String key) {
        return getAppPreference(USER).getInt(key, 0);
    }

}
