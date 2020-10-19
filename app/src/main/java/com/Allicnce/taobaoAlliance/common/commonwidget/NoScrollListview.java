package com.Allicnce.taobaoAlliance.common.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author: ChenYang
 * @date: 2017-10-24 15:11
 * @Email 1016224774@qq.com
 * @Description 
 */
public class NoScrollListview extends ListView {
    public NoScrollListview(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public NoScrollListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}