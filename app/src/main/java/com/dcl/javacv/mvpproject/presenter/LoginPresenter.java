package com.dcl.javacv.mvpproject.presenter;

import com.dcl.javacv.mvpproject.base.BaseMvpPresenter;
import com.dcl.javacv.mvpproject.contact.LoginContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/3/13.
 */

public class LoginPresenter extends BaseMvpPresenter<LoginContract.IView> implements LoginContract.Presenter {
    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(String username, String password) {
        baseView.showTipMsg("登陆成功");
    }
}
