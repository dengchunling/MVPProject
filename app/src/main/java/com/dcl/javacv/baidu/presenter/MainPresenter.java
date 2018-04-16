package com.dcl.javacv.baidu.presenter;

import com.dcl.javacv.baidu.MyApplication;
import com.dcl.javacv.baidu.base.BaseMvpPresenter;
import com.dcl.javacv.baidu.contract.MainContract;
import com.dcl.javacv.baidu.model.http.HttpNoResult;
import com.dcl.javacv.baidu.model.http.MyRxUtils;
import com.dcl.javacv.baidu.model.http.MySubscriber;
import com.dcl.javacv.baidu.model.DataHelper;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by dengchunlin on 2018/3/12.
 */

public class MainPresenter extends BaseMvpPresenter<MainContract.IView> implements MainContract.Presenter {
    private DataHelper dataHelper;

    @Inject
    MainPresenter() {
        dataHelper = MyApplication.getAppComponent().getDataHelper();
    }

    @Override
    public void loadData() {
        addSubscribe(dataHelper.loginCode("13752596565")
                .compose(MyRxUtils.toMain(Schedulers.io()))
                .subscribeWith(new MySubscriber<HttpNoResult>(baseView, true) {
                    @Override
                    public void onNext(HttpNoResult httpNoResult) {
                        baseView.showTipMsg("加载数据");
                    }
                }));
    }
}