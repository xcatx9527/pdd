package com.Allicnce.taobaoAlliance.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import butterknife.ButterKnife;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.ToastUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:53
 * @Email 1016224774@qq.com
 * @Description
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {

    private long lastClickTime;


    public BaseDialogFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onMessage(getArguments());
        EventBus.getDefault().register(this);

    }

    //获取布局文件
    protected abstract int getDialogResource();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    protected abstract void initView();

    public abstract void onChildClick(View v);

    public abstract void setListener();


    public void onMessage(Bundle bundle) {
    }

    public void sendMessage(Bundle bundle) {
        EventBus.getDefault().post(bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBusMessage(Bundle bundle) {
        onMessage(bundle);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.BaseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(getDialogResource());
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = setParams(window);
        if (layoutParams == null) {
            layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM; // 紧贴底部
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        }

        window.setAttributes(layoutParams);
        ButterKnife.bind(this, dialog); // Dialog即View
        initView();
        setListener();
        return dialog;
    }

    public WindowManager.LayoutParams setParams(Window window) {
        return null;
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
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
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtils.showShort(text);
    }


    @Override
    public void onClick(View v) {
        if (isFastClick()) {// 如果是快速点击，返回
            return;
        }
        lastClickTime = System.currentTimeMillis();
        onChildClick(v);
    }

    private boolean isFastClick() {
        long timeInterval = System.currentTimeMillis() - lastClickTime;
        return timeInterval < 500 && timeInterval > 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

}
