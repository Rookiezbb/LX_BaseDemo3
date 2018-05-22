package com.bawei.lx_basedemo3.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.lx_basedemo3.utils.CommonWebActivity;
import com.bawei.lx_basedemo3.view.top.TopBar;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;

import static com.bawei.lx_basedemo3.base.Keys.KEY_IS_POST_URL;
import static com.bawei.lx_basedemo3.base.Keys.KEY_PARAM_MAP;
import static com.bawei.lx_basedemo3.base.Keys.KEY_TOPBAR_TITLE;
import static com.bawei.lx_basedemo3.base.Keys.KEY_WEB_JSON;
import static com.bawei.lx_basedemo3.base.Keys.KEY_WEB_URL;

/**
 * Created by Zhang on 2017/12/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected TopBar topBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayout());
        ButterKnife.bind(this);
        dealLogicBeforeInitView();
        initview();
        dealLogicAfterInitView();
        initdata();
    }

    protected abstract void initdata();

    /**
     * 初始化控件之前需要处理的逻辑，例如接收传过来的数据
     */
    public abstract void dealLogicBeforeInitView();

    protected abstract void initview();

    /**
     * 初始化控件之后需要处理的业务，例如从网络或缓存加载数据
     */
    public abstract void dealLogicAfterInitView();

    protected abstract int getlayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    /***
     * 跳转到指定Activity页面
     *
     * @param clas 指定页面
     */
    public void goPage(Class<? extends Activity> clas) {
        goPage(clas, null);
    }

    /***
     * 跳转到指定Activity页面
     *
     * @param clas 指定页面
     * @param data 传入数据
     */
    protected void goPage(Class<? extends Activity> clas, Bundle data) {
        goPage(clas, data, -1);
    }

    /***
     * 跳转到指定Activity页面
     *
     * @param clas        指定页面
     * @param data        传入数据
     * @param requestCode 请求值
     */
    protected void goPage(Class<? extends Activity> clas, Bundle data, int requestCode) {
        if (clas == null) {
            return;
        }
        Intent intent = new Intent(this, clas);
        if (data != null) {
            intent.putExtra(Keys.KEY_DATA, data);
        }
        startActivityForResult(intent, requestCode);
    }

    /***
     * 跳转到Webview页面
     *
     * @param title 标题名称
     * @param url 网址
     */
    protected void goWebPage(String title, String url) {
        goWebPage(title, url, null);
    }

    /***
     * 跳转到Webview页面
     *
     * @param title 标题名称
     * @param url 网址
     * @param jsonObj 部分json参数
     */
    protected void goWebPage(String title, String url, JSONObject jsonObj) {
        Bundle bd = new Bundle();
        bd.putString(KEY_TOPBAR_TITLE, title);
        bd.putString(KEY_WEB_URL, url);
        if (jsonObj != null) bd.putString(KEY_WEB_JSON, jsonObj.toString());
        goPage(CommonWebActivity.class, bd);
    }

    /***
     * 跳转到Webview页面
     *
     * @param title 标题名称
     * @param url 网址
     * @param paramMap post需要的参数
     */
    protected void goWebPageForPost(int requestCode, String title, String url, HashMap<String, String> paramMap) {
        Bundle bd = new Bundle();
        bd.putString(KEY_TOPBAR_TITLE, title);
        bd.putString(KEY_WEB_URL, url);
        bd.putBoolean(KEY_IS_POST_URL, true);
        bd.putSerializable(KEY_PARAM_MAP, paramMap);
        goPage(CommonWebActivity.class, bd, requestCode);
    }

    /***
     * Activity跳转后 获取String传入值，如果为空返回空字符串
     *
     * @param key
     * @return
     */
    protected String getBundleStr(String key) {
        Bundle bundle = getIntent().getBundleExtra(Keys.KEY_DATA);
        if (bundle == null) {
            return "";
        }
        return bundle.getString(key, "");
    }

    /***
     * 获取intent传入值
     *
     * @return bundle
     */
    protected Bundle getIntentData() {
        Bundle bundle = getIntent().getBundleExtra(Keys.KEY_DATA);
        if (bundle == null) {
            bundle = new Bundle();
        }
        return bundle;
    }

    /**
     * 获取TextView中的文本信息
     *
     * @param tv
     * @return
     */
    protected String mGetTextViewContent(TextView tv) {
        return tv.getText().toString().trim();
    }

    /**
     * 获取EditText中的文本信息
     *
     * @param et
     * @return
     */
    protected String mGetEditTextContent(EditText et) {
        return et.getText().toString().trim();
    }
}
