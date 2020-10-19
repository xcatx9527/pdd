package com.Allicnce.taobaoAlliance.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppApplication;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;

public class DeviceErrorActivity extends BaseActivity {

    @Bind(R.id.bu_show)
    Button buShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.rv_error_view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.bu_show:
                AppApplication.instance.onTerminate();
                break;

        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void setListener() {
        buShow.setOnClickListener(this);
    }
}
