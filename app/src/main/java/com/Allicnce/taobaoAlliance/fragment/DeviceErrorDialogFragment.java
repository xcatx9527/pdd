package com.Allicnce.taobaoAlliance.fragment;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.Allicnce.taobaoAlliance.R;

/**
 * @author Administrator
 * @time 2018/5/17$
 * @descrition:
 */

public class DeviceErrorDialogFragment extends BaseDialogFragment {

    public static DeviceErrorDialogFragment instance;

    public static DeviceErrorDialogFragment getInstance() {
        if (instance == null) {
            instance = new DeviceErrorDialogFragment();
        }
        return instance;
    }

    @Override
    protected int getDialogResource() {
        return R.layout.rv_error_view;
    }

    @Override
    protected void initView() {
    }

    public void show(AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        show(fragmentManager, "");
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }
}
