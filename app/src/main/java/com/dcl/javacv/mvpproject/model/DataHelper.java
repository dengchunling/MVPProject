package com.dcl.javacv.mvpproject.model;

import com.dcl.javacv.mvpproject.model.bean.Login;
import com.dcl.javacv.mvpproject.model.http.HttpHelper;
import com.dcl.javacv.mvpproject.model.http.HttpNoResult;
import com.dcl.javacv.mvpproject.model.http.HttpResult;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/13.
 * 网络请求的实现类
 */

public class DataHelper implements HttpHelper{
    private HttpHelper httpHelper;

    public DataHelper(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Flowable<HttpNoResult> loginCode(String phone) {
        return httpHelper.loginCode(phone);
    }

    @Override
    public Flowable<HttpResult<Login>> login(String phone, String code) {
        return httpHelper.login(phone, code);
    }
}
