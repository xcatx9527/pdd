package com.Allicnce.taobaoAlliance.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:32
 * @Email 1016224774@qq.com
 * @Description 找回密码第二步
 */
public class RetrievePwdSecondStepActivity extends BaseActivity {


    @Bind(R.id.iv_headpic)
    ImageView ivHeadpic;
    @Bind(R.id.passicon)
    ImageView passicon;
    @Bind(R.id.pass)
    EditText pass;
    @Bind(R.id.passlayout)
    RelativeLayout passlayout;
    @Bind(R.id.makesurepassicon)
    ImageView makesurepassicon;
    @Bind(R.id.makesurepass)
    EditText makesurepass;
    @Bind(R.id.makesuerpasslayout)
    RelativeLayout makesuerpasslayout;
    @Bind(R.id.finish)
    Button finish;

    @Override
    public int getLayoutId() {
        return R.layout.retrieve_pwd_second_step;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void setListener() {

    }

}
