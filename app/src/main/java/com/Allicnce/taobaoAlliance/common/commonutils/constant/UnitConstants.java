package com.Allicnce.taobaoAlliance.common.commonutils.constant;

import android.annotation.SuppressLint;
import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/13
 *     desc  : 计量单位相关常量
 * </pre>
 */
public final class UnitConstants {

    /**
     * 毫秒与毫秒的倍数
     */
    public static final int MSEC = 1;
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC  = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN  = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 86400000;
    /**
     * Byte与Byte的倍数
     */
    public static final int BYTE = 1;
    /**
     * KB与Byte的倍数
     */
    public static final int KB   = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final int MB   = 1048576;
    /**
     * GB与Byte的倍数
     */
    public static final int GB   = 1073741824;



    @SuppressLint("UniqueConstants")
    @IntDef({MSEC, SEC, MIN, HOUR, DAY,BYTE, KB, MB, GB})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
