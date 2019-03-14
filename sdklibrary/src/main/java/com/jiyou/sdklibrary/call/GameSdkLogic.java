package com.jiyou.sdklibrary.call;

import android.content.Context;
import android.content.Intent;

import com.jiyou.sdklibrary.callback.SdkCallbackListener;
import com.jiyou.sdklibrary.config.ConstData;
import com.jiyou.sdklibrary.config.HttpUrlConstants;
import com.jiyou.sdklibrary.config.SDKStatusCode;
import com.jiyou.sdklibrary.mvp.model.JYSdkInitBean;
import com.jiyou.sdklibrary.mvp.model.MVPPayBean;
import com.jiyou.sdklibrary.mvp.model.MVPPlayerBean;
import com.jiyou.sdklibrary.tools.GsonUtils;
import com.jiyou.sdklibrary.tools.HttpRequestUtil;
import com.jiyou.sdklibrary.tools.LoggerUtils;
import com.jiyou.sdklibrary.tools.ParamHelper;
import com.jiyou.sdklibrary.tools.SPDataUtils;
import com.jiyou.sdklibrary.ui.SdkLoginActivity;
import com.jiyou.sdklibrary.ui.SdkPayActivity;

import java.io.IOException;
import java.util.SortedMap;

/**
 * Created by tzw on 2018/6/5.
 * 供接入使用SDK开发人员调用的核心类
 */

public class GameSdkLogic {
    private boolean checkInit;

    private GameSdkLogic(){ }

    private volatile static GameSdkLogic sdkLogic;

    public static GameSdkLogic getInstance(){
        if (sdkLogic == null){
            synchronized (GameSdkLogic.class){
                if (sdkLogic == null){
                    sdkLogic = new GameSdkLogic();
                }
            }
        }
        return sdkLogic;
    }

    //游戏初始化:
    //这里没有商业接口,固定是初始化成功,实际开发需要根据后台去判断成功/失败
    //只有当初始化的时候才可以进行后续操作
    public void sdkInit(Context context,final Object o, final SdkCallbackListener<String> callback){
//        callback.callback(SDKStatusCode.SUCCESS, "初始化成功");
        SortedMap<String, String> Param = ParamHelper.mapParam();
//        Param.put("vCode", DeviceUtil.getVersionCode());
//        Param.put("vName", DeviceUtil.getVersionName());
        String iFirstInstall = SPDataUtils.getInstance().getString(SPDataUtils.IS_SDK_FIRST_INSTALL,"1");
        Param.put("install", iFirstInstall);
        String sign = ParamHelper.createSign("UTF-8", Param);
        Param.put("sign", sign);

        HttpRequestUtil.okPostFormRequest(HttpUrlConstants.URL_SDK_INIT, Param, new HttpRequestUtil.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
//                LoggerUtils.i(LogTAG.login,"responseBody:"+result);
                //初始化回调后设置为非首次安装
                SPDataUtils.getInstance().savaString(SPDataUtils.IS_SDK_FIRST_INSTALL,"0");
                checkInit = true;
                JYSdkInitBean initBean = GsonUtils.GsonToBean(result, JYSdkInitBean.class);

                int state =  initBean.getState();

                if (state == 1){
                    callback.callback(SDKStatusCode.SUCCESS, "初始化成功");

                }else {
                    callback.callback(SDKStatusCode.FAILURE, initBean.getMessage());
                }
            }

            @Override
            public void requestFailure(String request, IOException e) {
                callback.callback(SDKStatusCode.FAILURE, e.toString());
            }

            @Override
            public void requestNoConnect(String msg, String data) {
                callback.callback(SDKStatusCode.FAILURE, data);
            }
        });
    }

    //登录:
    //理论上初始化成功才可以登录 这里的接口使用的是 玩Android 开放接口
    public void sdkLogin(Context context, final SdkCallbackListener<String> callback){
        LoggerUtils.i("SdkLogic Login");
        if (checkInit){
            Intent intent = new Intent(context, SdkLoginActivity.class);
            context.startActivity(intent);
            Delegate.listener = callback;
        }else {
            callback.callback(SDKStatusCode.FAILURE, ConstData.INIT_FAILURE);
            return;
        }

    }

    //支付:
    //需要将SDK支付信息传递给具体的方式中
    public void sdkPay(Context context, MVPPayBean bean,final SdkCallbackListener<String> callback){
        LoggerUtils.i("SdkLogic Pay");
        if (checkInit){
            Intent intent = new Intent(context, SdkPayActivity.class);
            context.startActivity(intent);
            Delegate.listener = callback;
        }else {
            callback.callback(SDKStatusCode.FAILURE, ConstData.INIT_FAILURE);
            return;
        }
    }


    //提交游戏信息：
    public void subGameInfoMethod(MVPPlayerBean bean){
        //doing something:
        LoggerUtils.i("submit Player Information");
        //step:
        //Build HttpRequest   ----> server get Request ------->server return ResponseBody

        //This function is mainly used to record and count player information
    }


}
