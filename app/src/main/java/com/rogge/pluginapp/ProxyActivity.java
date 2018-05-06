package com.rogge.pluginapp;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rogge.plugin.PluginInterface;
import com.rogge.plugin.PluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

public class ProxyActivity extends Activity {

    private PluginInterface pluginInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = getIntent().getStringExtra("className");

        try {
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            pluginInterface = (PluginInterface) instance;
            pluginInterface.attach(this);
            Bundle bundle = new Bundle();
            pluginInterface.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public Resources.Theme getTheme() {
        return PluginManager.getInstance().getTheme();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
         return super.getApplicationInfo();
    }

    @Override
    public void onStart() {
        super.onStart();
        pluginInterface.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
        pluginInterface.onResume();
    }


    @Override
    public void onStop() {
        super.onStop();
        pluginInterface.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pluginInterface.onDestroy();
    }
}
