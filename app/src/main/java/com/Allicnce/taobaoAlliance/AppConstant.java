package com.Allicnce.taobaoAlliance;

import android.os.Environment;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:03
 * @Email 1016224774@qq.com
 * @Description
 */
public class AppConstant {
    public static String DOWNAPPPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/taskDown/";

    public static String SERVICE_URL = BuildConfig.SERVER_HOST;
    public static String APPIDPATH = "/storage/emulated/0/Android/data/com.tencent.mobileqq.file/file";
    public static String APPID;
    public static String LOGIN_URL = SERVICE_URL + "/gameApp.do?method=login";//登陆
    public static String GETERWEIMA_URL = SERVICE_URL + "/gameApp.do?method=getRobotWxCode";//获取二维码
    public static String TASK_LIST = SERVICE_URL + "/gameApp.do?method=listTask";//任务列表
    public static String TASK_DETAILS = SERVICE_URL + "/gameApp.do?method=getTaskDetail";//任务详情
    public static String GETTASK_URL = SERVICE_URL + "/gameApp.do?method=startGame";//获取任务
    public static String SUBMITDATA_URL = SERVICE_URL + "/gameApp.do?method=submitData";//提交资料
    public static String GETUSER_URL = SERVICE_URL + "/gameApp.do?method=getUser";//获取用户信息
    public static String TAKEMONEY_URL = SERVICE_URL + "/gameApp.do?method=withdraw";//提现
    public static String GETORDERLIST_URL = SERVICE_URL + "/gameApp.do?method=listOrder";//订单列表
    public static String GETORDER_URL = SERVICE_URL + "/gameApp.do?method=getOrder";//订单列表

    public static String UPDATEPIC_URL = SERVICE_URL + "/gameApp.do?method=submitData";//上传图片
    public static String CHECKVERSION_URL = SERVICE_URL + "/mobile/appVersion/updateVersion.do";//升级检查

}
