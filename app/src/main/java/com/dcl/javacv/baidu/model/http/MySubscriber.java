package com.dcl.javacv.baidu.model.http;

import com.dcl.javacv.baidu.base.BaseView;
import com.dcl.javacv.baidu.model.HttpCode;

import java.util.concurrent.TimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Administrator on 2018/3/13.
 */

public abstract class MySubscriber<T> extends ResourceSubscriber<T> {
    private BaseView baseView;
    private boolean showLoading;

    public MySubscriber(BaseView baseView) {
        this.baseView = baseView;
    }

    public MySubscriber(BaseView baseView, boolean showLoading) {
        this.baseView = baseView;
        this.showLoading = showLoading;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != baseView && showLoading) {
            baseView.showLoading();
        }
    }

    @Override
    public void onError(Throwable t) {
        if (null == baseView) {
            return;
        }
        baseView.hideLoading();
        if (t instanceof ApiException) {
            ApiException apiException = (ApiException) t;
            switch (apiException.getCode()) {
                case HttpCode.NO_PARAMETER:
                    baseView.showTipMsg("参数为空");
                    break;
                case HttpCode.SERVER_ERR:
                    baseView.showTipMsg("服务器错误");
                    break;
                default:
                    break;
            }
        }else if (t instanceof TimeoutException){
            baseView.showTipMsg("请求超时，请稍后重试");
        }
    }

    @Override
    public void onComplete() {
        if (null != baseView) {
            baseView.hideLoading();
        }
    }
}
