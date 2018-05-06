package com.rogge.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.rogge.R;
import com.rogge.baserx.RxManager;
import com.rogge.utils.EncryptUtils;
import com.rogge.utils.ParamsUtilV2;
import com.rogge.utils.ToastUtils;
import com.rogge.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类
 */
public abstract class BaseActivity extends Activity {
    public Context mContext;
    public RxManager mRxManager;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetcontentView();
        setContentView(R.layout.activity_base);
        LinearLayout mBaseActivityLl = (LinearLayout) findViewById(R.id.base_activity_ll);
        View lView = getLayoutInflater().inflate(getLayoutId(), mBaseActivityLl, false);
        mBaseActivityLl.addView(lView);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        this.initView();
    }

    @Override
    public View findViewById(@IdRes int id) {
        return super.findViewById(id);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }


    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    public abstract void initView();

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtils.showShort(text);
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showEntLog(String text) {
        Log.e("OkHttp", "EntSTr==" + EncryptUtils.aesDecrypt(text, ParamsUtilV2.DES_KEY));
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUtils.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUtils.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUtils.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUtils.showToastWithImg(text, res);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("CurrentPageName", this.getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoadingDialog.cancelDialogForLoading();
        mRxManager.clear();
        unbinder.unbind();
        AppManager.getAppManager().finishActivity(this);
    }

}
