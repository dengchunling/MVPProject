package com.dcl.javacv.baidu.model.http;

import com.dcl.javacv.baidu.model.HttpCode;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/13.
 * 切换线程的工具类
 */

public class MyRxUtils {
    /**
     * 从其他线程转到主线程.
     *
     * @param scheduler Schedulers.io()等等
     * @param <T>       t
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> toMain(final Scheduler scheduler) {
        return upstream -> upstream.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<HttpResult<T>, T> handResult() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<HttpResult<T>, Publisher<T>>) tHttpResult -> {
                    if (tHttpResult.getCode() == HttpCode.SUCCESS) {
                        return createData(tHttpResult.getData());/*Flowable.just(tHttpResult.getData());*/
                    } else {
                        return Flowable.error(new ApiException(tHttpResult.getCode(), tHttpResult.getMsg()));
                    }
                });
    }

    private static <T> Flowable<T> createData(final T data) {
        return Flowable.create(e -> {
            e.onNext(data);
            e.onComplete();
        },BackpressureStrategy.ERROR);
    }
}
