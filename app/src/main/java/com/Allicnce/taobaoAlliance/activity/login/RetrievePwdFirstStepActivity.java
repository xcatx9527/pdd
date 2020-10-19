package com.Allicnce.taobaoAlliance.activity.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.Allicnce.taobaoAlliance.AppApplication;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.widget.EditTextWithDel;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:31
 * @Email 1016224774@qq.com
 * @Description 找回密码第一步
 */
public class RetrievePwdFirstStepActivity extends BaseActivity implements EditTextWithDel.AddSearchTextChangedListener {

    private Button next_btn;
    private EditTextWithDel phone_et;
    private Button sendAuthCode_btn;
    private EditText authCode_et;

    @Override
    public int getLayoutId() {
        return R.layout.retrieve_pwd_first_step;
    }

    @Override
    public void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences("phone", Context.MODE_PRIVATE);

        next_btn = (Button) findViewById(R.id.next);
        phone_et = (EditTextWithDel) findViewById(R.id.phone);
        sendAuthCode_btn = (Button) findViewById(R.id.sendauthcode);
        authCode_et = (EditText) findViewById(R.id.authcode);
        phone_et.setText(sharedPreferences.getString("phone", ""));
    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
           /* case R.id.back://返回
                finish();
                overridePendingTransition(R.anim.fixed, R.anim.slide_left_exit);
                break;*/
            case R.id.next://找回密码
                if (TextUtils.isEmpty(phone_et.getText().toString().trim())) {
                    showShortToast("请输入手机号");
                    return;
                }
                if (phone_et.getText().toString().trim().length() != 11) {
                    showShortToast("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(authCode_et.getText().toString().trim())) {
                    showShortToast("请输入验证码");
                    return;
                }
//                doNext();
                break;
            case R.id.sendauthcode://发送验证码
                String phone = phone_et.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    showShortToast("请输入手机号");
                    return;
                }
                if (phone.length() != 11) {
                    showShortToast("请输入正确的手机号");
                    return;
                }
                AppApplication.instance.setRegisterCountDown(60 * 1000, 1000, sendAuthCode_btn, 2);
//                sendAuthCode();
                break;
            default:
                break;
        }
    }

    @Override
    public void setListener() {

    }

    private void isCanPoint() {
        if (phone_et.getText().toString().trim().length() > 0 && authCode_et.getText().toString().trim().length() > 0) {
            next_btn.setEnabled(true);
            next_btn.setBackgroundResource(R.drawable.login_bg);
        } else {
            next_btn.setEnabled(false);
            next_btn.setBackgroundResource(R.drawable.un_click_login);
        }
    }

    @Override
    public void afterTextChanged() {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isCanPoint();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    }
}

