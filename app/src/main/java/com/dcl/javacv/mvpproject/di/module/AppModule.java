package com.dcl.javacv.mvpproject.di.module;

import com.dcl.javacv.mvpproject.MyApplication;

import dagger.Module;

/**
 * Created by dengchunlin on 2018/3/12.
 */
@Module
public class AppModule {
    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }
}
