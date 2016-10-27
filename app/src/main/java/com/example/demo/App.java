package com.example.demo;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;


/**
 * Created by zhuangsj on 16-10-27.
 */


public class App extends Application {
    public void onCreate() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isCheck = sharedPreferences.getBoolean("settings_night", false);
        Log.d("zhuangsj", "App  onCreate() returned: " + isCheck);
        if (isCheck) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate();
    }
}