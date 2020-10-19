package com.Allicnce.taobaoAlliance.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @author: ChenYang
 * @date: 2017-10-23 18:00
 * @Email 1016224774@qq.com
 * @Description
 */
public interface BaseMethodInterface {
    public Activity getActivityContext();
    public boolean showToolBar(String middleTitle, String rightTitle, boolean canBack);

    public boolean showToolBar(String middleTitle, int righticon, boolean canBack, View.OnClickListener rightListener);

    public boolean showToolBar(String middleTitle, String rightTitle, View.OnClickListener rightListener);

    /**
     * @param moddleTitle   中间标题
     * @param rightTitle    右侧标题
     * @param canback       返回键
     * @param rightListener 返回键监听
     *                      标题设置null或空字符串则不显示该标题
     */
    public void showToolBar(String moddleTitle, String rightTitle, boolean canback, View.OnClickListener rightListener);


    /******************************
     * 子类实现
     *****************************/
    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void onChildClick(View v);

    public abstract void setListener();


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls);

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode);

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode);

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle);

    /**
     * 开启浮动加载进度条
     */
    public void showLoading();

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void showLoading(String msg);

    /**
     * 停止浮动加载进度条
     */
    public void hideLoading();

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text);


    public void sendMessage(Bundle bundle);

    public void showEmptyView();

    public void hideStateView();

    public void showErrorView();

}
