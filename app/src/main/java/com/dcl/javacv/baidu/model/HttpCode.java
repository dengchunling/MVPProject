package com.dcl.javacv.baidu.model;

/**
 * Created by Administrator on 2018/3/13.
 * 网络请求状态码
 */

public interface HttpCode {
    /**
     * 成功.
     */
    int SUCCESS = 0;
    /**
     * 参数为空.
     */
    int NO_PARAMETER = 1;
    /**
     * 服务器错误.
     */
    int SERVER_ERR = 3;
}
