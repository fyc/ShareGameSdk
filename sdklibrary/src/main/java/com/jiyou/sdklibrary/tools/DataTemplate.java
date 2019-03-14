package com.jiyou.sdklibrary.tools;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class DataTemplate {
    protected Map<String, String> map = null;
    protected Map<String, Map<String, String>> mapArray = null;

    public final void setData(String key, String value)
    {
        createMap();
        if (value != null)
            this.map.put(key, value);
    }

    public final String getData(String key, String defaultValue)
    {
        createMap();
        String str = defaultValue;
        try {
            str = this.map.get(key);
        }
        catch (Exception localException) {
        }
        return TextUtils.isEmpty(str) ? defaultValue : str;
    }

    protected final void createMap()
    {
        if (this.map == null)
            this.map = new HashMap();
    }

    public final Map<String, String> getMap()
    {
        if (this.map == null) {
            this.map = new HashMap();
        }
        return this.map;
    }

    public final void setMap(Map<String, String> map)
    {
        if (map == null) {
            map = new HashMap();
        }
        for (String key : map.keySet())
        {
            String value = map.get(key);
            setData(key, value);
        }
    }

    protected final void createMapArray(String sdkId)
    {
        if (this.mapArray == null) {
            this.mapArray = new HashMap();
        }
        if (sdkId == null) {
            return;
        }
        if (!this.mapArray.containsKey(sdkId)) {
            this.map = new HashMap();
            this.mapArray.put(sdkId, this.map);
        }
    }

    public final void setData(String sdkId, String key, String value)
    {
        createMapArray(sdkId);
        if (value != null) {
            Map map = this.mapArray.get(sdkId);
            map.put(key, value);
            this.mapArray.put(sdkId, map);
        }
    }

    public final String getData(String sdkId, String key, String defaultValue)
    {
        createMapArray(sdkId);
        String str = defaultValue;
        try {
            str = (String)((Map)this.mapArray.get(sdkId)).get(key);
        }
        catch (Exception localException) {
        }
        return TextUtils.isEmpty(str) ? defaultValue : str;
    }

    public final Map<String, String> getMap(String sdkId)
    {
        createMapArray(sdkId);
        Map map = null;
        try {
            map = this.mapArray.get(sdkId);
        }
        catch (Exception localException) {
        }
        return map;
    }

    public final void setMap(Map<String, String> map, String sdkId)
    {
        createMapArray(sdkId);
        if ((map == null) || (sdkId == null)) {
            return;
        }
        this.mapArray.put(sdkId, map);
    }

    public final Map<String, Map<String, String>> getMaps()
    {
        createMapArray(null);
        return this.mapArray;
    }
}
