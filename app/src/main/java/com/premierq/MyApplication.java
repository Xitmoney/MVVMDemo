package com.premierq;

import android.app.Application;

import com.premierqlibrary.common.Utils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
