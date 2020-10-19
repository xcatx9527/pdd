package com.Allicnce.taobaoAlliance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.BuildConfig;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.commonutils.AppUtils;
import com.Allicnce.taobaoAlliance.common.commonutils.EncryptUtils;
import com.Allicnce.taobaoAlliance.common.commonutils.FileUtils;
import com.Allicnce.taobaoAlliance.common.commonutils.GsonUtils;
import com.Allicnce.taobaoAlliance.common.immersionbar.ImmersionBar;
import com.Allicnce.taobaoAlliance.model.AppInfoBean;
import com.Allicnce.taobaoAlliance.utils.CMDUtil;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:46
 * @Email 1016224774@qq.com
 * @Description 启动页
 */
public class SplashActivity extends BaseActivity {
    @Bind(R.id.tv_name)
    TextView tvName;

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onMessage(Bundle bundle) {

    }

    @Override
    public void initView() {
        check();
        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            tvName.setText(BuildConfig.VERSION_NAME + BuildConfig.BUILD_TYPE + AppUtils.getAppName());
        }
        new Handler().postDelayed(() -> {
            if (Object2SdCardUtils.getObjectFormSdCard() != null) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            finish();
        }, 3000);

    }

    public void check() {
       /* if (RootUtil.isDeviceRooted()) {
            startActivity(DeviceErrorActivity.class);
            finish();
            return;
        }*/
        CMDUtil.CommandResult result = CMDUtil.execCommand(new String[]{"cat /sys/class/android_usb/android0/iSerial"}, false, true);
        CMDUtil.CommandResult result1 = CMDUtil.execCommand(new String[]{"cat /sys/block/mmcblk0/device/cid"}, false, true);
        String appid = "";
        if (!FileUtils.isFileExists(AppConstant.APPIDPATH)) {
            FileUtils.createOrExistsFile(AppConstant.APPIDPATH);
            appid = EncryptUtils.md5Encrypt(result.successMsg + result1.successMsg + System.currentTimeMillis());
            try {
                InputStream in_withcode = new ByteArrayInputStream(appid.getBytes("UTF-8"));
                FileUtils.writeFileFromIS(AppConstant.APPIDPATH, in_withcode, false);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            appid = FileUtils.readFile2String(AppConstant.APPIDPATH, "UTF-8");
        }
        AppInfoBean infoBean = new AppInfoBean();
        infoBean.androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        infoBean.appid = appid;
        infoBean.xcid = result1.successMsg;
        infoBean.iSerial = result.successMsg;
        AppConstant.APPID = GsonUtils.toJson(infoBean);
    }

    @Override
    public void setListener() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).transparentStatusBar().init();
    }

}
