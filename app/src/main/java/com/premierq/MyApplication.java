package com.premierq;

import android.app.Application;

import com.premierqlibrary.common.ContextUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtils.init(this);
    }
}
