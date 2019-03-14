package com.jiyou.sdklibrary.mvp.Imp;

import android.content.Context;

import com.jiyou.sdklibrary.base.BaseView;
import com.jiyou.sdklibrary.callback.SdkCallbackListener;
import com.jiyou.sdklibrary.config.HttpUrlConstants;
import com.jiyou.sdklibrary.config.SDKStatusCode;
import com.jiyou.sdklibrary.mvp.model.JYSdkInitBean;
import com.jiyou.sdklibrary.mvp.presenter.InitPresenter;
import com.jiyou.sdklibrary.tools.DeviceUtil;
import com.jiyou.sdklibrary.tools.GsonUtils;
import com.jiyou.sdklibrary.tools.HttpRequestUtil;
import com.jiyou.sdklibrary.tools.ParamHelper;
import com.jiyou.sdklibrary.tools.SPDataUtils;

import java.io.IOException;
import java.util.SortedMap;

public class InitPresenterImp implements InitPresenter {
    @Override
    public void init(Context context, final SdkCallbackListener sdkCallbackListener) {
        SortedMap<String, String> Param = ParamHelper.mapParam();
        Param.put("vCode", DeviceUtil.getVersionCode());
        Param.put("vName", DeviceUtil.getVersionName());
        String iFirstInstall = SPDataUtils.getInstance().getString(SPDataUtils.IS_SDK_FIRST_INSTALL, "1");
        Param.put("install", iFirstInstall);
        String sign = ParamHelper.createSign("UTF-8", Param);
        Param.put("sign", sign);

        HttpRequestUtil.okPostFormRequest(HttpUrlConstants.URL_SDK_INIT, Param, new HttpRequestUtil.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                //初始化回调后设置为非首次安装
                SPDataUtils.getInstance().getString(SPDataUtils.IS_SDK_FIRST_INSTALL, "0");
                JYSdkInitBean initBean = GsonUtils.GsonToBean(result, JYSdkInitBean.class);

                int state = initBean.getState();

                if (state == 1) {
                    sdkCallbackListener.callback(SDKStatusCode.SUCCESS, "初始化成功");

                } else {
                    sdkCallbackListener.callback(SDKStatusCode.FAILURE, initBean.getMessage());
                }
            }

            @Override
            public void requestFailure(String request, IOException e) {
            }

            @Override
            public void requestNoConnect(String msg, String data) {
            }
        });
    }

    @Override
    public void attachView(BaseView baseView) {

    }

    @Override
    public void detachView() {

    }
}
