package com.rogge.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * [Description]
 * <p>
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/6/25.
 * @since 1.0.0
 */

public class ParamsUtilV2 {
    private static final String PUB_KEY = "+wpYuSiWKbPntpQWmhGZOGiDzCsBlUmckcTsWBpKjrBBrD6UC3JBi/nxH8GX/Mo/kNVusl1Q/xn++76u9ZXkiFC7HaihVQgs4zR3mevf3Q4KM5LUl2aRWzmwTJfKDgZ4ubD38vu/K0qVFXmPIOpdOQn7rGpKJxCDGR+DYFpJrjl6hlkwe9RDWtvODOBlaXZPDfkiFTbo3JCqdOgIYDRAEgbcR2R2tzx2Q8brVVJU9qk4suyg4eHILW0fCCqOTaoWwX1w7JVJYpU/6e7FYpEs8lrjPpwIDAQAB";
    public static String DES_KEY = "";

    private Map<String, String> params;

    public ParamsUtilV2() {
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

    public Map<String, String> getParams(String urlName) {
        JsonObject lJsonObject = new JsonObject();
        for (String key : params.keySet()) {
            lJsonObject.addProperty(key, params.get(key));
        }
        String ps = lJsonObject.toString();
        String reqKey = "";
        String reqMsg;
        String sign;
        if (TextUtils.isEmpty(DES_KEY)) {
            DES_KEY = aesKeyGeneration();
            reqKey = EncryptUtils.encryptRSAForClient(DES_KEY, EncryptUtils.getPubKey(PUB_KEY));
        }
        reqMsg = EncryptUtils.aesEncrypt(ps, DES_KEY);
        sign = EncryptUtils.bitToAsc(EncryptUtils.encryptHmacSHA256(("POST" + '&' + "/com/rogge/rest/" + urlName + ".do" + '&' + "req_key=" + reqKey + '&' + "req_msg=" + reqMsg + '&').getBytes(), DES_KEY.getBytes()));
        params.clear();
        params.put("req_key", reqKey);
        params.put("req_msg", reqMsg);
        params.put("sign", sign);
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

    private String aesKeyGeneration() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int nextInt = random.nextInt(500000);
            int seed = (nextInt % 90 + 33);
            char c = (char) seed;
            sb.append(c);
        }
        return sb.toString();
    }

}
