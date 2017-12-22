package com.bawei.lx_basedemo3.api;

import com.bawei.lx_basedemo3.Test.bean.TestBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Zhang on 2017/12/21.
 */

public interface ApiService {
    @GET("AppNews/getNewsList/type/1/p/1")
    Observable<TestBean> getdata();
}
