package com.dcl.javacv.mvpproject.base;

import android.support.annotation.StringRes;

/**
 * Created by Administrator on 2018/3/12.
 */

public interface BaseView {
    void showTipMsg(String msg);

    void showTipMsg(@StringRes int msg);

    void showLoading();

    void hideLoading();

    void invalidToken();

    void myFinish();
}
