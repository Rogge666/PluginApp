package com.rogge.pluginapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rogge.utils.Utils;

public class MyApplication extends Application {

    private static MyApplication ourInstance = new MyApplication();

    public static MyApplication getInstance() {
        return ourInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        Utils.init(this);
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();
        ARouter.init(this);
    }

    public Context getContext() {
        return this.getApplicationContext();
    }

}
