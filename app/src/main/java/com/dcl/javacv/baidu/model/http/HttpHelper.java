package com.dcl.javacv.baidu.model.http;

import com.dcl.javacv.baidu.model.bean.Login;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/13.
 * 网络接口，接口参数Token统一处理，方法中不传Token
 */

public interface HttpHelper {
    /**
     * 登录时获取验证码.
     *
     * @param phone 手机号
     * @return {"code":0}
     */
    Flowable<HttpNoResult> loginCode(String phone);
  Flowable<HttpResult<Login>> login(String phone, String code);
//  Flowable<HttpResult<List<DiyBean>>> diyKeys(String allId);
}
