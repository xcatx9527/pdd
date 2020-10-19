package com.Allicnce.taobaoAlliance.animator.interpolator;


/**
 * @author: ChenYang
 * @date: 2017-10-24 17:50
 * @Email 1016224774@qq.com
 * @Description 工具类，将自定义的函数快速封装成AbstractFunction
 */
public class Functions {

    public static AbstractFunction with(final IFunction function) {
        return new AbstractFunction() {
            @Override
            public float getValue(float input) {
                return function.getValue(input);
            }
        };
    }
}
