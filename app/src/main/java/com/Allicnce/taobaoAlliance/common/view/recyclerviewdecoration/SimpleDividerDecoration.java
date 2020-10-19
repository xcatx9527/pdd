package com.Allicnce.taobaoAlliance.common.view.recyclerviewdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.ColorRes;
import androidx.recyclerview.widget.RecyclerView;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.DisplayUtil;

/**
 * @author: ChenYang
 * @date: 2017-11-23 14:58
 * @Email 1016224774@qq.com
 * @Description
 */

public class SimpleDividerDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;

    public SimpleDividerDecoration(Context context) {
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(R.color.md_grey200));
        dividerHeight = DisplayUtil.dp2px(1);
    }

    public SimpleDividerDecoration(Context context, @ColorRes int color, int height) {
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(color));
        dividerHeight = DisplayUtil.dp2px(height);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}