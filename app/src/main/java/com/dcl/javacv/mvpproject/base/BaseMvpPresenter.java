package com.dcl.javacv.mvpproject.base;

import com.dcl.javacv.mvpproject.base.BasePresenter;
import com.dcl.javacv.mvpproject.base.BaseView;

/**
 * Created by dengchunlin on 2018/3/12.
 * 带mvp的presenter的基类
 */

public class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T baseView;

    @Override
    public void attachView(T baseView) {
        this.baseView=baseView;
    }

    @Override
    public void detachView() {
        this.baseView=null;
    }
}
