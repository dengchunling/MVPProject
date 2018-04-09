package com.dcl.javacv.serialport.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dengchunlin on 2018/3/12.
 * 带mvp的presenter的基类
 */

public class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T baseView;
    private CompositeDisposable disposables;

    @Override
    public void attachView(T baseView) {
        this.baseView=baseView;
    }

    protected void addSubscribe(Disposable disposable) {
        if (null == disposables) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }

    @Override
    public void detachView() {
        this.baseView=null;
    }

    private void unSubscribe() {
        if (null != disposables) {
            disposables.clear();
            disposables = null;
        }
    }
}
