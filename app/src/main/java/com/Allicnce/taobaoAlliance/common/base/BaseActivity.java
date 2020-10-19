package com.Allicnce.taobaoAlliance.common.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.ToastUtils;
import com.Allicnce.taobaoAlliance.common.loading.ios.KProgressHUD;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author: ChenYang
 * @date: 2017-10-23 17:12
 * @Email 1016224774@qq.com
 * @Description
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseMethodInterface {
    private long lastClickTime;
    private String rightTitle;
    private String middleTitle;
    private boolean canBack;
    private View.OnClickListener rightListener;
    private int righticon;
    private KProgressHUD loadingDialog;
    private LinearLayout rootLayout;

    @Override
    public boolean showToolBar(String middleTitle, String rightTitle, boolean canBack) {
        this.middleTitle = middleTitle;
        this.rightTitle = rightTitle;
        this.canBack = canBack;
        return true;
    }

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

    public interface OnToolBarListener {
        public void onBack();

        public void onNext();
    }

    @Override
    public Activity getActivityContext() {
        return this;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (getIntent().getExtras() != null) {
            onMessage(getIntent().getExtras());
        }
        ActivityManager.getAppManager().addActivity(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        this.initView();
        this.setListener();
    }


    public void onMessage(Bundle bundle) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBusMessage(Bundle bundle) {
        onMessage(bundle);
    }

    @Override
    public void setContentView(int layoutId) {
        if (middleTitle != null) {
            // 这句很关键，注意是调用父类的方法
            super.setContentView(R.layout.rootlayout_titlebar);
            setContentView(View.inflate(this, layoutId, null));
        } else {
            super.setContentView(layoutId);
        }
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    public boolean OnBackPress() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initToolbar() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(canBack);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

            toolbar.setNavigationOnClickListener(v -> {
                if (OnBackPress()) {
                    return;
                }
                finish();
                overridePendingTransition(R.anim.fixed, R.anim.slide_left_exit);
            });

        }
        TextView tv_middle_title = (TextView) findViewById(R.id.tv_middle_title);
        TextView tv_right_title = (TextView) findViewById(R.id.tv_right_title);
        ImageView iv_righticon = (ImageView) findViewById(R.id.iv_righticon);
        if (!TextUtils.isEmpty(rightTitle)) {
            tv_right_title.setOnClickListener(rightListener);
            tv_right_title.setText(rightTitle);
            iv_righticon.setVisibility(View.GONE);
            tv_right_title.setVisibility(View.VISIBLE);
        }
        if (righticon != 0) {
            iv_righticon.setImageResource(righticon);
            iv_righticon.setVisibility(View.VISIBLE);
            tv_right_title.setVisibility(View.GONE);
            iv_righticon.setOnClickListener(rightListener);
        }
        if (!TextUtils.isEmpty(middleTitle)) {
            tv_middle_title.setText(middleTitle);
        }

    }


    @Override
    public void onClick(View v) {
        if (isFastClick()) {// 如果是快速点击，返回
            return;
        }
        lastClickTime = System.currentTimeMillis();
        onChildClick(v);
    }


    public boolean isFastClick() {
        long timeInterval = System.currentTimeMillis() - lastClickTime;
        return timeInterval < 500 && timeInterval > 0;
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
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
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
        intent.setClass(this, cls);
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
            loadingDialog = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(getResources().getColor(R.color.colorPrimaryOverlay))
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
            loadingDialog = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setWindowColor(getResources().getColor(R.color.colorPrimary))
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void sendMessage(Bundle bundle) {
        EventBus.getDefault().post(bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        ActivityManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);

    }

    public void showEmptyView() {
    }

    public void hideStateView() {
    }

    public void showErrorView() {

    }
}
