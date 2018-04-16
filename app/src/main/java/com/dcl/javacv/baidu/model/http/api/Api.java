package com.dcl.javacv.baidu.model.http.api;


import com.dcl.javacv.baidu.BuildConfig;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface Api {
    String HTTP_HOST = BuildConfig.HOST_URL;
    String HTTP_COMMON = "common/";

    String METHOD_LOGIN_CODE = HTTP_COMMON + "code";//登录发送验证码
    String METHOD_LOGIN = HTTP_COMMON + "login";//登录
}
