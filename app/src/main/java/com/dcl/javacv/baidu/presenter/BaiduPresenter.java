package com.dcl.javacv.baidu.presenter;

import com.dcl.javacv.baidu.base.BaseMvpPresenter;
import com.dcl.javacv.baidu.contract.BaiduContract;

import javax.inject.Inject;

/**
 * Created by dengchunlin on 2018/4/16.
 */

public class BaiduPresenter extends BaseMvpPresenter<BaiduContract.IView> implements BaiduContract.Presenter {

    @Inject
    public BaiduPresenter() {
    }
}
