package com.rogge.utils;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/2/25.
 * @since 1.0.0
 */

public class ParamsUtil {
    private Map<String, String> params;

    public ParamsUtil() {
        params = new HashMap<>();
    }

    public void put(String key, Object value) {
        if (value == null) {
            Log.e("ParamsUtil", key + "为null");
            params.put(key, "");
        } else {
            params.put(key, String.valueOf(value));
        }
    }

    public Map<String, String> getParams(){
        return params;
    }

    /**
     * 经纬度转String
     *
     * @param input 输入
     * @return 输出
     */
    public static String locationCastStr(double input) {
        return new DecimalFormat("0.000000").format(input);
    }

}
