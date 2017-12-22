package com.bawei.lx_basedemo3.Test.presenter;

import android.view.View;

import com.bawei.lx_basedemo3.Test.bean.TestBean;
import com.bawei.lx_basedemo3.Test.model.ITestModel;
import com.bawei.lx_basedemo3.Test.model.OnTestListener;
import com.bawei.lx_basedemo3.Test.model.TestModel;
import com.bawei.lx_basedemo3.Test.view.ITestView;
import com.bawei.lx_basedemo3.base.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Zhang on 2017/12/21.
 */

public class TestPresenter extends BasePresenter<ITestView,TestModel> implements ITestPresenter {

    @Inject
    public TestPresenter() {
    }

    @Override
    public void LoadData() {
        mview.showLoading();
        model.RequestData(new OnTestListener() {
            @Override
            public void OnSuccess(TestBean testBean) {
                mview.ShowData(testBean);
            }

            @Override
            public void OnError(String e) {
                mview.ShowError(e);
            }
        });
        mview.hideLoading();
    }
}
