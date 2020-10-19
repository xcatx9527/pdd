package com.Allicnce.taobaoAlliance.animator.interpolator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.base.BaseActivity;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:49
 * @Email 1016224774@qq.com
 * @Description 演示TypeEvaluator
 */
public class EvaluatorActivity extends BaseActivity {

    @Bind(R.id.drawview)
    EvaluatorView evaluatorView;
    @Bind(R.id.circle)
    View circle;
    @Bind(R.id.list)
    ListView list;
    private EvaluatorAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_evaluator;
    }

    @Override
    public void initView() {
        adapter = new EvaluatorAdapter(this);
        list.setAdapter(adapter);
    }

    @Override
    public void onChildClick(View v) {

    }

    @Override
    public void setListener() {
        list.setOnItemClickListener((adapterView, view, position, l) -> {
            evaluatorView.clear();
            circle.setTranslationX(0);
            circle.setTranslationY(0);
            ObjectAnimator animator = ObjectAnimator.ofFloat(circle, "translationY", 0, dipToPixels(EvaluatorActivity.this, -(160 - 3)));
            animator.addUpdateListener(animation -> evaluatorView.drawPoint(animation.getCurrentPlayTime(), animation.getDuration(), (float) animation.getAnimatedValue() - dipToPixels(EvaluatorActivity.this, 60)));
            animator.setEvaluator(EasingFunction.values()[position]);
            animator.setDuration(1200);
            animator.start();
        });
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}
