package com.dcl.javacv.serialport.di.module;

import com.dcl.javacv.serialport.MyApplication;
import com.dcl.javacv.serialport.model.DataHelper;
import com.dcl.javacv.serialport.model.http.HttpHelper;
import com.dcl.javacv.serialport.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dengchunlin on 2018/3/12.
 */
@Module
public class AppModule {
    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    DataHelper provideDataHelper(HttpHelper httpHelper) {
        return new DataHelper(httpHelper);
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }
}
