package com.dcl.javacv.baidu.base;

/**
 * Created by dengchunlin on 2018/3/12.
 * 不带mvp的presenter的基类
 */
public interface BasePresenter<T extends BaseView> {
    /**
     * 绑定view
     *
     * @param baseView
     */
    void attachView(T baseView);

    /**
     * 在ondestory方法里面解除绑定的方法，用来保证P层的生命周期和V层同步，避免了，当V层销毁的时候，P层仍然存在造成的内存泄漏
     */
    void detachView();
}
