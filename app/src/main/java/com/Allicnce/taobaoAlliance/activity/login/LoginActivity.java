package com.Allicnce.taobaoAlliance.activity.login;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.activity.MainActivity;
import com.Allicnce.taobaoAlliance.animator.ObjectAnimatorUtils;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.commonutils.KeyboardUtils;
import com.Allicnce.taobaoAlliance.widget.EditTextWithDel;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:31
 * @Email 1016224774@qq.com
 * @Description 登陆
 */
public class LoginActivity extends BaseActivity implements EditTextWithDel.AddSearchTextChangedListener {

    @Bind(R.id.iv_headpic)
    ImageView ivHeadpic;
    @Bind(R.id.account)
    EditTextWithDel account_et;
    @Bind(R.id.pass)
    EditTextWithDel pass_et;
    @Bind(R.id.login)
    Button login_btn;
    @Bind(R.id.register)
    Button register_btn;
    @Bind(R.id.retrievepwd)
    Button retrievePwd_btn;
    @Bind(R.id.sendauthcode)
    Button sendauthcode;
    @Bind(R.id.tv_animation)
    TextView tvanimation;
    @Bind(R.id.tv_animation1)
    TextView tvAnimation1;
    @Bind(R.id.tv_animation2)
    TextView tvAnimation2;
    @Bind(R.id.tv_animation3)
    TextView tvAnimation3;
    @Bind(R.id.tv_animation4)
    TextView tvAnimation4;
    @Bind(R.id.ll_phone)
    LinearLayout llPhone;
    @Bind(R.id.ll_password)
    LinearLayout llPassword;
    @Bind(R.id.tv_animation7)
    TextView tvAnimation7;
    @Bind(R.id.tv_animation6)
    TextView tvAnimation6;
    @Bind(R.id.tv_animation5)
    TextView tvAnimation5;
    @Bind(R.id.tv_animation8)
    TextView tvAnimation8;
    @Bind(R.id.tv_animation9)
    TextView tvAnimation9;

    @Override
    public int getLayoutId() {
        return R.layout.login;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {

        account_et.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ObjectAnimatorUtils.animate(llPhone).translationZ(10).duration(500).start();

            } else {
                ObjectAnimatorUtils.animate(llPhone).translationZ(-10).duration(500).start();
            }
        });
        pass_et.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                ObjectAnimatorUtils.animate(llPassword).translationZ(10).duration(500).start();
            } else {
                ObjectAnimatorUtils.animate(llPassword).translationZ(-10).duration(500).start();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1002) {//注册
                setResult(RESULT_OK);
                finish();
            } else if (requestCode == 1003) {//找回密码
                String phone = data.getStringExtra("phone");
                account_et.setText(phone);
                pass_et.requestFocus();
            }

        }

    }


    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.register://注册
                startActivityForResult(new Intent(LoginActivity.this, RegisterFirstStepActivity.class), 1002);
                overridePendingTransition(R.anim.slide_right_in, R.anim.fixed);
                finish();
                break;
            case R.id.retrievepwd://找回密码
                startActivityForResult(new Intent(LoginActivity.this, RetrievePwdFirstStepActivity.class), 1003);
                overridePendingTransition(R.anim.slide_right_in, R.anim.fixed);
                break;
            case R.id.login://登录:
                if (TextUtils.isEmpty(account_et.getText().toString().trim())) {
                    showShortToast("请输入您的手机号");
                    return;
                }
                if (TextUtils.isEmpty(pass_et.getText().toString().trim())) {
                    showShortToast("请输入您密码");
                    return;
                }
                KeyboardUtils.hideSoftInput(this);
                startActivity(MainActivity.class);
                break;
            case R.id.sendauthcode://找回密码
                String phones = account_et.getText().toString().trim();
                if (TextUtils.isEmpty(phones)) {
                    showShortToast("请输入手机号");
                    return;
                }
                if (phones.length() != 11) {
                    showShortToast("请输入正确的手机号");
                    return;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 密码验证
     *
     * @param password
     * @return
     */
    public boolean isPassword(String password) {
        String passRegex = "[0-9a-zA-Z]{6,}";// 6到16位数字组合
        return password.matches(passRegex);
    }

    @Override
    public void setListener() {
        login_btn.setOnClickListener(this);
        retrievePwd_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
        account_et.setAddSearchTextChangedListener(this);
        pass_et.setAddSearchTextChangedListener(this);
        sendauthcode.setOnClickListener(this);
        ObjectAnimatorUtils.animate(tvAnimation5).translationZ(5).duration(2000).alpha(0, 1).startDelay(500).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation6).translationZ(5).duration(2000).alpha(0, 1).startDelay(1000).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation7).translationZ(5).duration(2000).alpha(0, 1).startDelay(1500).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation8).translationZ(5).duration(2000).alpha(0, 1).startDelay(900).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation9).translationZ(5).duration(2000).alpha(0, 1).startDelay(1200).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvanimation).translationZ(5).duration(2000).alpha(0, 1).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation1).translationZ(5).duration(2000).alpha(0, 1).startDelay(500).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation2).translationZ(5).duration(2000).alpha(0, 1).startDelay(900).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation3).translationZ(5).duration(2000).alpha(0, 1).startDelay(1300).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();
        ObjectAnimatorUtils.animate(tvAnimation4).translationZ(5).duration(2000).alpha(0, 1).startDelay(1700).repeatMode(ValueAnimator.REVERSE).repeatCount(-1).start();

    }

    @Override
    public void afterTextChanged() {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (account_et.getText().toString().trim().length() > 0 && pass_et.getText().toString().trim().length() > 0) {
            login_btn.setEnabled(true);
        } else {
            login_btn.setEnabled(false);
        }
    }


}
