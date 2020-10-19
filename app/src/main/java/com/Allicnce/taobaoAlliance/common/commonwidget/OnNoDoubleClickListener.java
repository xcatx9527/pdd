package com.Allicnce.taobaoAlliance.common.commonwidget;

import android.view.View;

import java.util.Calendar;

/**
 * @author: ChenYang
 * @date: 2017-10-24 15:11
 * @Email 1016224774@qq.com
 * @Description 
 */
public abstract class OnNoDoubleClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);

}