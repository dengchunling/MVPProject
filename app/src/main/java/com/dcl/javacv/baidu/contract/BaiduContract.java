package com.dcl.javacv.baidu.contract;

import com.dcl.javacv.baidu.base.BasePresenter;
import com.dcl.javacv.baidu.base.BaseView;

/**
 * Created by dengchunlin on 2018/4/16.
 */

public interface BaiduContract {

    interface IView extends BaseView{

    }

    interface Presenter extends BasePresenter<IView>{

    }
}
