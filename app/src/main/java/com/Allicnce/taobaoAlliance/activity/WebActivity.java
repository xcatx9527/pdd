package com.Allicnce.taobaoAlliance.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.login.LoginActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:47
 * @Email 1016224774@qq.com
 * @Description 统一处理h5界面
 */
public class WebActivity extends BaseActivity {


    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.button_show)
    Button buttonShow;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.msg)
    TextView msg;
    @Bind(R.id.retry)
    Button retry;
    @Bind(R.id.retry_layout)
    LinearLayout retryLayout;
    private CookieManager cookieManager;


    @Override
    public int getLayoutId() {
        showToolBar(getIntent().getStringExtra("title"), getIntent().getStringExtra("righttitle"), true);
        return R.layout.web;
    }

    @Override
    public void onMessage(Bundle bundle) {

    }

    @Override
    public void initView() {
        WebSettings settings = webview.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        // js与android调用接口
        webview.addJavascriptInterface(new MyJavascriptInterface(), "com.Allicnce.Allicnce");
        // 设置Web视图
        webview.setWebViewClient(new MyWebViewClient());
        // 处理Javascript的对话框、网站图标、网站title、加载进度等
        webview.setWebChromeClient(new MyWebChromeClient());
        initErrorPage();
        webview.setDownloadListener(new MyWebViewDownLoadListener());
//        syncCookie(getIntent().getStringExtra("url"));
        webview.loadUrl(getIntent().getStringExtra("url"));
    }

    class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Toast t = Toast.makeText(webview.getContext(), "需要SD卡。", Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                return;
            }
           /* if (url.split().endsWith(".apk")) {
                Intent intent = new Intent();
                intent.putExtra("url", url);
                setResult(TaskDetialsActivity.DOWNLOADCODE, intent);
                finish();
            }*/
        }

    }

    public boolean syncCookie(String url) {
        cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, "JSESSIONID=" + "设置tooken");
        String newCookie = cookieManager.getCookie(url);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(this);
            cookieSyncManager.sync();
        }
        CookieSyncManager.getInstance().sync();
        Log.e("--^_^-->syncCookie", newCookie);

        return !TextUtils.isEmpty(newCookie);
    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 80) {
                progress.setProgress(newProgress);
                progress.setVisibility(View.GONE);
            } else {
                if (progress.getVisibility() == View.GONE) {
                    progress.setVisibility(View.VISIBLE);
                }
                progress.setProgress(newProgress);
            }

            super.onProgressChanged(view, newProgress);
        }

        /*此处覆盖的是javascript中的alert方法。
         *当网页需要弹出alert窗口时，会执行onJsAlert中的方法
         * 网页自身的alert方法不会被调用。
         */
     /*   @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            Log.d(TAG, "弹出了提示框");
                        *//*此处代码非常重要，若没有，android就不能与js继续进行交互了，
         * 且第一次交互后，webview不再展示出来。
         * result：A JsResult to confirm that the user hit enter.
         * 我的理解是，confirm代表着此次交互执行完毕。只有执行完毕了，才可以进行下一次交互。
         *//*
            result.confirm();
            return true;
        }*/

        /*此处覆盖的是javascript中的confirm方法。
         *当网页需要弹出confirm窗口时，会执行onJsConfirm中的方法
         * 网页自身的confirm方法不会被调用。
         */
     /*   @Override
        public boolean onJsConfirm(WebView view, String url,
                                   String message, JsResult result) {
            Log.d(TAG, "弹出了确认框");
            result.confirm();
            return true;
        }

        *//*此处覆盖的是javascript中的confirm方法。
         *当网页需要弹出confirm窗口时，会执行onJsConfirm中的方法
         * 网页自身的confirm方法不会被调用。
         *//*
        @Override
        public boolean onJsPrompt(WebView view, String url,
                                  String message, String defaultValue,
                                  JsPromptResult result) {
            Log.d(TAG, "弹出了输入框");
            result.confirm();
            return true;
        }

        *//*
         * 如果页面被强制关闭,弹窗提示：是否确定离开？
         * 点击确定 保存数据离开，点击取消，停留在当前页面
         *//*
        @Override
        public boolean onJsBeforeUnload(WebView view, String url,
                                        String message, JsResult result) {
            Log.d(TAG, "弹出了离开确认框");
            result.confirm();
            return true;
        }*/
    }

    /**
     * 显示自定义错误提示页面，用一个View覆盖在WebView
     */
    protected void showErrorPage() {
        retryLayout.setVisibility(View.VISIBLE);
        webview.setVisibility(View.GONE);
    }

    /**
     * 隐藏自定义错误提示页面
     */
    protected void hideErrorPage() {
        retryLayout.setVisibility(View.GONE);
        webview.setVisibility(View.VISIBLE);
    }

    private boolean mIsErrorPage;

    protected void initErrorPage() {
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mIsErrorPage) {//显示隐藏页
                showErrorPage();
            } else {
                hideErrorPage();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("--^_^-->UrlLoading", url);
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mIsErrorPage = true;
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.retry:
                mIsErrorPage = false;
                webview.reload();
                break;
            case R.id.btn_back:
                if (webview.canGoBack()) {//如果能回退
                    webview.goBack();
                } else {
                    finish();
                    overridePendingTransition(R.anim.fixed, R.anim.slide_left_exit);
                }
                break;
        }

    }

    @Override
    public void setListener() {
        retry.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果没有历史会导致崩溃
//        webview.clearCache(true);
//        webview.clearHistory();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {//如果能回退
                webview.goBack();
            } else {
                finish();
                overridePendingTransition(R.anim.fixed, R.anim.slide_left_exit);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class MyJavascriptInterface {
        public MyJavascriptInterface() {

        }

        /**
         * session失效
         */
        @JavascriptInterface
        public void sessionUnvalid() {
          /*  SharedPreferences userSp = getSharedPreferences(Constant.USER, Context.MODE_PRIVATE);
            userSp.edit().putBoolean("islogin", false).commit();
            SharedPreferences sp = getSharedPreferences("logisticsinfo", MODE_MULTI_PROCESS);
            sp.edit().putBoolean("islogin", false).commit();// 设置为登陆状态*/
            startActivityForResult(new Intent(WebActivity.this, LoginActivity.class).putExtra("msg", "请重新登录"), -1);
        }
    }
}
