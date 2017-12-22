package com.bawei.lx_basedemo3.base.mvp.presenter;

import com.bawei.lx_basedemo3.base.mvp.model.IBaseModel;
import com.bawei.lx_basedemo3.base.mvp.view.IBaseView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by Zhang on 2017/12/21.
 */

public class BasePresenter<V extends IBaseView, M extends IBaseModel> implements IBasePresenter<V> {

    @Inject
    protected  M model;

    private WeakReference<V> vWeakReference;
    protected V mview;

    @Override
    public void attachView(V v) {
        vWeakReference = new WeakReference<V>(v);
        mview = vWeakReference.get();
    }

    @Override
    public void deattachView() {

        if (vWeakReference != null) {
            vWeakReference.clear();
            mview = null;
            vWeakReference = null;
        }
    }
}
