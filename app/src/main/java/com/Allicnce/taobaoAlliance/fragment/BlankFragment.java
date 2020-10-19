package com.Allicnce.taobaoAlliance.fragment;


import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.animator.AnimationListener;
import com.Allicnce.taobaoAlliance.animator.ObjectAnimatorUtils;
import com.Allicnce.taobaoAlliance.common.base.BaseFragment;

import java.util.List;


public class BlankFragment extends BaseFragment implements AnimationListener.Stop {


    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;
    @Bind(R.id.tv5)
    TextView tv5;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    public void initView() {
    }


    @Override
    public void onChildClick(View v) {
        ObjectAnimatorUtils.animate(tv1).rotationX(180, 0).pivotY(0).alpha(1, 1).onAnimationStop(this).duration(500)
                .thenAnimate(tv2).rotationX(180, 0).pivotY(0).alpha(1, 1).onAnimationStop(this).duration(500)
                .thenAnimate(tv3).rotationX(180, 0).pivotY(0).alpha(1, 1).onAnimationStop(this).duration(500)
                .thenAnimate(tv4).rotationX(180, 0).pivotY(0).alpha(1, 1).onAnimationStop(this).duration(500)
                .thenAnimate(tv5).rotationX(180, 0).pivotY(0).alpha(1, 1).onAnimationStop(this).duration(500)
                .start();

    }

    @Override
    public void setListener() {
        tv1.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv4.setOnClickListener(this);
    }


    @Override
    public void onAnimationStop(List<View> views) {
        switch (views.get(0).getId()) {
            case R.id.tv1:
                Log.e("--^_^-->", "tv1");
//                new android.os.Handler().postDelayed(() ->tv1.setAlpha(0), 1000);
                break;
            case R.id.tv2:
                Log.e("--^_^-->", "tv2");
                new android.os.Handler().postDelayed(() -> tv2.setAlpha(0), 1000);

                break;
            case R.id.tv3:
                Log.e("--^_^-->", "tv3");
                new android.os.Handler().postDelayed(() -> tv3.setAlpha(0), 1000);

                break;
            case R.id.tv4:
                Log.e("--^_^-->", "tv4");
                new android.os.Handler().postDelayed(() -> tv4.setAlpha(0), 1000);

                break;
            case R.id.tv5:
                Log.e("--^_^-->", "tv5");
                new android.os.Handler().postDelayed(() -> tv5.setAlpha(0), 1000);

                break;
        }
    }
}
