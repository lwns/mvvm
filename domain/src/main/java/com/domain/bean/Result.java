package com.domain.bean;

/**
 * Created by hcc on 16/8/22 20:48
 * 100332338@qq.com
 * <p/>
 * 直播数据模型基础类
 */
public class Result<T> {

    public int code;

    public String message;

    public T data;
}
