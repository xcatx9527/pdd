package com.Allicnce.taobaoAlliance.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:57
 * @Email 1016224774@qq.com
 * @Description
 */
public class NotificationModel implements Serializable{

    /**
     * returnCode : 0000
     * proprietorMsgList : [{"createDatetime":1499239992000,"createDatetimeStr":"2017-07-05 15:33:12","id":1,"title":"343","content":"rfrerere"},{"createDatetime":1499240908000,"createDatetimeStr":"2017-07-05 15:48:28","id":2,"title":"333","content":"4444"}]
     * returnMessage : 执行成功
     */
    public String returnCode;
    public List<ProprietorMsgListEntity> proprietorMsgList;
    public String returnMessage;

    public class ProprietorMsgListEntity implements Serializable{
        /**
         * createDatetime : 1499239992000
         * createDatetimeStr : 2017-07-05 15:33:12
         * id : 1
         * title : 343
         * content : rfrerere
         */
        public long createDatetime;
        public String createDatetimeStr;
        public String id;
        public String title;
        public String content;
        public boolean hasread;
    }
}
