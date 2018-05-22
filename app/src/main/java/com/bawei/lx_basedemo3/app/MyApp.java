package com.bawei.lx_basedemo3.app;

import android.app.Application;

/**
 * Created by Zhang on 2018/5/22.
 */

public class MyApp extends Application{
    public static MyApp myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApp getApplication() {
        return myApplication;
    }
}
