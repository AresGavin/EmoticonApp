package com.example.app.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者：Gavin 时间：2016/8/17.
 * 描述：
 */
public class AppContext extends Application{

    public static AppContext mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Fresco.initialize(this);
    }
}
