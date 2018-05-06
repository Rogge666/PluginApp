package com.rogge.widget.single_check;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/2/17.
 * @since 1.0.0
 */

public class CompoundDrawableUtils {
    public static void setCompoundDrawableByDirection(TextView textView, Drawable drawable,int direction){
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (direction) {
            case 1:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
            case 2:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case 3:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case 4:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
        }
    }
}
