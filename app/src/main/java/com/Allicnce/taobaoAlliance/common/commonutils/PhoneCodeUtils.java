package com.Allicnce.taobaoAlliance.common.commonutils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * author NullPointer
 * create time 2016/9/20$ 0:50$
 * descrition:
 */

public class PhoneCodeUtils {
    public static PhoneCodeUtils instance = new PhoneCodeUtils();
    private static TimeCount mTimeCount;// 注册验证码计时

    private static TimeCount mForgetTimeCount;// 忘记验证码计时


    /*
     * 设置注册验证码倒计时
     */
    public static void setRegisterCountDown(long millisInFuture, long countDownInterval, Button mButton, int type) {
        if (type == 1) {
            mTimeCount = new TimeCount(millisInFuture, countDownInterval, mButton, type);
            mTimeCount.start();
        }
        if (type == 2) {
            mForgetTimeCount = new TimeCount(millisInFuture, countDownInterval, mButton, type);
            mForgetTimeCount.start();
        }
    }

    public static void cancle(int type) {
        if (type == 1 && mTimeCount != null) {
            mTimeCount.cancel();
        }
        if (type == 2 && mForgetTimeCount != null) {
            mForgetTimeCount.cancel();
        }
    }

    /* 定义一个倒计时的内部类 */
    public static class TimeCount extends CountDownTimer {

        private long millisUntilFinished;

        private boolean isFinish;

        private Button mButton;

        private int type;// 1为注册 2为忘记密码

        public long getMillisUntilFinished() {
            return millisUntilFinished;
        }

        public boolean isFinish() {
            return isFinish;
        }


        public void setButton(Button mButton) {
            this.mButton = mButton;
        }

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public TimeCount(long millisInFuture, long countDownInterval, Button mButton, int type) {
            super(millisInFuture, countDownInterval);
            this.mButton = mButton;
            this.type = type;
        }

        @Override
        public void onFinish() {
            isFinish = true;
            mButton.setClickable(true);
            mButton.setText("获取验证码");
            // mButton.setBackgroundResource(R.drawable.verification_code_selector);
            mButton = null;
            if (type == 1) {
                mTimeCount = null;
            }
            if (type == 2) {
                mForgetTimeCount = null;
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
            this.millisUntilFinished = millisUntilFinished;
            isFinish = false;
            mButton.setClickable(false);
            // mButton.setBackgroundResource(R.drawable.forgot_cancel_normail);
            mButton.setText(millisUntilFinished / 1000 + "秒后重发");
        }
    }
}
