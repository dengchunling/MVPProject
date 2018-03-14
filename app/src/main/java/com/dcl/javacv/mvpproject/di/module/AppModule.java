package com.dcl.javacv.mvpproject.di.module;

import com.dcl.javacv.mvpproject.MyApplication;
import com.dcl.javacv.mvpproject.model.DataHelper;
import com.dcl.javacv.mvpproject.model.http.HttpHelper;
import com.dcl.javacv.mvpproject.model.http.RetrofitHelper;

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
