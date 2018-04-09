package com.dcl.javacv.serialport.presenter;

import com.dcl.javacv.serialport.MyApplication;
import com.dcl.javacv.serialport.base.BaseMvpPresenter;
import com.dcl.javacv.serialport.contract.SerialPortContract;
import com.dcl.javacv.serialport.model.DataHelper;

import javax.inject.Inject;

/**
 * Created by dengchunlin on 2018/4/8.
 */

public class SerialPortPresenter extends BaseMvpPresenter<SerialPortContract.IView> implements SerialPortContract.Presenter {
    private DataHelper dataHelper;

    @Inject
    public SerialPortPresenter() {
        dataHelper = MyApplication.getAppComponent().getDataHelper();
    }
}
