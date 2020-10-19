package com.Allicnce.taobaoAlliance.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.commonutils.AppUtils;


public class AboutActivity extends BaseActivity {

    @Bind(R.id.iv_ic_luncher)
    ImageView ivIcLuncher;
    @Bind(R.id.tv_versions)
    TextView tvVersions;
    @Bind(R.id.tv_web)
    TextView tvWeb;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.activity_about)
    RelativeLayout activityAbout;
    @Bind(R.id.ll_toweixin)
    LinearLayout llToweixin;

    @Override
    public int getLayoutId() {
        showToolBar("关于", "", true);
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        tvVersions.setText(AppUtils.getAppVersionName() + "");
        tvPhone.setOnClickListener(v -> {
            Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + "010-88464666"));
            startActivity(phoneIntent);
        });
        tvWeb.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse("http://www.com.Allicnce.Allicnce.com");
            intent.setData(content_url);
            startActivity(intent);
        });

    }


    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {

    }
}
