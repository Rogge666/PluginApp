package com.rogge.base;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;


/**
 * [Description]
 * <p>用于刷新，加载更多的数据业务逻辑处理
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/1/16.
 * @since 1.0.0
 */

public abstract class ListHelper {

    protected ListHelper(int currentPage, List resultData, BaseQuickAdapter baseQuickAdapter) {
        if (currentPage == 1) {
            if (baseQuickAdapter == null) {
                if (resultData == null) {
                    getFirstDataFail();
                } else {
                    initAdapter();
                    if (resultData.size() == 0) setEmptyUI();
                }
            } else {
                //setNewData必须放在setEmptyUI前
                baseQuickAdapter.setNewData(resultData);
                if (resultData == null || resultData.size() == 0) {
                    setEmptyUI();
                }
            }
        } else {//第二页adapter不可能为空
            if (resultData == null) {
                baseQuickAdapter.loadMoreFail();
                loadMoreDataFail();
            } else if (resultData.size() == 0) {
                baseQuickAdapter.loadMoreEnd();
            } else {
                baseQuickAdapter.addData(resultData);
                baseQuickAdapter.loadMoreComplete();
            }
        }
    }

    protected abstract void initAdapter();

    protected abstract void getFirstDataFail();

    protected abstract void loadMoreDataFail();

    protected abstract void setEmptyUI();

}
