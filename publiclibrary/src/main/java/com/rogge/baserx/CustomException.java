package com.rogge.baserx;

/**
 * des:服务器请求异常
 * Created by xsf
 * on 2016.09.10:16
 */
public class CustomException extends RuntimeException {

    //通用错误
    public static final int COMMON_BAD = 0;
    //服务器返回数据为空
    public static final int DATA_NULL = 1;
    //数据解析失败
    public static final int DATA_BAD = 2;
    //服务器异常
    public static final int SERVER_FAIL = 3;


    private int ErrorCode;
    private String ErrorMsg;

    private CustomException() {
    }

    private CustomException(int errorCode) {
    }

    private CustomException(String errorMsg) {
    }

    public CustomException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.ErrorCode = errorCode;
        this.ErrorMsg = errorMsg;
    }

    private int getErrorCode() {
        return ErrorCode;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }
}
