package com.dcl.javacv.mvpproject.model.http;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface ProtocolHttp {
    String HTTP_HOST = "http://xxx.xx.xxx.xxx:8080/app/con/";
    String HTTP_COMMON = "common/";
    String METHOD_LOGIN_CODE = HTTP_COMMON + "code";//登录发送验证码
    String METHOD_LOGIN = HTTP_COMMON + "login";//登录
}
