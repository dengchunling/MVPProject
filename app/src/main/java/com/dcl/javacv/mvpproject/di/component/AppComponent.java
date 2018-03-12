package com.dcl.javacv.mvpproject.di.component;

import com.dcl.javacv.mvpproject.di.module.AppModule;
import com.dcl.javacv.mvpproject.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dengchunlin on 2018/3/12.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface  AppComponent {
}
