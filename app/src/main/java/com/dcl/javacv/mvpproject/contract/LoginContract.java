package com.dcl.javacv.mvpproject.contract;

import com.dcl.javacv.mvpproject.base.BasePresenter;
import com.dcl.javacv.mvpproject.base.BaseView;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface LoginContract {
    interface IView extends BaseView{

    }

    interface Presenter extends BasePresenter<IView>{
        void login(String username,String password);
        void requestPermiss();
    }
}
