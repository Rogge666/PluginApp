package com.rogge.widget;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rogge.R;
import com.rogge.utils.Utils;

/**
 * [Description]
 *
 * [How to use]
 *
 * [Tips]
 * @author
 * Created by Rogge on 2017/1/18.
 * @since
 *   1.0.0
 */
public class ToolBarManager implements Parcelable {

    private TextView mLeftButton;
    private Toolbar mToolbar;
    private boolean mToolbarShow = true;
    private static final long HEADER_HIDE_ANIM_DURATION = 1000;
    public TextView mRightButton;
    public ImageView mLeftImage;
    public RadioButton mRightImage;


    protected ToolBarManager(Parcel in) {

    }

    public static final Creator<ToolBarManager> CREATOR = new Creator<ToolBarManager>() {
        @Override
        public ToolBarManager createFromParcel(Parcel in) {
            return new ToolBarManager(in);
        }

        @Override
        public ToolBarManager[] newArray(int size) {
            return new ToolBarManager[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


    //标题
    private TextView mTitleTv;

    public ToolBarManager(Toolbar toolbar) {
        mToolbar = toolbar;
        mTitleTv = (TextView) mToolbar.findViewById(R.id.title);
        mRightButton = (TextView) mToolbar.findViewById(R.id.right_button);
        mLeftButton = (TextView) mToolbar.findViewById(R.id.left_text_tv);
        mLeftImage = (ImageView) mToolbar.findViewById(R.id.left_image);
        mRightImage = (RadioButton) mToolbar.findViewById(R.id.right_image);

    }

    public ToolBarManager destroy() {
        mToolbar = null;
        return this;
    }

    public ToolBarManager init() {
        //设置toolbar
        mToolbar.setTitleTextColor(ContextCompat.getColor(Utils.getContext(),R.color.ffffff));
        renew();
        return this;
    }

    public ToolBarManager renew() {
        mToolbar.setNavigationIcon(R.drawable.ic_left_arrow_white);
        //防止fragment被销毁后，右边按钮被隐藏
//        showRightButton(false);
        if (mLeftImage != null) {
            showLeftImage(false);
        }
        if (mRightImage != null) {
            showRightImage(false);
        }
        if (mLeftButton != null) {
            showLeftButton(false);
        }
        if (mRightButton != null) {
            showRightButton(false);
        }
        return this;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public ToolBarManager setTitle(int titleResId) {
        return setTitle(Utils.getContext().getString(titleResId));
    }

    public ToolBarManager setTitle(@Nullable String title) {
        if (title == null) {
            mTitleTv.setVisibility(View.GONE);
        } else {
            mTitleTv.setVisibility(View.VISIBLE);
            mTitleTv.setText(title);
        }
        return this;
    }

    public TextView getRightButton() {
        return mRightButton;
    }

    public ImageView getLeftImage() {
        return mLeftImage;
    }

    public ToolBarManager showTitle(boolean isShow) {
        mTitleTv.setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    public ToolBarManager showRightButton(boolean isShow) {
        mRightButton.setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    public ToolBarManager showLeftButton(boolean isShow) {
        mLeftButton.setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    public ToolBarManager showLeftImage(boolean isShow) {
        mLeftImage.setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    public ToolBarManager showRightImage(boolean isShow) {
        mRightImage.setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    public ToolBarManager setLeftImage(int imgResId) {
        mLeftImage.setImageResource(imgResId);
        return this;
    }

    public ToolBarManager setRightImage(int imgResId) {
        Drawable lDrawable = ContextCompat.getDrawable(Utils.getContext(), imgResId);
        lDrawable.setBounds(0, 0, lDrawable.getMinimumWidth(), lDrawable.getMinimumHeight());
        mRightImage.setCompoundDrawables(null, null, lDrawable, null);
        return this;
    }

    public ToolBarManager setRightImage(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mRightImage.setCompoundDrawables(null, null, drawable, null);
        return this;
    }


    public ToolBarManager clickLeftImage(View.OnClickListener clickListener) {
        mLeftImage.setOnClickListener(clickListener);
        return this;
    }

    public ToolBarManager clickRightImage(View.OnClickListener clickListener) {
        mRightImage.setOnClickListener(clickListener);
        mRightImage.setChecked(false);
        return this;
    }

    public ToolBarManager setRightText(String rightText) {
        if (mRightButton != null) {
            mRightButton.setText(rightText);
        }
        return this;
    }

    public ToolBarManager setLeftText(String rightText) {
        if (mLeftButton != null) {
            mLeftButton.setText(rightText);
        }
        return this;
    }

    public ToolBarManager setRightText(int stingResId) {
        return setRightText(Utils.getContext().getString(stingResId));
    }

    public ToolBarManager clickRightButton(View.OnClickListener clickListener) {
        mRightButton.setOnClickListener(clickListener);
        return this;
    }

    public ToolBarManager clickLeftButton(View.OnClickListener clickListener) {
        mLeftButton.setOnClickListener(clickListener);
        return this;
    }

    public ToolBarManager showBack(boolean show) {
        if (show) {
            mToolbar.setNavigationIcon(R.drawable.ic_left_arrow_white);
        } else {
            mToolbar.setNavigationIcon(null);
        }
        return this;
    }

    /**
     * 显示或隐藏toolbar，带效果
     *
     * @param show
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void showToolbar(boolean show) {
        if (show == mToolbarShow || mToolbar == null) return;
        mToolbarShow = show;
        if (show) {
            mToolbar.animate()
                    .translationY(0)
                    .alpha(1)
                    .setDuration(HEADER_HIDE_ANIM_DURATION)
                    .setInterpolator(new DecelerateInterpolator());
        } else {
            mToolbar.animate()
                    .translationY(-mToolbar.getBottom())
                    .alpha(0)
                    .setDuration(HEADER_HIDE_ANIM_DURATION)
                    .setInterpolator(new DecelerateInterpolator());
        }
    }

    /**
     * 设置toolbar透明度
     *
     * @param alpha
     */
    protected void setToolbarAlpha(float alpha) {
        mToolbar.setAlpha(alpha);
    }

}
