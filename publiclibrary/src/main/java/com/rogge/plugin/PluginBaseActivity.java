package com.rogge.plugin;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2018/5/6 0006.
 * @since 1.0.0
 */

public class PluginBaseActivity extends Activity implements PluginInterface {
    private Activity diActivity;

    @Override
    public void attach(Activity activity) {
        this.diActivity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public View findViewById(@IdRes int id) {
        if (diActivity == null) {
            return super.findViewById(id);
        } else {
            return diActivity.findViewById(id);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (diActivity == null) {
            super.setContentView(layoutResID);
        } else {
            diActivity.setContentView(layoutResID);
        }
    }

    @Override
    public Resources getResources() {
        if (diActivity == null) {
            return super.getResources();
        } else {
            return diActivity.getResources();
        }
    }

    @Override
    public Resources.Theme getTheme() {
        if (diActivity == null) {
            return super.getTheme();
        } else {
            return diActivity.getTheme();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (diActivity == null) {
            return super.getClassLoader();
        } else {
            return diActivity.getClassLoader();
        }
    }

    @Override
    public Window getWindow() {
        if (diActivity == null) {
            return super.getWindow();
        } else {
            return diActivity.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (diActivity == null) {
            return super.getWindowManager();
        } else {
            return diActivity.getWindowManager();
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (diActivity == null) {
            return super.getApplicationInfo();
        } else {
            return diActivity.getApplicationInfo();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (diActivity == null) {
            return super.getLayoutInflater();
        } else {
            return diActivity.getLayoutInflater();
        }
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }
}
