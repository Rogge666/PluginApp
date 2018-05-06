package com.rogge.widget.single_check;

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

public interface SingleCheckListener {
    void setSCClickCallBackListener(SingleCheckClickCallBack scClickCallBackListener);
    void setChangeSingleCheckUiListener(RelevanceSingleCheck relevanceSingleCheck);
    void setUnCheck();
    void setCheck();
}
