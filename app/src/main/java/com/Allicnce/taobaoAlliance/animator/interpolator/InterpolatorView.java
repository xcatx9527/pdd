package com.Allicnce.taobaoAlliance.animator.interpolator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:51
 * @Email 1016224774@qq.com
 * @Description 函数曲线图
 */
public class InterpolatorView extends View {

    public int width;
    public int height;
    public int blankTB;//blank of top or bottom
    public int blankLR;//blank of left or right

    private Context context;
    private Paint linePaint;
    private Paint pathPaint;
    private Path path;

    public InterpolatorView(Context context) {
        this(context, null);
    }

    public InterpolatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        width = dip2px(100); //from layout, 100dp
        height = dip2px(100); //from layout
        blankTB = height * 2 / 7;//why?
        blankLR = 0;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(1);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.DKGRAY);

        pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setStrokeWidth(2);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setColor(Color.RED);

        path = new Path();
    }

    public void setDurationAndInterpolator(long duration, Interpolator interpolator) {
        if (duration <= 0)
            return;
        if (interpolator == null)
            return;

        int w = width - blankLR * 2;
        int h = height - blankTB * 2;

        path.reset();
        path.moveTo(blankLR, height - blankTB);
        int factor = (int) (duration / w + (duration % w > 0 ? 1 : 0));
        int i = 0;
        for (; i < duration; i += factor) {
            path.lineTo(i / factor + blankLR, h - interpolator.getInterpolation((float) i / duration) * h + blankTB);
        }

//        if (!(interpolator instanceof EaseBreathInterpolator))
//            path.lineTo(i / factor + blankLR, blankTB);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(0, blankTB, width, blankTB, linePaint);//float startX, float startY, float stopX, float stopY, Paint paint
        canvas.drawLine(0, height - blankTB, width, height - blankTB, linePaint);

        canvas.drawPath(path, pathPaint);
    }

    private int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
