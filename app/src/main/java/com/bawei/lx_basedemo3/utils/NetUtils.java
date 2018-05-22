package com.bawei.lx_basedemo3.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;


import java.util.Map;
import java.util.TreeMap;

/**
 * 网络工具类
 * Created by yangwei on 2017/11/6.
 */

public class NetUtils {

    public static final String DEVICEID = "device_id";
    private static SharedPreferences sp;
    public static final String USER_TOKEN = "user_token";
    /**
     * 对网络连接状态进行判断
     *
     * @return true, 可用； false， 不可用
     */
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }

        return false;

    }

    /**
     * 获取网络请求体中的header部分和h5页面的通用参数
     *
     * @param context
     * @return
     */
    public static Map<String, String> getRequestHeader(Context context) {
        TreeMap headerMap = new TreeMap();
        headerMap.put("appType", "android");
        headerMap.put("appNo", getShareStringData(DEVICEID));
        headerMap.put("appVersion", DeviceUtils.getCurrentAppVersionName(context));
        headerMap.put("token", getShareStringData(USER_TOKEN));

        return headerMap;
    }

    public static String getShareStringData(String key) {
        return sp.getString(key, "");
    }
}
