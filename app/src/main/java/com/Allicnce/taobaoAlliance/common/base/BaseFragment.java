package com.Allicnce.taobaoAlliance.common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.ToastUtils;
import com.Allicnce.taobaoAlliance.common.loading.ios.KProgressHUD;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends Fragment implements View.OnClickListener, BaseMethodInterface {
    protected View rootView;
    private long lastClickTime;
    private String rightTitle;
    private String middleTitle;
    private boolean canBack;
    private KProgressHUD loadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        if (middleTitle != null) {
            // 这句很关键，注意是调用父类的方法
            rootView = inflater.inflate(R.layout.fragment_rootlayout_titlebar, container, false);
            ((LinearLayout) rootView).addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            initToolbar();
        } else {
            rootView = view;
        }
        onMessage(getArguments());
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, rootView);
        initView();
        setListener();
        return rootView;
    }

    @Override
    public Activity getActivityContext() {
        return getActivity();
    }

    @Override
    public void sendMessage(Bundle bundle) {
        EventBus.getDefault().post(bundle);
    }

    private View.OnClickListener rightListener;
    private int righticon;

    @Override
    public boolean showToolBar(String middleTitle, int righticon, boolean canBack, View.OnClickListener rightListener) {
        this.middleTitle = middleTitle;
        this.righticon = righticon;
        this.canBack = canBack;
        this.rightListener = rightListener;
        return true;
    }

    @Override
    public boolean showToolBar(String middleTitle, String rightTitle, View.OnClickListener rightListener) {
        this.middleTitle = middleTitle;
        this.rightTitle = rightTitle;
        this.rightListener = rightListener;

        return true;
    }

    /**
     * @param moddleTitle   中间标题
     * @param rightTitle    右侧标题
     * @param canback       返回键
     * @param rightListener 返回键监听
     *                      标题设置null或空字符串则不显示该标题
     */
    @Override
    public void showToolBar(String moddleTitle, String rightTitle, boolean canback, View.OnClickListener rightListener) {
        this.rightListener = rightListener;
        this.middleTitle = moddleTitle;
        this.rightTitle = rightTitle;
        this.canBack = canback;
    }


    @Override
    public boolean showToolBar(String middleTitle, String rightTitle, boolean canBack) {
        this.canBack = canBack;
        this.middleTitle = middleTitle;
        this.rightTitle = rightTitle;
        return true;
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
        if (canBack) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(canBack);
            toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView tv_middle_title = (TextView) rootView.findViewById(R.id.tv_middle_title);
        TextView tv_right_title = (TextView) rootView.findViewById(R.id.tv_right_title);

        if (!TextUtils.isEmpty(rightTitle)) {
            tv_right_title.setText(rightTitle);
        }
        if (rightListener != null) {
            tv_right_title.setOnClickListener(rightListener);
        }
        if (!TextUtils.isEmpty(middleTitle)) {
            tv_middle_title.setText(middleTitle);
        }

    }


    public void onMessage(Bundle bundle) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBusMessage(Bundle bundle) {
        onMessage(bundle);
    }

    /**
     * 通过Class跳转界面
     **/
    @Override
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    @Override
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    @Override
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    @Override
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivityContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = KProgressHUD.create(getActivityContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(getActivityContext().getResources().getColor(R.color.md_grey500))
                    .setAnimationSpeed(2);
        }

        loadingDialog.show();
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    @Override
    public void showLoading(String msg) {
        if (loadingDialog == null) {
            loadingDialog = KProgressHUD.create(getActivity())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(getResources().getColor(R.color.md_grey500))
                    .setAnimationSpeed(2);
        }
        loadingDialog.show();
    }

    /**
     * 停止浮动加载进度条
     */
    @Override
    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    @Override
    public void showShortToast(String text) {
        ToastUtils.showShort(text);
    }


    @Override
    public void onClick(View v) {
        // 如果是快速点击，返回
        if (isFastClick()) {
            return;
        }
        lastClickTime = System.currentTimeMillis();
        onChildClick(v);
    }

    public boolean isFastClick() {
        long timeInterval = System.currentTimeMillis() - lastClickTime;
        return timeInterval < 500 && timeInterval > 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void hideStateView() {
    }

    @Override
    public void showErrorView() {

    }
}
