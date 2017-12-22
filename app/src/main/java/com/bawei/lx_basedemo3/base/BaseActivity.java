package com.bawei.lx_basedemo3.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Zhang on 2017/12/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        ButterKnife.bind(this);
        initview();
        initdata();
    }

    protected abstract void initdata();

    protected abstract void initview();

    protected abstract int getlayout();

}
