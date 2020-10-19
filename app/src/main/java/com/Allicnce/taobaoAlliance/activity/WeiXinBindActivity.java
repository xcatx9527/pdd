package com.Allicnce.taobaoAlliance.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppConstant;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.commonutils.EncryptUtils;
import com.Allicnce.taobaoAlliance.model.LoginModel;
import com.Allicnce.taobaoAlliance.okgohttp.CustomRequest;
import com.Allicnce.taobaoAlliance.okgohttp.callback.ModelCallBack;
import com.Allicnce.taobaoAlliance.picPicker.GlideImageLoader;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;
import com.lzy.okgo.request.base.Request;

import java.util.HashMap;

public class WeiXinBindActivity extends BaseActivity {


    @Bind(R.id.iv_erweima)
    ImageView ivErweima;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.bt_bind)
    Button btBind;
    private String picUrl;

    @Override
    public int getLayoutId() {
        showToolBar("微信绑定", "", true);
        return R.layout.activity_wei_xin_bind;
    }

    @Override
    public void initView() {
        getPic();
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.bt_bind:
                if (!TextUtils.isEmpty(etCode.getText())) {
                    login();
                }
                break;
        }
    }

    @Override
    public void setListener() {
        btBind.setOnClickListener(this);
    }

    public void login() {
        String sign = EncryptUtils.md5Encrypt(String.valueOf(etCode.getText()) + "lj8965ljk");
        HashMap<String, String> params = new HashMap<>();
        params.put("os", "Android");
        params.put("devInfo", AppConstant.APPID);
        params.put("verifyCode", String.valueOf(etCode.getText()));
        params.put("sign", sign);
        CustomRequest.PostNoSecret(params, AppConstant.LOGIN_URL, new ModelCallBack<LoginModel>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<LoginModel> response) {
                super.onSuccess(response);
                if (response.body() != null && response.body().code == 0) {
                    Object2SdCardUtils.writeObjectToSdCard(response.body());
                    startActivity(MainActivity.class);
                    finish();
                }
            }

            @Override
            public void onStart(Request<LoginModel, ? extends Request> request) {
                super.onStart(request);

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<LoginModel> response) {
                super.onError(response);

            }
        });
    }

    public void getPic() {
        HashMap<String, String> params = new HashMap<>();
        CustomRequest.PostNoSecret(params, AppConstant.GETERWEIMA_URL, new ModelCallBack<LoginModel>() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<LoginModel> response) {
                super.onSuccess(response);
                if (response.body() != null && response.body().code == 0) {
                    GlideImageLoader.instance.displayImage(WeiXinBindActivity.this, response.body().robotWxCode, ivErweima);
                }
            }

            @Override
            public void onStart(Request<LoginModel, ? extends Request> request) {
                super.onStart(request);

            }

            @Override
            public void onError(com.lzy.okgo.model.Response<LoginModel> response) {
                super.onError(response);

            }
        });
    }

}
