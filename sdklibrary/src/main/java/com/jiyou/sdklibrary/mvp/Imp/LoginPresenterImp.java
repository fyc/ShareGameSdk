package com.jiyou.sdklibrary.mvp.Imp;

import android.content.Context;
import android.text.TextUtils;

import com.jiyou.sdklibrary.config.ConstData;
import com.jiyou.sdklibrary.config.HttpUrlConstants;
import com.jiyou.sdklibrary.config.LogTAG;
import com.jiyou.sdklibrary.mvp.model.JYSdkLoginBean;
import com.jiyou.sdklibrary.mvp.model.JYSdkLoginRequestData;
import com.jiyou.sdklibrary.mvp.presenter.LoginPresenter;
import com.jiyou.sdklibrary.mvp.view.MVPLoginView;
import com.jiyou.sdklibrary.tools.GsonUtils;
import com.jiyou.sdklibrary.tools.HttpRequestUtil;
import com.jiyou.sdklibrary.tools.LoggerUtils;
import com.jiyou.sdklibrary.tools.MD5Util;
import com.jiyou.sdklibrary.tools.ParamHelper;

import java.io.IOException;
import java.util.SortedMap;


/**
 * Created by tzw on 2018/6/5.
 * 登录逻辑类 请求---响应判断---
 * 通过MVPLoginView将结果回调出去给View
 *
 */

public class LoginPresenterImp  implements LoginPresenter {

    private String userName;
    private String passWord;
    private MVPLoginView mvpLoginView;

    @Override
    public void attachView(MVPLoginView mvpLoginView) {
        this.mvpLoginView = mvpLoginView;
    }

    @Override
    public void login(JYSdkLoginRequestData user, Context context) {
        userName = user.getUserName().toString().trim();
        passWord = user.getPassWord().toString().trim();

        if ((!TextUtils.isEmpty(userName)) && (!TextUtils.isEmpty(passWord))) {
            loginMethod(HttpUrlConstants.URL_SDK_LOGIN,userName,passWord );
        } else {
            mvpLoginView.showAppInfo("","帐号或密码输入为空");
        }

    }
    //测试登录账号：testN6mn1tnm a111111
    private void loginMethod(String url,String userName,String passWord){
//        Map<String,String> map = new HashMap<>();
//        map.put("username",userName);
//        map.put("password",passWord);


        SortedMap<String, String> Param = ParamHelper.mapParam();
        String passwordMd5 = MD5Util.encode(passWord).toLowerCase();
        Param.put("username", userName);
        Param.put("password", passwordMd5);

        String sign = ParamHelper.createSign("UTF-8", Param);
        Param.put("sign", sign);

        HttpRequestUtil.okPostFormRequest(url, Param, new HttpRequestUtil.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                LoggerUtils.i(LogTAG.login,"responseBody:"+result);
                JYSdkLoginBean loginBean = GsonUtils.GsonToBean(result, JYSdkLoginBean.class);

                int state =  loginBean.getState();
//                String msg = mvpLoginResultBean.getErrorMsg();

                if (state == 1){
                    mvpLoginView.loginSuccess(ConstData.LOGIN_SUCCESS,result);
//                    LoggerUtils.i(LogTAG.login,"responseBody: login Success");

                }else {
                    mvpLoginView.loginFailed(ConstData.LOGIN_FAILURE,loginBean.getMessage());
                    LoggerUtils.i(LogTAG.login,"responseBody: login Failure");
                }
            }

            @Override
            public void requestFailure(String request, IOException e) {
                mvpLoginView.loginFailed(HttpUrlConstants.SERVER_ERROR,HttpUrlConstants.SERVER_ERROR);
            }

            @Override
            public void requestNoConnect(String msg, String data) {
                mvpLoginView.loginFailed(HttpUrlConstants.NET_NO_LINKING,HttpUrlConstants.NET_NO_LINKING);
            }
        });
    }

    @Override
    public void detachView() {
        this.mvpLoginView = null;
    }
}
