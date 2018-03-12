package com.dcl.javacv.mvpproject.base;

import com.dcl.javacv.mvpproject.base.BaseActivity;
import com.dcl.javacv.mvpproject.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by dengchunlin on 2018/3/12.
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity {
    @Inject
    protected T basePresenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void initView() {
        super.initView();
        initInject();
        if (null != basePresenter) {
            basePresenter.attachView(this);
        }
    }

    protected abstract void initInject();

    @Override
    protected void onDestroy() {
        if (null != basePresenter) {
            basePresenter.detachView();
            basePresenter = null;
        }
        super.onDestroy();
    }
}