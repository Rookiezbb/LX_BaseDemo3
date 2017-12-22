package com.bawei.lx_basedemo3.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bawei.lx_basedemo3.base.BaseActivity;
import com.bawei.lx_basedemo3.base.mvp.presenter.BasePresenter;
import com.bawei.lx_basedemo3.base.mvp.view.IBaseView;
import com.bawei.lx_basedemo3.dagger.DaggerMVPComponent;
import com.bawei.lx_basedemo3.dagger.MVPComponent;

import javax.inject.Inject;

/**
 * Created by Zhang on 2017/12/21.
 */

public abstract class BasemvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseView {

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getInjet();
        presenter.attachView(this);
        super.onCreate(savedInstanceState);

    }

    protected MVPComponent getComponent(){

    return DaggerMVPComponent.builder().build();
    }
    protected abstract void getInjet();
}
