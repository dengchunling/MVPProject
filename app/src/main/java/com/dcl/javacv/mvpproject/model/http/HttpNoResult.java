package com.dcl.javacv.mvpproject.model.http;

/**
 * Created by Administrator on 2018/3/13.
 * 判断返回code码,没有解析数据的返回
 */

public class HttpNoResult {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public HttpNoResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public HttpNoResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "HttpNoResult{" + "code=" + code + ", msg='" + msg + '\'' + '}';
    }
}
