package com.Allicnce.taobaoAlliance.animator;

import android.view.View;

import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:53
 * @Email 1016224774@qq.com
 * @Description
 */
public class AnimationListener {

    private AnimationListener(){}

    public interface Start{
        void onAnimationStart(List<View> views);
    }

    public interface Stop{
        void onAnimationStop(List<View> views);
    }

    public interface Update<V extends View>{
        void update(V view, float value);
    }
}
