package com.Allicnce.taobaoAlliance.activity.login;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseApplication;
import com.Allicnce.taobaoAlliance.widget.EditTextWithDel;

import static com.Allicnce.taobaoAlliance.R.id.tv_how;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:31
 * @Email 1016224774@qq.com
 * @Description 注册第一步
 */
public class RegisterFirstStepActivity extends BaseActivity implements EditTextWithDel.AddSearchTextChangedListener {

    @Bind(R.id.titlelayout)
    RelativeLayout titlelayout;
    @Bind(R.id.phone)
    EditTextWithDel phone;
    @Bind(R.id.sendauthcode)
    Button sendauthcode;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.authcode)
    EditTextWithDel authcode;
    @Bind(R.id.et_code)
    EditTextWithDel etCode;
    @Bind(R.id.ll_code)
    LinearLayout llCode;
    @Bind(tv_how)
    TextView tvHow;
    @Bind(R.id.next)
    Button next;
    @Bind(R.id.agreeprotocol)
    CheckBox agreeprotocol;
    @Bind(R.id.readprotocol)
    TextView readprotocol;


    @Override
    public void afterTextChanged() {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isCanPoint();
    }

    private void isCanPoint() {
        if (phone.getText().toString().trim().length() > 0 && authcode.getText().toString().trim().length() > 0) {
            next.setEnabled(true);
            next.setBackgroundResource(R.drawable.login_bg);
        } else {
            next.setEnabled(false);
            next.setBackgroundResource(R.drawable.un_click_login);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.register_first_step;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {

            case R.id.readprotocol://阅读协议

                overridePendingTransition(R.anim.slide_right_in, R.anim.fixed);
                break;
            case tv_how://阅读协议

                break;
            case R.id.next://注册:
                if (TextUtils.isEmpty(phone.getText().toString().trim())) {
                    showShortToast("请输入手机号");
                    return;
                }
                if (phone.getText().toString().trim().length() != 11) {
                    showShortToast("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(authcode.getText().toString().trim())) {
                    showShortToast("请输入验证码");
                    return;
                }
                if (!agreeprotocol.isChecked()) {
                    showShortToast("请选择我已阅读并同意惠学帮用户协议");
                    return;
                }
//                doRegister();
                break;
            case R.id.sendauthcode://发送验证码
                String phones = phone.getText().toString().trim();
                if (TextUtils.isEmpty(phones)) {
                    showShortToast("请输入手机号");
                    return;
                }
                if (phones.length() != 11) {
                    showShortToast("请输入正确的手机号");
                    return;
                }
                BaseApplication.instance.setRegisterCountDown(60 * 1000, 1000, sendauthcode, 1);
//                sendAuthCode();
                break;
            default:
                break;
        }
    }

    @Override
    public void setListener() {
        next.setOnClickListener(this);
        phone.setAddSearchTextChangedListener(this);
        authcode.addTextChangedListener(new MyTextWatcher());
        sendauthcode.setOnClickListener(this);
        readprotocol.setOnClickListener(this);
        tvHow.setOnClickListener(this);
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
