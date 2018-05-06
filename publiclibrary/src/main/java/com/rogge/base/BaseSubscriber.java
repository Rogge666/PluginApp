package com.rogge.base;

import android.app.Activity;
import android.content.Context;

import com.rogge.R;
import com.rogge.base_bean.BaseRespModel;
import com.rogge.baserx.CustomException;
import com.rogge.utils.NetWorkUtils;
import com.rogge.utils.ToastUtils;
import com.rogge.utils.Utils;
import com.rogge.widget.LoadingDialog;

import rx.Subscriber;

/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

public abstract class BaseSubscriber<T> extends Subscriber<BaseRespModel<T>> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public BaseSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public BaseSubscriber(Context context) {
        this(context, Utils.getContext().getString(R.string.loading), true);
    }

    public BaseSubscriber(Context context, boolean showDialog) {
        this(context, Utils.getContext().getString(R.string.loading), showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(BaseRespModel<T> response) {
        if (response != null) {
            boolean state = response.isError();
            if (state) {
                onFailedCall(true, response.getMessage());
            } else {
                if (response.getResults() != null) {
                    onSucCall(response.getResults());
                } else {
                    sucWithNoData(response.getMessage());
                }
            }
            onCompletedCall(state, response.getMessage());
        } else {
            onErrorCall();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(Utils.getContext())) {
            ToastUtils.showShort(Utils.getContext().getString(R.string.no_net));
        } else if (e instanceof CustomException) {//服务器
            onFailedCall(true, e.getMessage());
        } else {//其它
            ToastUtils.showShort(Utils.getContext().getString(R.string.net_error));
        }
    }

    public void sucWithNoData(String result) {
        ToastUtils.showShort("没有数据！");
    }

    /**
     * 状态非成功时会走向这里，提供默认的实现，如果有需要可进行重写
     */
    public void onFailedCall(boolean code, String message) {
        ToastUtils.showShort(message);
    }

    /**
     * 状态成功时会走向这里
     */
    public abstract void onSucCall(T response);


    //无论成功或失败都会走向这里，可用于统一处理失败和成功都需要处理的事件
    public void onCompletedCall(boolean status, String message) {

    }

    /**
     * 状态为空进处理
     */
    public void onErrorCall() {

    }


}
