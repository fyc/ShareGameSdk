package com.jiyou.sdklibrary.config;

/**
 * Created by tzw on 2018/6/5.
 * URL_状态限定
 */

public final class HttpUrlConstants {

    public static final String COOKIE_DATA = "cookieData";


    //  请求成功，响应成功(!--接口成功--!)
    public static final String NET_OK = "netOK";
    //cookieData

    //  请求成功，响应失败(!--接口失败--!)
    public static final String NET_ON_FAILURE = "netFaiure";

    //------------!!!------------

    //  没有网络链接(!--客户端没有联网--!)
    public static final String NET_NO_LINKING = "请检查网络链接";

    //  后台服务器错误(!--服务器宕机--!)
    public static final String SERVER_ERROR = "啊哦~服务器去月球了";

    private static String SDK_BASE_HOST1 = "https://sdk.7yol.cn";
    private static String SDK_BASE_HOST = SDK_BASE_HOST1;

    public static String URL_SDK_INIT = SDK_BASE_HOST + "/?method=user.init"; //初始化接口
    public static String URL_SDK_LOGIN = SDK_BASE_HOST + "/?method=user.login";//帐号登录
    public static String URL_SDK_REG = SDK_BASE_HOST + "/?method=user.userReg";//帐号注册

}
