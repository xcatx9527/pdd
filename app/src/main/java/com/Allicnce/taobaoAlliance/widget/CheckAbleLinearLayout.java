package com.Allicnce.taobaoAlliance.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.DisplayUtil;


/**
 * @author: ChenYang
 * @date: 2017-10-24 18:04
 * @Email 1016224774@qq.com
 * @Description 
 */
public class CheckAbleLinearLayout extends RelativeLayout implements Checkable {
    boolean mChecked = false;

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private ImageView imageView;

    public CheckAbleLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.selectimg);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp.height = DisplayUtil.dp2px( 13);
        lp.width = DisplayUtil.dp2px(13);
        imageView.setLayoutParams(lp);
        imageView.setVisibility(GONE);
        addView(imageView);
        setClickable(true);
    }

    public CheckAbleLinearLayout(Context context) {
        super(context);
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
        if (checked) {
            imageView.setVisibility(VISIBLE);
        } else {
            imageView.setVisibility(GONE);
        }
    }

    @Override
    public void toggle() {
        if (!mChecked) {
            imageView.setVisibility(VISIBLE);
        } else {
            imageView.setVisibility(GONE);
        }
        mChecked = !mChecked;
        refreshDrawableState();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

}
