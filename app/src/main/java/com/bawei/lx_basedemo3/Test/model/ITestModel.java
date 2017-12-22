package com.bawei.lx_basedemo3.Test.model;

import com.bawei.lx_basedemo3.base.mvp.model.IBaseModel;

/**
 * Created by Zhang on 2017/12/21.
 */

public interface ITestModel extends IBaseModel{
    void RequestData(OnTestListener onTestListener);
}
