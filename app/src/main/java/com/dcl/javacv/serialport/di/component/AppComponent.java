package com.dcl.javacv.serialport.di.component;

import com.dcl.javacv.serialport.di.module.AppModule;
import com.dcl.javacv.serialport.di.module.HttpModule;
import com.dcl.javacv.serialport.model.DataHelper;

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
