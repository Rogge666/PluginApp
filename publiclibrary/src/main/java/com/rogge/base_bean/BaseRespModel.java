package com.rogge.base_bean;

import java.io.Serializable;

/**
 * [Description]
 *
 * [How to use]
 *
 * [Tips]
 * @author
 *   Created by Rogge on 2016/7/19.
 * @since
 *   1.0.0
 */
public class BaseRespModel<T> implements Serializable {
    private boolean error ; // 是否失败
    private String message; // 返回消息
    private int count;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
