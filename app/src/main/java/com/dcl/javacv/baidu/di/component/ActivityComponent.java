package com.dcl.javacv.baidu.di.component;

import com.dcl.javacv.baidu.ui.BaiduActivity;
import com.dcl.javacv.baidu.ui.MainActivity;
import com.dcl.javacv.baidu.di.module.ActivityModule;
import com.dcl.javacv.baidu.di.scope.ActivityScope;
import com.dcl.javacv.baidu.ui.login.LoginActivity;

import dagger.Component;

/**
 * Created by dengchunlin on 2018/3/12.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(BaiduActivity baiduActivity);
}
