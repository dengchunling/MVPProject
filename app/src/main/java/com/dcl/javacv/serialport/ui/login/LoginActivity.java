package com.dcl.javacv.serialport.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dcl.javacv.serialport.R;
import com.dcl.javacv.serialport.base.BaseMvpActivity;
import com.dcl.javacv.serialport.contract.LoginContract;
import com.dcl.javacv.serialport.presenter.LoginPresenter;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/13.
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.IView {
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        super.initData();
        initToolbar(true, true, false).setTitles("登陆");
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        basePresenter.login("dcl", "123456");
    }

    public static void startIntentLoginActivity(Context context){
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

}
