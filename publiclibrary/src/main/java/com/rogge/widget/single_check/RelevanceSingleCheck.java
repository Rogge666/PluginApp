package com.rogge.widget.single_check;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/2/16.
 * @since 1.0.0
 */

public class RelevanceSingleCheck implements ChangeSingleCheckUi{

    private List<SingleCheckListener> mSingleCheckListeners = new ArrayList<>();

    public void relevanceSingleCheck(SingleCheckListener... singleCheckListeners) {
        Collections.addAll(mSingleCheckListeners, singleCheckListeners);
    }

    public void setSingleCheckClickCallBack(SingleCheckClickCallBack singleCheckClickCallBack){
        for (SingleCheckListener lSingleCheckListener : mSingleCheckListeners) {
            lSingleCheckListener.setChangeSingleCheckUiListener(this);
            lSingleCheckListener.setSCClickCallBackListener(singleCheckClickCallBack);
        }
    }

    @Override
    public void change(View v) {
        for (SingleCheckListener lSingleCheckListener : mSingleCheckListeners) {
            if (v.getId() == ((ViewGroup)lSingleCheckListener).getId()){
                lSingleCheckListener.setCheck();
            }else {
                lSingleCheckListener.setUnCheck();
            }
        }
    }
}
