package com.rogge.pluginapp;

import android.content.Intent;
import android.os.Environment;
import android.view.View;

import com.rogge.base.BaseActivity;
import com.rogge.plugin.PluginManager;

import java.io.File;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        PluginManager.getInstance().setContext(mContext);
    }

    @OnClick({R.id.load_plugin1, R.id.load_plugin21, R.id.goto_plugin1, R.id.goto_plugin2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.load_plugin1:
                File file1 = new File(Environment.getExternalStorageDirectory(),"plugin1.apk");
                PluginManager.getInstance().loadPath(file1.getAbsolutePath());
                break;
            case R.id.load_plugin21:
                File file2 = new File(Environment.getExternalStorageDirectory(),"plugin2.apk");
                PluginManager.getInstance().loadPath(file2.getAbsolutePath());
                break;
            case R.id.goto_plugin1:
                Intent intent1 = new Intent(this,ProxyActivity.class);
                intent1.putExtra("className",PluginManager.getInstance().getEntryActivityName());
                startActivity(intent1);
                break;
            case R.id.goto_plugin2:
                Intent intent2 = new Intent(this,ProxyActivity.class);
                intent2.putExtra("className","com.rogge.pluginapp2.MainActivity");
                startActivity(intent2);
                break;
        }
    }

}
