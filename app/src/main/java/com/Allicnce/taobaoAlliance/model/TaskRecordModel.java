package com.Allicnce.taobaoAlliance.model;

import java.util.ArrayList;

/**
 * @author Administrator
 * @time 2018/5/18$
 * @descrition:
 */

public class TaskRecordModel {


    /**
     * code : 0
     * data : [{"date":"2018-05-18","totalAmount":"1.50","gameLogo":"http://118.145.5.141:4080/shell/gameTaskApp/task/logo1.png","gameName":"蜀剑封神传","orderId":100001,"rewardAmount":"1.50","time":1526637638880,"type":0,"status":1}]
     * desc : 成功
     */
    public int code;
    public ArrayList<DataEntity> data;
    public String desc;

    public class DataEntity {
        /**
         * date : 2018-05-18
         * totalAmount : 1.50
         * gameLogo : http://118.145.5.141:4080/shell/gameTaskApp/task/logo1.png
         * gameName : 蜀剑封神传
         * orderId : 100001
         * rewardAmount : 1.50
         * time : 1526637638880
         * type : 0
         * status : 1
         */
        public String date;
        public String totalAmount;
        public String gameLogo;
        public String gameName;
        public String orderId;
        public String rewardAmount;
        public long time;
        public int type;
        public int status;
    }
}
