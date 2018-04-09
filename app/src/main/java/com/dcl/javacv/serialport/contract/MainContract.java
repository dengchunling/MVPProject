package com.dcl.javacv.serialport.contract;

import com.dcl.javacv.serialport.base.BasePresenter;
import com.dcl.javacv.serialport.base.BaseView;

/**
 * Created by dengchunlin on 2018/3/12.
 */

public interface MainContract {
    interface IView extends BaseView {

    }
    interface Presenter extends BasePresenter<IView> {
        void loadData();
    }
}