/*
    private void sendAuthCode() {
        LoginRequest authCodeRequest = newdialog LoginRequest(Constant.APP_TYPE, UniversalUtil.getInstance().getLoginToken(getApplicationContext()));
        JSONObject jsonObject = newdialog JSONObject();
        jsonObject.put("mobile", phone_et.getText().toString().trim());
        authCodeRequest.smsCode = jsonObject;
        HttpRequest httpRequest = newdialog HttpRequest();
        httpRequest.sendRequest(RetrievePwdFirstStepActivity.this, authCodeRequest, AuthCodeReply.class, UrlManager.FINDPWD_URL, newdialog HttpAsyncTask.TaskCallBack() {
            @Override
            public void beforeTask() {

            }

            @Override
            public void successRequest(AbstractReply reply) throws Exception {
                AuthCodeReply authCodeReply = (AuthCodeReply) reply;
                if (authCodeReply.checkSuccess()) {//成功
                    Toast.makeText(RetrievePwdFirstStepActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.equals(authCodeReply.getReturnCode(), "")) {//发送失败

                } else {
                    UniversalUtil.getInstance().showToast(authCodeReply.getReturnMessage(), getApplicationContext());
                }
            }

            @Override
            public void failureRequest() {
                UniversalUtil.getInstance().showToast(R.string.failurerequest, getApplicationContext());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            setResult(RESULT_OK, data);
            finish();
        }
    }
    *//**
 * 注册
 * <p>
 * 下一步
 * <p>
 * 下一步
 * <p>
 * 下一步
 * <p>
 * 下一步
 * <p>
 * 下一步
 *//*
    private void doRegister() {
        LoginRequest request = newdialog LoginRequest(Constant.APP_TYPE, UniversalUtil.getInstance().getLoginToken(getApplicationContext()));
        JSONObject jsonObject = newdialog JSONObject();
        jsonObject.put("mobile", phone_et.getText().toString().trim());
        jsonObject.put("code", authCode_et.getText().toString().trim());
        request.smsCodeQuery = jsonObject;
        HttpRequest httpRequest = newdialog HttpRequest();
        httpRequest.sendRequest(RetrievePwdFirstStepActivity.this, request, RegisterFirstStepReply.class, UrlManager.REGISTER_FIRST_STEP_URL, newdialog HttpAsyncTask.TaskCallBack() {
            @Override
            public void beforeTask() {

            }

            @Override
            public void successRequest(AbstractReply reply) throws Exception {
                RegisterFirstStepReply registerFirstStepReply = (RegisterFirstStepReply) reply;
                if (registerFirstStepReply.checkSuccess()) {
                    Intent intent = newdialog Intent(RetrievePwdFirstStepActivity.this, RetrievePwdSecondStepActivity.class);
                    intent.putExtra("mobile", phone_et.getText().toString().trim());
                    intent.putExtra("code", authCode_et.getText().toString().trim());
                    startActivity(intent);
                    overridePendingTransition(R.anim.fixed, R.anim.slide_right_in);
                    finish();
                } else {
                    UniversalUtil.getInstance().showToast(registerFirstStepReply.getReturnMessage(), getApplicationContext());
                }
            }

            @Override
            public void failureRequest() {
                UniversalUtil.getInstance().showToast(R.string.failurerequest, getApplicationContext());
            }
        });

    }
    */

/**
 * 下一步
 *//*
    private void doNext() {
        LoginRequest registerFirstStepRequest = newdialog LoginRequest(Constant.APP_TYPE, UniversalUtil.getInstance().getLoginToken(getApplicationContext()));
        JSONObject jsonObject = newdialog JSONObject();
        jsonObject.put("mobile", phone_et.getText().toString().trim());
        jsonObject.put("code", authCode_et.getText().toString().trim());

        registerFirstStepRequest.smsCodeQuery = jsonObject;
        HttpRequest httpRequest = newdialog HttpRequest();
        httpRequest.sendRequest(RetrievePwdFirstStepActivity.this, registerFirstStepRequest, RegisterFirstStepReply.class, UrlManager.REGISTER_FIRST_STEP_URL, newdialog HttpAsyncTask.TaskCallBack() {
            @Override
            public void beforeTask() {

            }

            @Override
            public void successRequest(AbstractReply reply) throws Exception {
                RegisterFirstStepReply registerFirstStepReply = (RegisterFirstStepReply) reply;
                if (registerFirstStepReply.checkSuccess()) {
                    Intent intent = newdialog Intent(RetrievePwdFirstStepActivity.this, RetrievePwdSecondStepActivity.class);
                    intent.putExtra("mobile", phone_et.getText().toString().trim());
                    intent.putExtra("code", authCode_et.getText().toString().trim());
                    startActivityForResult(intent, 1001);
                    overridePendingTransition(R.anim.fixed, R.anim.slide_right_in);
                    finish();
                } else {
                    UniversalUtil.getInstance().showToast(registerFirstStepReply.getReturnMessage(), getApplicationContext());
                }
            }

            @Override
            public void failureRequest() {
                UniversalUtil.getInstance().showToast(R.string.failurerequest, getApplicationContext());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            onChildClick(back);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void afterTextChanged() {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isCanPoint();
    }




    }*/


