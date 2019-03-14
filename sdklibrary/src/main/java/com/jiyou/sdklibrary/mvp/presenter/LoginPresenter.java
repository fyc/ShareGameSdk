package com.jiyou.sdklibrary.mvp.presenter;

import android.content.Context;

import com.jiyou.sdklibrary.base.BasePresenter;
import com.jiyou.sdklibrary.mvp.model.JYSdkLoginRequestData;
import com.jiyou.sdklibrary.mvp.view.MVPLoginView;

/**
 * Created by tzw on 2018/6/5.
 * 登录Presenter
 */

public interface LoginPresenter extends BasePresenter<MVPLoginView> {
    void login(JYSdkLoginRequestData user , Context context) ;
}
