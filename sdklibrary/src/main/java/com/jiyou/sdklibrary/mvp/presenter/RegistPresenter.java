package com.jiyou.sdklibrary.mvp.presenter;


import android.content.Context;

import com.jiyou.sdklibrary.base.BasePresenter;
import com.jiyou.sdklibrary.mvp.model.MVPRegisterBean;
import com.jiyou.sdklibrary.mvp.view.MVPRegistView;

/**
 * Created by tzw on 2018/6/5.
 * 注册Presenter
 */

public interface RegistPresenter extends BasePresenter<MVPRegistView> {
    void regist(MVPRegisterBean user, Context context) ;
}
