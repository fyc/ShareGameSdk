package com.jiyou.sdklibrary.tools;

public class PlatformConfig extends DataTemplate {
    private static PlatformConfig _instance = null;
    private static byte[] lock = new byte[0];

    public static PlatformConfig getInstance() {
        if (_instance == null) {
            synchronized (lock) {
               if(_instance == null) {
                   _instance = new PlatformConfig();
                   init();
               }
            }
        }
        return _instance;
    }

    public static void init() {
        PlatformConfig.getInstance().setData("JY_PARTNERID","1");
        PlatformConfig.getInstance().setData("JY_URL","https://sdk.7yol.cn/");
        PlatformConfig.getInstance().setData("LOGINJAR","cc.dkmproxy.simpleAct.plugin.simpleUserPlugin");
        PlatformConfig.getInstance().setData("PAYJAR","cc.dkmproxy.simpleAct.plugin.simplePayPlugin");
        PlatformConfig.getInstance().setData("orientation","1");
    }
}
