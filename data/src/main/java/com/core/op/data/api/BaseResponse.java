package com.core.op.data.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/7/15
 */
public class BaseResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
