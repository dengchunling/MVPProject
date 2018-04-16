package com.dcl.javacv.baidu.utils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by dengchunlin on 2018/4/16.
 */

public class RxBus {

    private final Subject<Object> mBus;

    private RxBus() {
        this.mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        return RxBus.Holder.INSTANCE;
    }

    public void post(@NonNull Object obj) {
        this.mBus.onNext(obj);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return this.mBus.ofType(eventType);
    }

    private static class Holder {
        private static RxBus INSTANCE = new RxBus();

        private Holder() {
        }
    }
}
