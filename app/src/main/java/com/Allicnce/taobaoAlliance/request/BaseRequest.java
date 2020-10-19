package com.Allicnce.taobaoAlliance.request;


import com.Allicnce.taobaoAlliance.common.commonutils.AppUtils;

import java.io.Serializable;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:06
 * @Email 1016224774@qq.com
 * @Description
 */
public abstract class BaseRequest implements Serializable {

    public String appType = "90"; //"appType"（app类型）, “appVersion”（app版本号），“dt”（客户端时间串）, “appLng”（经度）,“appLat”（维度）
    public String appVersion = AppUtils.getAppVersionName();
    public String dt;
    public String appLng;
    public String appLat;


}
