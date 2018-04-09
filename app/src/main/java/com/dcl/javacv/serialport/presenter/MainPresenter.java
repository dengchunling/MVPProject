package com.dcl.javacv.serialport.presenter;

import com.dcl.javacv.serialport.MyApplication;
import com.dcl.javacv.serialport.base.BaseMvpPresenter;
import com.dcl.javacv.serialport.contract.MainContract;
import com.dcl.javacv.serialport.model.DataHelper;
import com.dcl.javacv.serialport.model.http.HttpNoResult;
import com.dcl.javacv.serialport.model.http.MyRxUtils;
import com.dcl.javacv.serialport.model.http.MySubscriber;

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