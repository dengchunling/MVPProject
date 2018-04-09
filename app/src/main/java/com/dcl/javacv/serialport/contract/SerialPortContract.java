package com.dcl.javacv.serialport.contract;

import com.dcl.javacv.serialport.base.BasePresenter;
import com.dcl.javacv.serialport.base.BaseView;

/**
 * Created by dengchunlin on 2018/4/8.
 */

public interface SerialPortContract {

    interface IView extends BaseView{

    }

    interface Presenter extends BasePresenter<IView>{

    }
}
