package com.Allicnce.taobaoAlliance.model;

import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:59
 * @Email 1016224774@qq.com
 * @Description
 */

public class TopListModel {


    /**
     * priceAll : 41.0
     * returnCode : 0000
     * priceAllQueryContent : 总销量
     * returnMessage : 执行成功
     * gameList : [{"priceAll":4340,"saleStopDate2String":"","picSmallUrl":"http://10.0.0.235:8801/nfs/nullnull","prizeClosingDate2String":"","stateCodeLabel":"","picUrl":"http://10.0.0.235:8801/nfs/nullnull","price":10,"marketDate2String":"","name":"小彩票1","checked":false,"lotteryTypeCodeLabel":"","id":"1","underDate2String":""},{"priceAll":1700,"saleStopDate2String":"","picSmallUrl":"http://10.0.0.235:8801/nfs/nullnull","prizeClosingDate2String":"","stateCodeLabel":"","picUrl":"http://10.0.0.235:8801/nfs/nullnull","price":10,"marketDate2String":"","name":"小彩票2","checked":false,"lotteryTypeCodeLabel":"","id":"22","underDate2String":""}]
     */
    public double priceAll;
    public String returnCode;
    public String priceAllQueryContent;
    public String returnMessage;
    public List<GameListEntity> gameList;

    public class GameListEntity {
        /**
         * priceAll : 4340.0
         * saleStopDate2String :
         * picSmallUrl : http://10.0.0.235:8801/nfs/nullnull
         * prizeClosingDate2String :
         * stateCodeLabel :
         * picUrl : http://10.0.0.235:8801/nfs/nullnull
         * price : 10
         * marketDate2String :
         * name : 小彩票1
         * checked : false
         * lotteryTypeCodeLabel :
         * id : 1
         * underDate2String :
         */
        public int priceAll;
        public String saleStopDate2String;
        public String picSmallUrl;
        public String prizeClosingDate2String;
        public String stateCodeLabel;
        public String picUrl;
        public int price;
        public String marketDate2String;
        public String name;
        public boolean checked;
        public String lotteryTypeCodeLabel;
        public String id;
        public String underDate2String;
    }
}
