package com.rogge.widget.single_check;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rogge.R;


/**
 * [Description]
 * <p/> 单选的View  类似于自定义的checkBox
 * [How to use]
 * <p/> 需要配合 RelevanceSingleCheck 用
 * <p/> 先调用relevanceSingleCheck  设置相关联的 SingleCheckView
 * <p/> 然后 setSingleCheckClickCallBack  设置点击事件的回调监听器
 * [Tips]
 *
 * @author Created by Rogge on 2016-09-27 14:15
 * @since 1.0.0
 */
public class SingleCheckView extends LinearLayout implements View.OnClickListener, SingleCheckListener {

    private TextView mExplain;
    private int mCheckedTvColor;//mExplain选中的颜色
    private int mUnCheckedTvColor;//mExplain未选中的颜色
    private int mDirection;//图片环绕的位置
    private boolean isChecked;//是否选中
    private Drawable mCheckedDrawable;//mExplain选中的图片
    private Drawable mUnCheckedDrawable;//mExplain未选中的图片

    private SingleCheckClickCallBack mSingleCheckClickCallBack;
    private ChangeSingleCheckUi mChangeSingleCheckUi;

    public SingleCheckView(Context context) {
        this(context, null);
    }

    public SingleCheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleCheckView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SingleCheckView);
        String lExplain = a.getString(R.styleable.SingleCheckView_scv_explain);
        mCheckedDrawable = a.getDrawable(R.styleable.SingleCheckView_scv_checked_drawable);
        mUnCheckedDrawable = a.getDrawable(R.styleable.SingleCheckView_scv_unChecked_drawable);
        mCheckedTvColor = a.getColor(R.styleable.SingleCheckView_scv_check_tv_color, 0);
        mUnCheckedTvColor = a.getColor(R.styleable.SingleCheckView_scv_unCheck_tv_color, 0);
        isChecked = a.getBoolean(R.styleable.SingleCheckView_scv_checked, false);
        mDirection = a.getInt(R.styleable.SingleCheckView_scv_direction, 3);
        a.recycle();
        init(context);
        if (!TextUtils.isEmpty(lExplain)) {
            mExplain.setText(lExplain);
        }
        if (isChecked) {
            if (mCheckedDrawable != null) {
                setCheckedUi();
            }
        } else {
            if (mUnCheckedDrawable != null) {
                setUnCheckedUi();
            }
        }

    }

    private void init(Context context) {
        setOnClickListener(this);
        View view = LayoutInflater.from(context).inflate(R.layout.single_check_view, this);
        mExplain = (TextView) view.findViewById(R.id.single_check_view_tv);

    }

    @Override
    public void onClick(View v) {
        if (!isChecked) {
            isChecked = true;
            setCheckedUi();
            if (mSingleCheckClickCallBack != null) mSingleCheckClickCallBack.callBack(this);
            if (mChangeSingleCheckUi != null) mChangeSingleCheckUi.change(v);
        }

    }

    private void setCheckedUi() {
        mExplain.setTextColor(mCheckedTvColor);
        if (mCheckedDrawable == null) return;
        CompoundDrawableUtils.setCompoundDrawableByDirection(mExplain,mCheckedDrawable,mDirection);
    }

    private void setUnCheckedUi() {
        mExplain.setTextColor(mUnCheckedTvColor);
        if (mUnCheckedDrawable == null) return;
        CompoundDrawableUtils.setCompoundDrawableByDirection(mExplain,mUnCheckedDrawable,mDirection);
    }

    @Override
    public void setSCClickCallBackListener(SingleCheckClickCallBack scClickCallBackListener) {
        this.mSingleCheckClickCallBack = scClickCallBackListener;
    }

    @Override
    public void setChangeSingleCheckUiListener(RelevanceSingleCheck relevanceSingleCheck) {
        this.mChangeSingleCheckUi = relevanceSingleCheck;
    }

    @Override
    public void setUnCheck() {
        isChecked = false;
        setUnCheckedUi();
    }

    @Override
    public void setCheck() {
        setCheckedUi();
    }

}
