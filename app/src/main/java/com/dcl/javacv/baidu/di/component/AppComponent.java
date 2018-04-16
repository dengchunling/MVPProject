package com.dcl.javacv.baidu.di.component;

import com.dcl.javacv.baidu.di.module.AppModule;
import com.dcl.javacv.baidu.di.module.HttpModule;
import com.dcl.javacv.baidu.model.DataHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dengchunlin on 2018/3/12.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface  AppComponent {
    DataHelper getDataHelper();
}
