package com.admin.core.util.storage;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.admin.core.app.Latte;

/**
 * Copyright (C)
 *
 * @file: LattePreference
 * @description: 提示：
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
