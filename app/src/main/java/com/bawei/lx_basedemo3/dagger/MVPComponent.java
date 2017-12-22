package com.bawei.lx_basedemo3.dagger;

import com.bawei.lx_basedemo3.Test.view.MainActivity;

import dagger.Component;

/**
 * Created by Zhang on 2017/12/21.
 */
@Component
public interface MVPComponent {
    void Inject(MainActivity mainActivity);
}
