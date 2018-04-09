package com.dcl.javacv.serialport.presenter;

import android.Manifest;
import android.app.Activity;

import com.dcl.javacv.serialport.MyApplication;
import com.dcl.javacv.serialport.base.BaseMvpPresenter;
import com.dcl.javacv.serialport.contract.LoginContract;
import com.dcl.javacv.serialport.model.DataHelper;
import com.dcl.javacv.serialport.model.bean.Login;
import com.dcl.javacv.serialport.model.http.MyRxUtils;
import com.dcl.javacv.serialport.model.http.MySubscriber;
import com.dcl.javacv.serialport.utils.RxPerUtils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/13.
 */

public class LoginPresenter extends BaseMvpPresenter<LoginContract.IView> implements LoginContract.Presenter {

    private DataHelper dataHelper;

    @Inject
    public LoginPresenter() {
        dataHelper = MyApplication.getAppComponent().getDataHelper();
    }

    @Override
    public void login(String username, String password) {
        addSubscribe(dataHelper.login(username, password)
                .compose(MyRxUtils.handResult())
                .subscribeWith(new MySubscriber<Login>(baseView, true) {

                    @Override
                    public void onNext(Login login) {
                        baseView.showTipMsg("登陆成功");
                    }
                }));
        requestPermiss();
    }

    @Override
    public void requestPermiss() {
        RxPerUtils.requestPermission((Activity) baseView.getMyContext(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, o -> {
            boolean it= (boolean) o;
            if (it) {
                //这里写你自己的逻辑，已经获得权限，做你自己的业务逻辑操作
                baseView.showTipMsg("已经获得权限");
            } else {
                RxPerUtils.setupPermission((Activity) baseView.getMyContext(), "相机和存储",
                        "相机功能", () -> null);
            }
            return null;
        });
    }
}
