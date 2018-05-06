package com.rogge.gson_converter_factory;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.rogge.baserx.CustomException;
import com.rogge.utils.EncryptUtils;
import com.rogge.utils.ParamsUtilV2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;


/**
 * [Description]
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/6/18.
 * @since 1.0.0
 */

final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String responseString = value.string();
        if (TextUtils.isEmpty(responseString))
            throw new CustomException(CustomException.DATA_NULL, "服务器返回数据为空");
        String response = EncryptUtils.aesDecrypt(responseString, ParamsUtilV2.DES_KEY);
        Log.i("OkHttp", response == null ? "" : response);
        if (TextUtils.isEmpty(response))
            throw new CustomException(CustomException.DATA_BAD, "数据解析失败");

        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);

        try {
            return adapter.fromJson(reader);
        } finally {
            value.close();
        }
    }
}
