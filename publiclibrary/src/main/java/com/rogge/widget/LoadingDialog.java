package com.rogge.widget;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rogge.R;


public class LoadingDialog {
    /**
     * 加载数据对话框
     */
    private static Dialog mLoadingDialog;
    private static int count = 0;

    /**
     * 显示加载对话框
     *
     * @param context    上下文
     * @param msg        对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public static synchronized Dialog showDialogForLoading(Activity context, String msg, boolean cancelable) {
        ++count;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView loadingText = view.findViewById(R.id.id_tv_loading_dialog_text);
        loadingText.setText(msg);

        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
        }
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    public static synchronized Dialog showDialogForLoading(Activity context) {
        ++count;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView loadingText = view.findViewById(R.id.id_tv_loading_dialog_text);
        loadingText.setText("加载中...");
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
        }
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    /**
     * 关闭加载对话框
     */
    public static synchronized void cancelDialogForLoading() {
        --count;
        if (count < 0) count = 0;
        if (mLoadingDialog != null && count == 0) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }


}
