package com.rogge.plugin;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

/**
 * [Description]
 * <p>插件化协议  BaseActivity实现接口并重写其生命周期以及其他方法
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/5/6 0006.
 * @since 1.0.0
 */

public interface PluginInterface {

    void attach(Activity activity);

    void onCreate(@Nullable Bundle savedInstanceState);

    void setTheme(@StyleRes int resid);

    void setContentView(@LayoutRes int layoutResID);

    void onStart();

    void onRestart();

    void onStop();

    void onDestroy();

    Resources getResources();

    ClassLoader getClassLoader();

    LayoutInflater getLayoutInflater();

    Window getWindow();

    WindowManager getWindowManager();

    void onPause();

    void onResume();

    void finish();

    void onBackPressed();

}
