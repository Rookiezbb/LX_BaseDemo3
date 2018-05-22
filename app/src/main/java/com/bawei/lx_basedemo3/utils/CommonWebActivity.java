package com.bawei.lx_basedemo3.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.bawei.lx_basedemo3.R;
import com.bawei.lx_basedemo3.base.BaseActivity;
import com.bawei.lx_basedemo3.view.webview.CustomWebView;
import com.bawei.lx_basedemo3.view.webview.WebViewLoadFinishListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bawei.lx_basedemo3.base.Keys.KEY_IS_POST_URL;
import static com.bawei.lx_basedemo3.base.Keys.KEY_PARAM_MAP;
import static com.bawei.lx_basedemo3.base.Keys.KEY_TOPBAR_TITLE;
import static com.bawei.lx_basedemo3.base.Keys.KEY_WEB_JSON;
import static com.bawei.lx_basedemo3.base.Keys.KEY_WEB_URL;


/**
 * Created by song on 2018/1/31 0030.
 * 通用的WebView Activity  与H5交互
 */
public class CommonWebActivity extends BaseActivity implements WebViewLoadFinishListener {
    //H5传唤来的action 根据action作相应的操作
    private final String ACTION_FINISH = "finish";

    @BindView(R.id.wv_common)
    CustomWebView wvCommon;

    private String url, title;
    private JSONObject jsonObject;
    //是否需要post url
    private boolean isPostUrl;
    private HashMap<String, String> paramMap;

    @Override
    protected void initdata() {

    }

    @Override
    protected void initview() {
        ButterKnife.bind(this);
        topBar.setTitle(title);
        topBar.setLineVisible(true);
        if(url.contains("commisionCalculation")) {
            topBar.setRightText("清空");
            topBar.setRightVisible(true);
        }
        wvCommon.getmWebView().addJavascriptInterface(new JSNotify(), "jsToJava");
        wvCommon.setTopBar(topBar);
        wvCommon.setWebViewLoadFinishListener(this);
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_common_web;
    }



    @Override
    public void dealLogicBeforeInitView() {
        WindowSoftModeAdjustResizeExecutor.assistActivity(this);
        url = getBundleStr(KEY_WEB_URL);
        title = getBundleStr(KEY_TOPBAR_TITLE);
        isPostUrl = getIntentData().getBoolean(KEY_IS_POST_URL, false);
        if (isPostUrl) {
            paramMap = (HashMap<String, String>) getIntentData().getSerializable(KEY_PARAM_MAP);
        } else {
            String json = getBundleStr(KEY_WEB_JSON);
            try {
                if (!TextUtils.isEmpty(json)) {
                    jsonObject = new JSONObject(json);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void dealLogicAfterInitView() {
        if (!isPostUrl) {
            wvCommon.getmWebView().loadUrl(url);
        } else {
            postUrl();
        }

    }


    private void postUrl() {
        StringBuffer result = new StringBuffer();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }
            result.append(entry.getKey());
            if (entry.getValue() != null) {
                result.append("=");
                result.append(entry.getValue());
            }
        }
        wvCommon.getmWebView().postUrl(url, result.toString().getBytes());
      //  LogUtils.e("JY", "postUrl---" + url + result.toString());
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onPageLoadFinish() {
        if (jsonObject != null) {
        //LogUtils.e(jsonObject + "");
        wvCommon.getmWebView().evaluateJavascript("jsCallbackMethodResult(" + jsonObject + ")", null);
        }
    }

    /*@Override
    public void OnTopLeftClick() {
        if (wvCommon.getmWebView().canGoBack()) {
            wvCommon.getmWebView().goBack();
            if (wvCommon.getTitles().size() > 1) {
                String title = wvCommon.getTitles().remove(wvCommon.getTitles().size() - 1);
                topBar.setTitle(title);
            }
        } else {
            finish();
        }
    }

    @Override
    public void OnTopRightClick() {
        if(url.contains("commisionCalculation")) {
            wvCommon.getmWebView().loadUrl("javascript:clear()");
        }
    }*/




    /**
     * android与js交互
     */
    public class JSNotify {
        /**
         * js回调方法，通过跟h5定协议 确定要做的操作
         *
         * @param json
         */
        @JavascriptInterface
        public void jsCallbackMethod(String json) {
            String type = GsonUtil.getStringFromJson(json, "type");
            //LogUtils.e("JY", json);
            switch (type) {
                case ACTION_FINISH:   //关闭页面

                    break;
                default:

                    break;
            }
        }


    }
}
