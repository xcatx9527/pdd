package com.Allicnce.taobaoAlliance.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import androidx.annotation.IntDef;
import androidx.annotation.IntRange;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:53
 * @Email 1016224774@qq.com
 * @Description
 */
public class ObjectAnimatorUtils {
    private static final long DEFAULT_DURATION = 3000;

    private List<AnimationBuilder> animationList = new ArrayList<>();
    private long duration = DEFAULT_DURATION;
    private long startDelay = 0;
    private Interpolator interpolator = null;

    private int repeatCount = 0;
    private int repeatMode = ValueAnimator.RESTART;

    private AnimatorSet animatorSet;
    private View waitForThisViewHeight = null;

    private AnimationListener.Start startListener;
    private AnimationListener.Stop stopListener;

    private ObjectAnimatorUtils prev = null;
    private ObjectAnimatorUtils next = null;
    public static View[] views;

    /**
     * The interface Repeat mode.
     */
    @IntDef(flag = false, value = {ValueAnimator.RESTART, ValueAnimator.REVERSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
        //代替enum，据说枚举类极其耗费内存
    }

    public static AnimationBuilder animate(View... view) {
        ObjectAnimatorUtils viewAnimator = new ObjectAnimatorUtils();
        views = view;
        return viewAnimator.addAnimationBuilder(view);
    }

    public AnimationBuilder thenAnimate(View... views) {
        ObjectAnimatorUtils nextViewAnimator = new ObjectAnimatorUtils();
        ObjectAnimatorUtils.views = views;
        this.next = nextViewAnimator;
        nextViewAnimator.prev = this;
        return nextViewAnimator.addAnimationBuilder(views);
    }

    public AnimationBuilder addAnimationBuilder(View... views) {
        AnimationBuilder animationBuilder = new AnimationBuilder(this, views);
        animationList.add(animationBuilder);
        return animationBuilder;
    }

    protected AnimatorSet createAnimatorSet() {
        List<Animator> animators = new ArrayList<>();
        List<View> views = new ArrayList<>();
        for (AnimationBuilder animationBuilder : animationList) {
            List<Animator> animatorList = animationBuilder.createAnimators();
            if (animationBuilder.getSingleInterpolator() != null) {
                for (Animator animator : animatorList) {
                    animator.setInterpolator(animationBuilder.getSingleInterpolator());
                }
            }
            views.addAll(Arrays.asList(animationBuilder.getViews()));
            animators.addAll(animatorList);
        }

        for (AnimationBuilder animationBuilder : animationList) {
            if (animationBuilder.isWaitForHeight()) {
                waitForThisViewHeight = animationBuilder.getView();
                break;
            }
        }

        for (Animator animator : animators) {
            if (animator instanceof ValueAnimator) {
                ValueAnimator valueAnimator = (ValueAnimator) animator;
                valueAnimator.setRepeatCount(repeatCount);
                valueAnimator.setRepeatMode(repeatMode);
            }
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);

        animatorSet.setDuration(duration);
        animatorSet.setStartDelay(startDelay);
        if (interpolator != null)
            animatorSet.setInterpolator(interpolator);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (startListener != null) startListener.onAnimationStart(views);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (stopListener != null) stopListener.onAnimationStop(views);
                if (next != null) {
                    next.prev = null;
                    next.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return animatorSet;
    }

    public ObjectAnimatorUtils start() {
        if (prev != null) {
            prev.start();
        } else {
            animatorSet = createAnimatorSet();

            if (waitForThisViewHeight != null) {
                waitForThisViewHeight.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        animatorSet.start();
                        waitForThisViewHeight.getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
            } else {
                animatorSet.start();
            }
        }
        return this;
    }

    public void cancel() {
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        if (next != null) {
            next.cancel();
            next = null;
        }
    }

    public ObjectAnimatorUtils duration(long duration) {
        this.duration = duration;
        return this;
    }

    public ObjectAnimatorUtils startDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    /**
     * Repeat count of animation.
     *
     * @param repeatCount the repeat count
     * @return the view animation
     */
    public ObjectAnimatorUtils repeatCount(@IntRange(from = -1) int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    /**
     * Repeat mode view animation.
     *
     * @param repeatMode the repeat mode
     * @return the view animation
     */
    public ObjectAnimatorUtils repeatMode(@RepeatMode int repeatMode) {
        this.repeatMode = repeatMode;
        return this;
    }

    public ObjectAnimatorUtils onStart(AnimationListener.Start startListener) {
        this.startListener = startListener;
        return this;
    }

    public ObjectAnimatorUtils onStop(AnimationListener.Stop stopListener) {
        this.stopListener = stopListener;
        return this;
    }

    /**
     * Interpolator view animator.
     *
     * @param interpolator the interpolator
     * @return the view animator
     * @link https://github.com/cimi-chen/EaseInterpolator
     */
    public ObjectAnimatorUtils interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

}
