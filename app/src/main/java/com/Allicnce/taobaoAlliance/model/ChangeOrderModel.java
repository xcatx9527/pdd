package com.Allicnce.taobaoAlliance.model;

import java.util.ArrayList;

/**
 * @author Administrator
 * @time 2018/5/23$
 * @descrition:
 */

public class ChangeOrderModel {

    /**
     * code : 0
     * data : {"orderId":100001,"gameAccount":"1234546","gameImgList":["http://118.145.5.141:4080/shell/gameTaskApp/100002/100001/IconMission.png","http://118.145.5.141:4080/shell/gameTaskApp/100002/100001/wj_006_wojin.png"],"status":1}
     * desc : 成功
     */
    public int code;
    public DataEntity data;
    public String desc;

    public class DataEntity {
        /**
         * orderId : 100001
         * gameAccount : 1234546
         * gameImgList : ["http://118.145.5.141:4080/shell/gameTaskApp/100002/100001/IconMission.png","http://118.145.5.141:4080/shell/gameTaskApp/100002/100001/wj_006_wojin.png"]
         * status : 1
         */
        public int orderId;
        public String gameAccount;
        public ArrayList<String> gameImgList;
        public int status;
    }
}
