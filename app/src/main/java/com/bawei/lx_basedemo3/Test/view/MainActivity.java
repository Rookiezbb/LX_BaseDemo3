package com.bawei.lx_basedemo3.Test.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.lx_basedemo3.R;
import com.bawei.lx_basedemo3.Test.bean.TestBean;
import com.bawei.lx_basedemo3.Test.presenter.TestPresenter;
import com.bawei.lx_basedemo3.base.mvp.BasemvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BasemvpActivity<TestPresenter> implements ITestView {


    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void getInjet() {
        getComponent().Inject(this);
    }

    @Override
    protected void initdata() {
        presenter.LoadData();
    }

    @Override
    protected void initview() {

    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "加载呢", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "加载完了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowData(TestBean testBean) {
        tv.setText(testBean.getData().toString());
    }

    @Override
    public void ShowError(String e) {
        Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
    }


}
