package com.bawei.lx_basedemo3.Test.model;

import com.bawei.lx_basedemo3.Test.bean.TestBean;
import com.bawei.lx_basedemo3.api.Api;
import com.bawei.lx_basedemo3.utils.RetroFactory;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhang on 2017/12/21.
 */

public class TestModel implements ITestModel {

    @Inject
    public TestModel() {
    }

    @Override
    public void RequestData(final OnTestListener onTestListener) {
        Observable<TestBean> getdata = RetroFactory.build(Api.HOST).getdata();
        getdata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TestBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onTestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(TestBean testBean) {
                        onTestListener.OnSuccess(testBean);
                    }
                });
    }
}
