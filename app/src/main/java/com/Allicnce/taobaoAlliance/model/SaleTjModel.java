package com.Allicnce.taobaoAlliance.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:59
 * @Email 1016224774@qq.com
 * @Description
 */

public class SaleTjModel implements Serializable{
    /**
     * priceAll : 0.0
     * returnCode : 0000
     * lotteryOrderStatList : [{"allMoney":5274,"weChatMoney":0,"cashMoney":5274,"otherMoney":0,"id":13,"payDate":"2017-10-10"},{"allMoney":26,"weChatMoney":0,"cashMoney":26,"otherMoney":0,"id":12,"payDate":"2017-10-09"},{"allMoney":18633,"weChatMoney":0,"cashMoney":18633,"otherMoney":0,"id":11,"payDate":"2017-09-30"},{"allMoney":455,"weChatMoney":35,"cashMoney":420,"otherMoney":0,"id":10,"payDate":"2017-09-29"},{"allMoney":355,"weChatMoney":0,"cashMoney":355,"otherMoney":0,"id":9,"payDate":"2017-09-28"},{"allMoney":330,"weChatMoney":0,"cashMoney":330,"otherMoney":0,"id":8,"payDate":"2017-09-27"},{"allMoney":85,"weChatMoney":0,"cashMoney":85,"otherMoney":0,"id":7,"payDate":"2017-09-15"},{"allMoney":875,"weChatMoney":0,"cashMoney":875,"otherMoney":0,"id":6,"payDate":"2017-09-14"},{"allMoney":1380,"weChatMoney":0,"cashMoney":1380,"otherMoney":0,"id":5,"payDate":"2017-09-13"},{"allMoney":1,"weChatMoney":1,"cashMoney":0,"otherMoney":0,"id":4,"payDate":"2017-08-08"}]
     * lotteryOrderStat : {"allMoney":52719,"weChatMoney":0,"cashMoney":52719,"otherMoney":0,"id":1}
     * returnMessage : 执行成功
     */
    public String priceAll;
    public String returnCode;
    public List<LotteryOrderStatListEntity> lotteryOrderStatList;
    public LotteryOrderStatEntity lotteryOrderStat;
    public String returnMessage;

    public class LotteryOrderStatListEntity {
        /**
         * allMoney : 5274.0
         * weChatMoney : 0.0
         * cashMoney : 5274.0
         * otherMoney : 0.0
         * id : 13.0
         * payDate : 2017-10-10
         */
        public String allMoney;
        public String weChatMoney;
        public String cashMoney;
        public String otherMoney;
        public String id;
        public String payDate;
    }

    public class LotteryOrderStatEntity {
        /**
         * allMoney : 52719.0
         * weChatMoney : 0.0
         * cashMoney : 52719.0
         * otherMoney : 0.0
         * id : 1.0
         */
        public String allMoney;
        public String weChatMoney;
        public String cashMoney;
        public String otherMoney;
        public String id;
    }
}
