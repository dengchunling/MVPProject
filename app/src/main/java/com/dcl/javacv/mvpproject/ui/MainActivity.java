package com.dcl.javacv.mvpproject.ui;

import android.os.Bundle;

import com.dcl.javacv.mvpproject.R;
import com.dcl.javacv.mvpproject.base.BaseMvpActivity;
import com.dcl.javacv.mvpproject.contact.MainContract;
import com.dcl.javacv.mvpproject.presenter.MainPresenter;
import com.dcl.javacv.mvpproject.ui.login.LoginActivity;

import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.IView {

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        initToolbar(true, false, true).setMyTitle("主页").setMoreTitle("更多");
        basePresenter.loadData();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.go_login)
    public void onViewClicked() {
        LoginActivity.startIntentLoginActivity(this);
    }
}