package com.dcl.javacv.mvpproject.di.component;

import com.dcl.javacv.mvpproject.ui.MainActivity;
import com.dcl.javacv.mvpproject.di.module.ActivityModule;
import com.dcl.javacv.mvpproject.di.scope.ActivityScope;
import com.dcl.javacv.mvpproject.ui.login.LoginActivity;

import dagger.Component;

/**
 * Created by dengchunlin on 2018/3/12.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
}
