package com.jiyou.sdklibrary.mvp.Imp;

import android.content.Context;
import android.text.TextUtils;

import com.jiyou.sdklibrary.config.ConstData;
import com.jiyou.sdklibrary.config.HttpUrlConstants;
import com.jiyou.sdklibrary.config.LogTAG;
import com.jiyou.sdklibrary.mvp.model.JYSdkRegistBean;
import com.jiyou.sdklibrary.mvp.model.JYSdkRegisterRequestData;
import com.jiyou.sdklibrary.mvp.presenter.RegistPresenter;
import com.jiyou.sdklibrary.mvp.view.MVPRegistView;
import com.jiyou.sdklibrary.tools.GsonUtils;
import com.jiyou.sdklibrary.tools.HttpRequestUtil;
import com.jiyou.sdklibrary.tools.LoggerUtils;
import com.jiyou.sdklibrary.tools.MD5Util;
import com.jiyou.sdklibrary.tools.ParamHelper;

import java.io.IOException;
import java.util.SortedMap;


/**
 * Created by tzw on 2018/6/5.
 * 注册逻辑类 请求---响应判断---接口回调
 */

public class RegistPresenterImp implements RegistPresenter {

    private String userName;
    private String passWord;

    private MVPRegistView mvpRegistView;

    @Override
    public void attachView(MVPRegistView mvpRegistView) {
        this.mvpRegistView = mvpRegistView;
    }

    @Override
    public void regist(JYSdkRegisterRequestData user, Context context) {
        userName = user.getUserName().toString().trim();
        passWord = user.getPassWord().toString().trim();

        if ((!TextUtils.isEmpty(userName)) && (!TextUtils.isEmpty(passWord))) {
            registMethod(HttpUrlConstants.URL_SDK_REG, userName, passWord);
        } else {
            mvpRegistView.showAppInfo("", "帐号或密码输入为空");
        }
    }

    private void registMethod(String url, String userName, String passWord) {
        SortedMap<String, String> Param = ParamHelper.mapParam();
        String passwordMd5 = MD5Util.encode(passWord).toLowerCase();
        Param.put("username", userName);
        Param.put("password", passwordMd5);

        String sign = ParamHelper.createSign("UTF-8", Param);
        Param.put("sign", sign);

        HttpRequestUtil.okPostFormRequest(url, Param, new HttpRequestUtil.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                LoggerUtils.i(LogTAG.login, "responseBody:" + result);

                JYSdkRegistBean registBean = GsonUtils.GsonToBean(result, JYSdkRegistBean.class);

                int state = registBean.getState();

                if (state == 1) {
                    mvpRegistView.registSuccess(ConstData.REGIST_SUCCESS, result);
                    LoggerUtils.i(LogTAG.register, "regist Success");

                } else {
                    mvpRegistView.registFailed(ConstData.REGIST_FAILURE, registBean.getMessage());
                    LoggerUtils.i(LogTAG.register, "regist Failure");
                }
            }

            @Override
            public void requestFailure(String request, IOException e) {
                mvpRegistView.registFailed(ConstData.REGIST_FAILURE, HttpUrlConstants.SERVER_ERROR);
            }

            @Override
            public void requestNoConnect(String msg, String data) {
                mvpRegistView.registFailed(ConstData.REGIST_FAILURE, HttpUrlConstants.NET_NO_LINKING);
            }
        });
    }

    @Override
    public void detachView() {
        this.mvpRegistView = null;
    }

}
