package com.Allicnce.taobaoAlliance.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.*;
import android.widget.Toast;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.BuildConfig;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;
import com.Allicnce.taobaoAlliance.common.smartrefresh.SmartRefreshLayout;

import java.lang.reflect.Field;

/**
 * Created by zhaoxiaofei on 2018/5/2.
 */

public class BookFragment extends BaseFragment implements DownloadListener {
    @Bind(R.id.progress_webview)
    WebView webView;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    String normal_url = "http://qdgx.ikanshu.cn/";
//    String normal_url = "http://10.5.80.224:8080/page/jsp/query/query_login.jsp";


    /*
     String headerOne = "function hide1(){var linkCss = \".ui_page_foot,.bt_addtohome_ok,.bt_addtohome_close,.footer,.zw_tc.blue,.ui_copyright,.footer-top.zw_box,.ui_addweixin,.ui_channel1>a,iframe,#audioBar~div,.ui_morelogin_list a:nth-child(2),.ui_morelogin_list a:nth-child(3),body>.header,.copyright{display:none !important;}\";var styleDom=document.createElement('style');styleDom.innerHTML=linkCss;document.head.appendChild(styleDom);}hide1();";
     String headerTwo = "function hide2(){if(location.host==\"qdgx.ikanshu.cn\"){var li3=$('.ui_slider .ul_item li').eq(2);var href=li3.find('a').attr('href');var imgUrl=li3.find('img').attr('src');var li6=$('.ui_slider .ul_item li').eq(5);li6.find('a').attr('href', href);li6.find('img').attr('src',imgUrl);}}hide2();";
    */
    String linkcss = "function hide1(){var linkCss=\".ui_page_foot,.bt_addtohome_ok,.bt_addtohome_close,.footer,.zw_tc.blue,.ui_copyright,.footer-top.zw_box,.ui_addweixin,.ui_channel1>a,iframe,#audioBar~div,.ui_morelogin_list a:nth-child(2),.ui_morelogin_list a:nth-child(3),body>.header,.copyright{display:none !important;}.menu{position:absolute;top:0;left:0;z-index:10;background:none !important;border-bottom:none !important;}.menu a{color:#fff !important;}.menu a span{text-decoration: underline;text-underline-position: under;padding: 3px 0 6px;}.menu a:nth-child(5) span{display:none;}\";var styleDom=document.createElement(\"style\");styleDom.innerHTML=linkCss;document.head.appendChild(styleDom)}function hide2(){if(location.host==\"qdgx.ikanshu.cn\"){var li3=$(\".ui_slider .ul_item li\").eq(2);var href=li3.find(\"a\").attr(\"href\");var imgUrl=li3.find(\"img\").attr(\"src\");var li6=$(\".ui_slider .ul_item li\").eq(5);li6.find(\"a\").attr(\"href\",href);li6.find(\"img\").attr(\"src\",imgUrl);$(\".menu a\").eq(4).html('<img style=\"width:20px;height:20px;position: relative;top:4px;\" src=\"http://118.145.5.140:3001/images/icon/search-white2.png\"/>')}}function createAdd(url){$.ajax({url:url,type:\"get\",success:function(d){d=typeof d===\"string\"?JSON.parse(d):d;createDom(d)}})}function createDom(d){var dom='<div class=\"xbAdd\" style=\"position:fixed;z-index:10;bottom:0;left:0;width:100%;background:#fff\"><a href=\"'+d.gotourl+'\"><span style=\"width:100%;overflow:hidden;margin-right:10px;padding-left: 0px;\"><img style=\"width:100%;\" src=\"'+d.imgurl+'\"/></span></a><div style=\"position: absolute; z-index: 11; top: 0px; right: 0px; width: 20px; height: 20px; margin: 0px; padding: 0px; border: 0px; background: rgb(153, 153, 153); font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 16px; line-height: 20px; font-family: Tahoma; color: rgb(238, 238, 238); text-align: center; overflow: hidden;\" onclick=\"hideAdd()\">×</div></div>';$(\"#audioBar\").before(dom)}function hideAdd(){$(\".xbAdd\").remove()}hide1();hide2();var reg=/\\/book\\/\\d*\\/\\d*\\.html$/g;var href=location.href;if(reg.test(href)){createAdd(\"http://sdk.cferw.com/api.php?z=15581&appkey=98a968099f61a5765e0892ea6dbe84b3&deviceId=xxxxxxxxxxxx&sw=0&sh=0&osver=6.5\")};";


    @Override
    public int getLayoutId() {
        return R.layout.fragment_anime;
    }

    @Override
    public void initView() {
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        settingWebView();
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                webView.loadUrl("javascript:" + linkcss);
                SystemClock.sleep(200);
            }
        };
        webView.setWebChromeClient(wvcc);

        webView.setWebViewClient(new WebViewClient() {
            //在页面加载开始时调用。
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                webView.setEnabled(true);// 当加载网页的时候将网页进行隐藏
//                webView.setVisibility(View.GONE);
              /*  webView.loadUrl("javascript:window.local_obj.showSource('<head>'+"
                        + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");*/
            }


            @Override
            public void onPageFinished(WebView view, String url) {//网页加载结束的时候
                super.onPageFinished(view, url);
                webView.setEnabled(true);
                webView.loadUrl("javascript:" + linkcss);
                webView.setVisibility(View.VISIBLE);
            }

            //在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
            @SuppressLint("MissingPermission")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("用户单击超连接", url);
                //判断用户单击的是那个超连接
                String tag = "tel";
                //mEmptyLayoutView.onDismiss();

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
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            webView.loadUrl(normal_url);
            new Handler().postDelayed(() -> {
                refreshlayout.finishRefresh();
            }, 5000);
        });


    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }


    class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                    long contentLength) {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Toast t = Toast.makeText(getActivity(), "需要SD卡。", Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
                return;
            }
            if (url.endsWith(".apk")) {
                //下载
             /*   Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);*/
            }
            /* */
        }

    }


    // 对WebView进入设置
    @SuppressLint("NewApi")
    private void settingWebView() {
        webView.setDownloadListener(new MyWebViewDownLoadListener());
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

    public boolean onKeyDown() {
        if (webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        if (url.endsWith(".apk")) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseAllWebViewCallback();
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
}

