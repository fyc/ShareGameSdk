package com.jiyou.sdklibrary.tools;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ParamHelper {
    public static String Key = "NSHDK-GHHJK-RFV7B-WCVY9"; //注意这个是假key,忽悠

    private ParamHelper() {

    }

    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        public static final ParamHelper INSTANCE = new ParamHelper();
    }

    public static ParamHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /*公共参数*/
    public static SortedMap<String, String> mapParam() {
        SortedMap<String, String> paramObj = new TreeMap<String, String>();
        paramObj.put("game_id", PlatformConfig.getInstance().getData("JY_GAMEID", "1"));//来自配置文件
        paramObj.put("game_pkg", PlatformConfig.getInstance().getData("JY_GAME_PKG", "csyx_csaz_B"));//来自配置文件
        paramObj.put("partner_id", PlatformConfig.getInstance().getData("JY_PARTNERID", "1"));
        paramObj.put("ad_code", PlatformConfig.getInstance().getData("JY_CHANNEL_ID", "0"));//来自配置文件
        paramObj.put("build_ver", String.valueOf(android.os.Build.VERSION.SDK_INT));
        paramObj.put("uuid", DeviceUtil.getImei());
        paramObj.put("idfv", "");
        paramObj.put("dname", DeviceUtil.getDeviceName());
        paramObj.put("mac", DeviceUtil.getMac());
        paramObj.put("net_type", DeviceUtil.getNetWork());
        paramObj.put("os_ver", android.os.Build.VERSION.RELEASE);
        paramObj.put("sdk_ver", String.valueOf(android.os.Build.VERSION.SDK_INT));
        paramObj.put("game_ver", DeviceUtil.getVersionCode());
        paramObj.put("time", System.currentTimeMillis() + "");
        paramObj.put("netserver", DeviceUtil.getWifiName());

//        paramObj.put("onekey", DeviceUtil.getOneDeviceId());
//        paramObj.put("and_id", DeviceUtil.getAndroid_Id());

//        paramObj.put("sysrom", DeviceUtil.getSystemRom());

//        paramObj.put("sdk_ver", PlatformConfig.getInstance().getData("frame_version", AkSDKConfig.SDK_VER));
//        paramObj.put("device", "1");
//        paramObj.put("frame_ver", PlatformConfig.getInstance().getData("frame_version", AkSDKConfig.SDK_VER));
//        paramObj.put("plugin_ver", PlatformConfig.getInstance().getData("plugin_version", "0"));
//        paramObj.put("third_ver", PlatformConfig.getInstance().getData("third_version", "0"));

        return paramObj;
    }

    public static String createSign(String characterEncoding, SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();

            if ("".equals(sb.toString())) {
                sb.append(k + "=" + v);
            } else {
                if (null != v && !"sign".equals(k)) {
                    sb.append("&" + k + "=" + v);
                }
            }
        }
        sb.append(Key);
        String sign = MD5Util.getMd5(sb.toString(), characterEncoding).toLowerCase();
        return sign;
    }
}