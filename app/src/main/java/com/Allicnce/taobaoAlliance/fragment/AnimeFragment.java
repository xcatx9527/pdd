package com.Allicnce.taobaoAlliance.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.BuildConfig;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.smartrefresh.SmartRefreshLayout;

import java.lang.reflect.Field;

/**
 * Created by zhaoxiaofei on 2018/5/2.
 */

public class AnimeFragment extends BaseFragment {


    @Bind(R.id.progress_webview)
    WebView webView;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    String normal_url = "https://m.dmzj.com/classify.html";
    String linkCss = "function hide(){var linkCss = \".header,.bottom,.control_panel .mark,.timeAD,.a_banner,.bottomDown,#app_home_ad,#divAD{display:none !important;}\";var styleDom=document.createElement('style');styleDom.innerHTML=linkCss;document.head.appendChild(styleDom)}hide();";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_anime;
    }

    @Override
    public void initView() {
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
       /* webView.setBackgroundResource(R.drawable.cash_bg);
        webView.setBackgroundColor(Color.argb(0, 0, 0, 0));*/
        settingWebView();
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                webView.loadUrl("javascript:" + linkCss);
                SystemClock.sleep(200);
            }
        };
        webView.setWebChromeClient(wvcc);
        webView.setWebViewClient(new WebViewClient() {
            //在页面加载开始时调用。
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
           /*     webView.setEnabled(true);// 当加载网页的时候将网页进行隐藏
                webView.setVisibility(View.GONE);*/
            }

            @Override
            public void onPageFinished(WebView view, String url) {//网页加载结束的时候
                super.onPageFinished(view, url);
                if (webView != null) {
                    webView.setEnabled(true);
                    webView.loadUrl("javascript:" + linkCss);
                    SystemClock.sleep(5);
                    webView.setVisibility(View.VISIBLE);
                }
            }

            //在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
            @SuppressLint("MissingPermission")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("用户单击超连接", url);
                //判断用户单击的是那个超连接
                String tag = "tel";
                if (url.contains(tag)) {
                    String mobile = url.substring(url.lastIndexOf("/") + 1);
                    Log.e("mobile----------->", mobile);
                    Intent mIntent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse(mobile);
                    mIntent.setData(data);
                    //Android6.0以后的动态获取打电话权限
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(mIntent);
                        //这个超连接,java已经处理了，webview不要处理
                        return true;
                    } else {
                        //申请权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return true;
                    }
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            //重写此方法可以让webview处理https请求。
            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            webView.loadUrl(normal_url);
            new Handler().postDelayed(() -> {
                refreshlayout.finishRefresh();
            }, 5000);
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }
 /*  final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            refreshHtmlContent(html);
        }
    }


    private void refreshHtmlContent(final String html){
        Log.e("网页内容",html);
        webView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //解析html字符串为对象
                Document document = Jsoup.parse(html);


                //获取进行处理之后的字符串并重新加载
                String body = document.toString();
                webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
            }
        },5000);
    }*/


    // 对WebView进入设置
    @SuppressLint("NewApi")
    private void settingWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        settings.setLoadWithOverviewMode(true);
        settings.supportZoom(); // 支持缩放
        //settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 关闭缓存
        settings.setAllowFileAccess(true); // 支持访问文件
        settings.setBuiltInZoomControls(false);
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        // 获取到UserAgentString
        String userAgent = settings.getUserAgentString();
        // 打印结果
        Log.i("TAG", "User Agent:" + userAgent);
    }


    /**
     * 释放所有的webview相关的调用
     */
    public void releaseAllWebViewCallback() {
        if (Build.VERSION.SDK_INT < 16) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean onKeyDown() {
        if (webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseAllWebViewCallback();

    }

}
