package com.bawei.lx_basedemo3.utils;

import com.bawei.lx_basedemo3.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fan on 2017/11/8.
 */

public class RetroFactory {

    public static ApiService build(String path) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                //可自定义拦截器
                // .addInterceptor(new LoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        ApiService retrofitService = new Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(ApiService.class);
        return retrofitService;
    }


}
