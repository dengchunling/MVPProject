package com.dcl.javacv.mvpproject.contact;

import com.dcl.javacv.mvpproject.base.BasePresenter;
import com.dcl.javacv.mvpproject.base.BaseView;

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
