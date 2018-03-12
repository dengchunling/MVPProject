package com.dcl.javacv.mvpproject;

import android.app.Application;

import com.dcl.javacv.mvpproject.di.component.AppComponent;
import com.dcl.javacv.mvpproject.di.component.DaggerAppComponent;
import com.dcl.javacv.mvpproject.di.module.AppModule;
import com.dcl.javacv.mvpproject.di.module.HttpModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2018/3/12.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private static AppComponent appComponent;

    public static MyApplication getInstance() {
        return instance;
    }

    public static void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        initLeakCanary();
    }

    /**
     * 初始化内存检测工具
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 获取AppComponent.
     *
     * @return AppComponent
     */
    public static synchronized AppComponent getAppComponent() {
        if (null == appComponent) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(getInstance()))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
