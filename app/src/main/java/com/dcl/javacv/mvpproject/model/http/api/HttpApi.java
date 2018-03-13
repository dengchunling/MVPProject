package com.dcl.javacv.mvpproject.model.http.api;

import com.dcl.javacv.mvpproject.model.bean.Login;
import com.dcl.javacv.mvpproject.model.http.HttpNoResult;
import com.dcl.javacv.mvpproject.model.http.HttpResult;
import com.dcl.javacv.mvpproject.model.http.ProtocolHttp;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface HttpApi {
    /**
     * 登录时获取验证码.
     *
     * @param phone 手机号
     * @return {"code":0}
     */
    @FormUrlEncoded
    @POST(ProtocolHttp.METHOD_LOGIN_CODE)
    Flowable<HttpNoResult> loginCode(@Field("phone") String phone);

    /**
     * 登录时获取验证码.
     *
     * @param phone 手机号
     * @return {"code":0}
     */
    @FormUrlEncoded
    @POST(ProtocolHttp.METHOD_LOGIN)
    Flowable<HttpResult<Login>> login(@Field("phone") String phone, @Field("code") String code);
}
