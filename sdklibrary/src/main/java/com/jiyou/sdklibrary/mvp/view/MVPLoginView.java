package com.jiyou.sdklibrary.mvp.view;

import com.jiyou.sdklibrary.base.BaseView;

/**
 * Created by tzw on 2018/6/5.
 * 登录
 */

public interface MVPLoginView extends BaseView {
    void loginSuccess(String msg,String data) ;
    void loginFailed(String msg,String data) ;
}
