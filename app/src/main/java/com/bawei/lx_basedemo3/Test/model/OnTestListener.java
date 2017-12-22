package com.bawei.lx_basedemo3.Test.model;

import com.bawei.lx_basedemo3.Test.bean.TestBean;

/**
 * Created by Zhang on 2017/12/21.
 */

public interface OnTestListener {
    void OnSuccess(TestBean testBean);
    void OnError(String e);
}
