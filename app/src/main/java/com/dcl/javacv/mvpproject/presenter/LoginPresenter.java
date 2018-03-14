package com.dcl.javacv.mvpproject.presenter;

import com.dcl.javacv.mvpproject.MyApplication;
import com.dcl.javacv.mvpproject.base.BaseMvpPresenter;
import com.dcl.javacv.mvpproject.contact.LoginContract;
import com.dcl.javacv.mvpproject.model.DataHelper;
import com.dcl.javacv.mvpproject.model.bean.Login;
import com.dcl.javacv.mvpproject.model.http.MyRxUtils;
import com.dcl.javacv.mvpproject.model.http.MySubscriber;

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

    }
}
