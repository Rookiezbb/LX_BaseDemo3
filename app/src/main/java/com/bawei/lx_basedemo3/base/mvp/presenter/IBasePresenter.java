package com.bawei.lx_basedemo3.base.mvp.presenter;

/**
 * Created by Zhang on 2017/12/21.
 */

public interface IBasePresenter<V>{

    void attachView(V v);
    void deattachView();
}
