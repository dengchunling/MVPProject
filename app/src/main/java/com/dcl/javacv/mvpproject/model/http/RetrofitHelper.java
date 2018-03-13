package com.dcl.javacv.mvpproject.model.http;

import com.dcl.javacv.mvpproject.model.bean.Login;
import com.dcl.javacv.mvpproject.model.http.api.HttpApi;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2018/3/13.
 */

public class RetrofitHelper implements HttpHelper {
    private HttpApi httpApi;

    @Inject
    public RetrofitHelper(HttpApi httpApi) {
        this.httpApi = httpApi;
    }

    @Override
    public Flowable<HttpNoResult> loginCode(String phone) {
        return httpApi.loginCode(phone);
    }

    @Override
    public Flowable<HttpResult<Login>> login(String phone, String code) {
        return httpApi.login(phone, code);
    }
}
