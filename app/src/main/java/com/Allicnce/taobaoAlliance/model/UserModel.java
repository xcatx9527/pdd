package com.Allicnce.taobaoAlliance.model;

/**
 * @author Administrator
 * @time 2018/5/18$
 * @descrition:
 */

public class UserModel {

    /**
     * code : 0
     * data : {"totalReward":"0.00","unReward":"0.00","expReward":"0.00","bindWx":1,"wxid":"wxid_5jrgn64dxxlv22","userId":100000}
     * desc : 成功
     */
    public int code;
    public DataEntity data;
    public String desc;

    public class DataEntity {
        /**
         * totalReward : 0.00
         * unReward : 0.00
         * expReward : 0.00
         * bindWx : 1
         * wxid : wxid_5jrgn64dxxlv22
         * userId : 100000
         */
        public String totalReward;
        public String unReward;
        public String expReward;
        public int bindWx;
        public String wxid;
        public String userId;
    }
}
