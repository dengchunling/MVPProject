package com.dcl.javacv.mvpproject.presenter;

import com.dcl.javacv.mvpproject.base.BaseMvpPresenter;
import com.dcl.javacv.mvpproject.contact.MainContract;

import javax.inject.Inject;

/**
 * Created by dengchunlin on 2018/3/12.
 */

public class MainPresenter extends BaseMvpPresenter<MainContract.IView> implements MainContract.Presenter {
    @Inject
    MainPresenter() {
    }

    @Override
    public void loadData() {
        baseView.showTipMsg("加载数据");
    }
}