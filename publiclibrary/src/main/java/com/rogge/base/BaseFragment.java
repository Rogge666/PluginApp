package com.rogge.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rogge.baserx.RxManager;
import com.rogge.utils.EncryptUtils;
import com.rogge.utils.ParamsUtilV2;
import com.rogge.utils.ToastUtils;
import com.rogge.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected View rootView;
    private Unbinder unbinder;
    public RxManager mRxManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        mRxManager = new RxManager();
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    //获取布局文件
    protected abstract int getLayoutResource();

    //初始化view
    protected abstract void initView();


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
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
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
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void showEntLog(String text) {
        Log.e("OkHttp", "EntSTr==" + EncryptUtils.aesDecrypt(text, ParamsUtilV2.DES_KEY));
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtils.showShort(text);
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


    public void showToastWithImg(String text, int res) {
        ToastUtils.showToastWithImg(text, res);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LoadingDialog.cancelDialogForLoading();
        mRxManager.clear();
        unbinder.unbind();
    }

}
