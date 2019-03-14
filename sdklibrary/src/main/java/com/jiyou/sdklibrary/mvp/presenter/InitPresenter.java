package com.jiyou.sdklibrary.mvp.presenter;

import android.content.Context;

import com.jiyou.sdklibrary.base.BasePresenter;
import com.jiyou.sdklibrary.base.BaseView;
import com.jiyou.sdklibrary.callback.SdkCallbackListener;

public interface InitPresenter extends BasePresenter<BaseView> {
    void init(Context context, SdkCallbackListener sdkCallbackListener) ;
}
