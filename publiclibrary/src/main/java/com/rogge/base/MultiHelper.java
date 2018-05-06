package com.rogge.base;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * [Description]
 * <p>用于刷新，加载更多的数据业务逻辑处理
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/2/18.
 * @since 1.0.0
 */

public class MultiHelper {
    private MultiHelper.CallBack mCallBack;

    private MultiHelper() {
    }

    public MultiHelper(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public void update(int currentPage, List resultData, BaseQuickAdapter baseQuickAdapter) {
        if (mCallBack == null) {
            Log.e("MultiHelper", "请先设置MultiHelper.CallBack");
            return;
        }
        if (currentPage == 1) {
            if (baseQuickAdapter == null) {
                if (resultData == null) {
                    mCallBack.getFirstDataFail();
                } else {
                    mCallBack.initAdapter(resultData);
                    if (resultData.size() == 0) mCallBack.setEmptyUI();
                }
            } else {
                //setNewData必须放在setEmptyUI前
                baseQuickAdapter.setNewData(resultData);
                if (resultData == null || resultData.size() == 0) {
                    mCallBack.setEmptyUI();
                }
            }
        } else {//第二页adapter不可能为空
            if (resultData == null) {
                baseQuickAdapter.loadMoreFail();
                mCallBack.loadMoreDataFail();
            } else if (resultData.size() != 0 && resultData.size() < 10) {
                baseQuickAdapter.addData(resultData);
                baseQuickAdapter.loadMoreComplete();
                baseQuickAdapter.loadMoreEnd();
            } else if (resultData.size() == 0) {
                baseQuickAdapter.loadMoreEnd();
            } else {
                baseQuickAdapter.addData(resultData);
                baseQuickAdapter.loadMoreComplete();
            }
        }
    }

    public interface CallBack<T> {
        void initAdapter(List<T> resultData);

        void getFirstDataFail();

        void loadMoreDataFail();

        void setEmptyUI();
    }
}
