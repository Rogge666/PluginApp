package com.rogge.base;

import com.rogge.baserx.RxManager;

import javax.inject.Inject;


/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BasePresenter {
    @Inject
    protected RxManager mRxManager;

    public void onDestroy() {
        mRxManager.clear();
    }

}
