package com.bawei.lx_basedemo3.Test.view;

import com.bawei.lx_basedemo3.Test.bean.TestBean;
import com.bawei.lx_basedemo3.base.mvp.view.IBaseView;

/**
 * Created by Zhang on 2017/12/21.
 */

public interface ITestView extends IBaseView{

    void ShowData(TestBean testBean);
    void ShowError(String e);
}
