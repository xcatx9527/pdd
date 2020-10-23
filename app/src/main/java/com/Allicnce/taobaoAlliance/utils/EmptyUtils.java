package com.Allicnce.taobaoAlliance.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author chenyang08
 * @date 2020/7/22
 * @Description: 判空工具类
 */
public class EmptyUtils {
    public static boolean notEmpty(Object... objects) {
        return !isEmpty(objects);
    }


    public static boolean isEmpty(Object... objects) {

        for (Object object : objects) {
            if (object == null) {
                return true;
            }
            if (object instanceof String) {
                if (object == null || object.equals("")) {
                    return true;
                }
            }
            if (object instanceof List) {
                if (object == null || ((List) object).isEmpty()) {
                    return true;
                }
            }
            if (object instanceof Map) {
                if (object == null || ((Map<Object, Object>) object).isEmpty()) {
                    return true;
                }
            }
            if (object instanceof Collection) {
                if (object == null || ((Collection) object).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }
}
