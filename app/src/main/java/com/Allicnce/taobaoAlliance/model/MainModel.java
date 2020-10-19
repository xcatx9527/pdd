package com.Allicnce.taobaoAlliance.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:57
 * @Email 1016224774@qq.com
 * @Description
 */
public class MainModel  implements Serializable{


    public String returnCode;
    public List<ProprietorMsgListEntity> proprietorMsgList;
    public List<LotteryOrderListEntity> lotteryOrderList;
    public double priceAllToday;
    public String returnMessage;
    public List<GameListEntity> gameList;
    public ProprietorEntity proprietor;
    public List<TerminalListEntity> terminalList;

    public class ProprietorMsgListEntity  implements Serializable{
        /**
         * createDatetime : 2017-11-13 10:15:01
         * onlineBeginDatetime : 2017-11-13 10:15:04
         * typeCodeLabel : 系统消息
         * createDatetimeNYRStr : 2017-11-13
         * onlineBeginDatetimeStr : 2017-11-13 10:15:04
         * stateCode : 1
         * createDatetimeStr : 2017-11-13 10:15:01
         * id : 20
         * title : 测试系统消息
         * stateCodeLabel : 已发布
         * content : 首页
         * typeCode : 0
         */
        public String createDatetime;
        public String onlineBeginDatetime;
        public String typeCodeLabel;
        public String createDatetimeNYRStr;
        public String onlineBeginDatetimeStr;
        public String stateCode;
        public String createDatetimeStr;
        public int id;
        public String title;
        public String stateCodeLabel;
        public String content;
        public String typeCode;
    }

    public class LotteryOrderListEntity  implements Serializable{
        /**
         * orderId :
         * appendLabel : 未追加
         * betTypeCodeLabel :
         * createDatetimeStr : 2017-11-10 18:06:02
         * terminalId : 17900011
         * payMoney : 14.0
         * lotteryTypeCode : 1
         * payTypeCodeLabel : 现金
         * lotteryTypeCodeLabel : 体彩
         * proprietorId : 39
         * id : 00171110557
         * proprietor : {"lottoSiteNo":"6501003010","name":"刘星","areaOperate":{"lotteryTypeLabel":"","areaName":"北京区域","id":36},"id":39,"isSignCodeLabel":"","accountBalance":0,"account":"13911460705"}
         * orderNo : 000002
         * createDatetimeNYRStr : 2017-11-10
         * totalMoney : 14.0
         * lotteryGameTypeCode : 0
         * multiple : 0
         * lotteryGameTypeCodeLabel : 即开
         * payUserId :
         * terminal : {"useStateCodeLabel":"","deployDatetimeStr":"","languageTypeCode":"zh","saleMoneySum":0,"deployTypeCodeLabel":"","id":17900011,"deployStateCodeLabel":""}
         * stateCodeLabel : 已取消
         * lastModifyDatetimeStr : 2017-11-10 18:06:04
         * createDatetime : 2017-11-10 18:06:02
         * payTypeCode : 0
         * lotteryPeriodId : 0
         * betNum : 7
         * lastModifyDatetime : 2017-11-10 18:06:04
         * stateCode : 5
         * website_id : 21
         * append : 0
         * status : 1
         * lotteryOrderDetailList : [{"game":{"saleStopDate2String":"","picSmallUrl":"http://app.caiyingkeji.vizhuo.cn/nfs//images/lotteryPic/20171101/201711010930436906841.png","ticketHeight":50,"picName":"201711010930436656534.png","prizeClosingDate2String":"","stateCodeLabel":"启用状态","picPath":"/images/lotteryPic/20171101/","picUrl":"http://app.caiyingkeji.vizhuo.cn/nfs//images/lotteryPic/20171101/201711010930436656534.png","picSmallName":"201711010930436906841.png","lotteryTypeCode":"1","price":2,"gameDes":"猜猜看","marketDate2String":"2015-10-01","ticketHeightLabel":"100*50mm","name":"猜猜看","checked":false,"lotteryTypeCodeLabel":"体彩","marketDate":"2015-10-01 00:00:00","stateCode":"1","id":43,"underDate2String":"","picSmallPath":"/images/lotteryPic/20171101/"},"orderId":"00171110557","betContentBlue":"","appendLabel":"","price":2,"count":7,"betTypeCodeLabel":"","stateCode":"0","id":1284,"stateCodeLabel":"未出票","betContentRed":""}]
         */
        public String orderId;
        public String appendLabel;
        public String betTypeCodeLabel;
        public String createDatetimeStr;
        public int terminalId;
        public double payMoney;
        public String lotteryTypeCode;
        public String payTypeCodeLabel;
        public String lotteryTypeCodeLabel;
        public int proprietorId;
        public String id;
        public ProprietorEntity proprietor;
        public String orderNo;
        public String createDatetimeNYRStr;
        public double totalMoney;
        public String lotteryGameTypeCode;
        public int multiple;
        public String lotteryGameTypeCodeLabel;
        public String payUserId;
        public TerminalEntity terminal;
        public String stateCodeLabel;
        public String lastModifyDatetimeStr;
        public String createDatetime;
        public int payTypeCode;
        public int lotteryPeriodId;
        public int betNum;
        public String lastModifyDatetime;
        public String stateCode;
        public int website_id;
        public String append;
        public String status;
        public List<LotteryOrderDetailListEntity> lotteryOrderDetailList;

        public class ProprietorEntity implements Serializable {
            /**
             * lottoSiteNo : 6501003010
             * name : 刘星
             * areaOperate : {"lotteryTypeLabel":"","areaName":"北京区域","id":36}
             * id : 39
             * isSignCodeLabel :
             * accountBalance : 0.0
             * account : 13911460705
             */

            public String lottoSiteNo;
            public String name;
            public AreaOperateEntity areaOperate;
            public int id;
            public String isSignCodeLabel;
            public double accountBalance;
            public String account;
            public String wecharOpenId;

            public class AreaOperateEntity  implements Serializable{
                /**
                 * lotteryTypeLabel :
                 * areaName : 北京区域
                 * id : 36
                 */
                public String lotteryTypeLabel;
                public String areaName;
                public int id;
            }
        }

        public class TerminalEntity  implements Serializable{
            /**
             * useStateCodeLabel :
             * deployDatetimeStr :
             * languageTypeCode : zh
             * saleMoneySum : 0.0
             * deployTypeCodeLabel :
             * id : 17900011
             * deployStateCodeLabel :
             */
            public String useStateCodeLabel;
            public String deployDatetimeStr;
            public String languageTypeCode;
            public double saleMoneySum;
            public String deployTypeCodeLabel;
            public int id;
            public String deployStateCodeLabel;
        }

        public class LotteryOrderDetailListEntity  implements Serializable{
            /**
             * game : {"saleStopDate2String":"","picSmallUrl":"http://app.caiyingkeji.vizhuo.cn/nfs//images/lotteryPic/20171101/201711010930436906841.png","ticketHeight":50,"picName":"201711010930436656534.png","prizeClosingDate2String":"","stateCodeLabel":"启用状态","picPath":"/images/lotteryPic/20171101/","picUrl":"http://app.caiyingkeji.vizhuo.cn/nfs//images/lotteryPic/20171101/201711010930436656534.png","picSmallName":"201711010930436906841.png","lotteryTypeCode":"1","price":2,"gameDes":"猜猜看","marketDate2String":"2015-10-01","ticketHeightLabel":"100*50mm","name":"猜猜看","checked":false,"lotteryTypeCodeLabel":"体彩","marketDate":"2015-10-01 00:00:00","stateCode":"1","id":43,"underDate2String":"","picSmallPath":"/images/lotteryPic/20171101/"}
             * orderId : 00171110557
             * betContentBlue :
             * appendLabel :
             * price : 2
             * count : 7
             * betTypeCodeLabel :
             * stateCode : 0
             * id : 1284
             * stateCodeLabel : 未出票
             * betContentRed :
             */
            public GameEntity game;
            public String orderId;
            public String betContentBlue;
            public String appendLabel;
            public int price;
            public int count;
            public String betTypeCodeLabel;
            public String stateCode;
            public int id;
            public String stateCodeLabel;
            public String betContentRed;

            public class GameEntity implements Serializable {
                /**
                 * saleStopDate2String :
                 * picSmallUrl : http://app.caiyingkeji.vizhuo.cn/nfs//images/lotteryPic/20171101/201711010930436906841.png
                 * ticketHeight : 50
                 * picName : 201711010930436656534.png
                 * prizeClosingDate2String :
                 * stateCodeLabel : 启用状态
                 * picPath : /images/lotteryPic/20171101/
                 * picUrl : http://app.caiyingkeji.vizhuo.cn/nfs//images/lotteryPic/20171101/201711010930436656534.png
                 * picSmallName : 201711010930436906841.png
                 * lotteryTypeCode : 1
                 * price : 2
                 * gameDes : 猜猜看
                 * marketDate2String : 2015-10-01
                 * ticketHeightLabel : 100*50mm
                 * name : 猜猜看
                 * checked : false
                 * lotteryTypeCodeLabel : 体彩
                 * marketDate : 2015-10-01 00:00:00
                 * stateCode : 1
                 * id : 43
                 * underDate2String :
                 * picSmallPath : /images/lotteryPic/20171101/
                 */
                public String saleStopDate2String;
                public String picSmallUrl;
                public int ticketHeight;
                public String picName;
                public String prizeClosingDate2String;
                public String stateCodeLabel;
                public String picPath;
                public String picUrl;
                public String picSmallName;
                public String lotteryTypeCode;
                public int price;
                public String gameDes;
                public String marketDate2String;
                public String ticketHeightLabel;
                public String name;
                public boolean checked;
                public String lotteryTypeCodeLabel;
                public String marketDate;
                public String stateCode;
                public int id;
                public String underDate2String;
                public String picSmallPath;
            }
        }
    }

    public class GameListEntity  implements Serializable{
        /**
         * priceAll : 180.0
         * saleStopDate2String :
         * picSmallUrl : http://app.caiyingkeji.vizhuo.cn/nfs/nullnull
         * prizeClosingDate2String :
         * stateCodeLabel :
         * picUrl : http://app.caiyingkeji.vizhuo.cn/nfs/nullnull
         * price : 30
         * marketDate2String :
         * ticketHeightLabel :
         * name : 黄金世界
         * checked : false
         * lotteryTypeCodeLabel :
         * id : 49
         * underDate2String :
         */
        public double priceAll;
        public String saleStopDate2String;
        public String picSmallUrl;
        public String prizeClosingDate2String;
        public String stateCodeLabel;
        public String picUrl;
        public int price;
        public String marketDate2String;
        public String ticketHeightLabel;
        public String name;
        public boolean checked;
        public String lotteryTypeCodeLabel;
        public int id;
        public String underDate2String;
    }

    public class ProprietorEntity  implements Serializable{
        /**
         * createUserId : 0
         * idCard : 110106199011116688
         * cooperate : 星星还是那个星星
         * adress : 翠微广场A座1408室
         * createDatetimeStr : 2017-10-30 14:04:56
         * terminalList : [{"useStateCodeLabel":"正常","terminalKey":"MWu+Y+hLORPkjPw1Imlsgg==","lng":"116.418324","lotteryDepotId":7,"deployStateCode":"1","languageTypeCode":"zh","languageId":46,"saleMoneySum":0,"terminalTypeId":12,"adress":"东长安街1号东方广场W2座1层","useStateCode":"1","areaNo":"110101","deployDatetimeStr":"2017-10-30","proprietorId":39,"deployTypeCodeLabel":"","id":17900011,"terminalBatchId":8,"lat":"39.915889","deployDatetime":"2017-10-30 15:23:33","deployStateCodeLabel":"已部署"}]
         * areaNo : 110108
         * id : 39
         * createDatetimeNYRStr : 2017-10-30
         * accountTypeCode : 0
         * mobile : 13911460705
         * areaOperate : {"lotteryTypeLabel":"","id":36}
         * isSignCode : 0
         * wecharAccount : lx888
         * alipayAccount : lx888@gmail.com
         * lastModifyDatetimeStr : 2017-10-30 14:04:56
         * createDatetime : 2017-10-30 14:04:56
         * lastModifyUserId : 0
         * name : 刘星
         * lastModifyDatetime : 2017-10-30 14:04:56
         * isSignCodeLabel : 未签约
         * sysAreaOperateId : 36
         * accountBalance : 0.0
         * account : 13911460705
         * status : 1
         */
        public int createUserId;
        public String idCard;
        public String cooperate;
        public String adress;
        public String createDatetimeStr;
        public List<TerminalListEntity> terminalList;
        public String areaNo;
        public int id;
        public String createDatetimeNYRStr;
        public String accountTypeCode;
        public String mobile;
        public AreaOperateEntity areaOperate;
        public String isSignCode;
        public String wecharAccount;
        public String alipayAccount;
        public String lastModifyDatetimeStr;
        public String createDatetime;
        public int lastModifyUserId;
        public String name;
        public String lastModifyDatetime;
        public String isSignCodeLabel;
        public int sysAreaOperateId;
        public double accountBalance;
        public String account;
        public String status;
        public String wecharOpenId;

        public class TerminalListEntity  implements Serializable{
            /**
             * useStateCodeLabel : 正常
             * terminalKey : MWu+Y+hLORPkjPw1Imlsgg==
             * lng : 116.418324
             * lotteryDepotId : 7
             * deployStateCode : 1
             * languageTypeCode : zh
             * languageId : 46
             * saleMoneySum : 0.0
             * terminalTypeId : 12
             * adress : 东长安街1号东方广场W2座1层
             * useStateCode : 1
             * areaNo : 110101
             * deployDatetimeStr : 2017-10-30
             * proprietorId : 39
             * deployTypeCodeLabel :
             * id : 17900011
             * terminalBatchId : 8
             * lat : 39.915889
             * deployDatetime : 2017-10-30 15:23:33
             * deployStateCodeLabel : 已部署
             */
            public String useStateCodeLabel;
            public String terminalKey;
            public String lng;
            public int lotteryDepotId;
            public String deployStateCode;
            public String languageTypeCode;
            public int languageId;
            public double saleMoneySum;
            public int terminalTypeId;
            public String adress;
            public String useStateCode;
            public String areaNo;
            public String deployDatetimeStr;
            public int proprietorId;
            public String deployTypeCodeLabel;
            public int id;
            public int terminalBatchId;
            public String lat;
            public String deployDatetime;
            public String deployStateCodeLabel;
        }

        public class AreaOperateEntity implements Serializable {
            /**
             * lotteryTypeLabel :
             * id : 36
             */
            public String lotteryTypeLabel;
            public int id;
        }
    }

    public class TerminalListEntity implements Serializable{
        /**
         * useStateCodeLabel : 正常
         * deployStateCode : 1
         * terminalTypeId : 12
         * adress : 东长安街1号东方广场W2座1层
         * terminalTheme : {"onlineDatetimeBeginStr":"2017-11-05","typeCodeLabel":"","onlineDatetimeBegin":"2017-11-05 00:00:00","onlineDatetimeEnd":"2017-12-09 00:00:00","name":"测试图标换","fileUrl":"http://app.caiyingkeji.vizhuo.cn/nfs/nullnull","languageTypeCodeLabel":"","id":48,"stateCodeLabel":"","onlineDatetimeEndStr":"2017-12-09"}
         * websiteId : 21
         * areaNo : 110101
         * deployDatetimeStr : 2017-10-30
         * onOffCode : 1
         * proprietorId : 39
         * deployTypeCodeLabel :
         * id : 17900011
         * proprietor : {"areaOperate":{"lotteryTypeLabel":"","areaName":"北京区域","id":36},"id":39,"isSignCodeLabel":"","accountBalance":0}
         * lat : 39.915889
         * terminalKey : MWu+Y+hLORPkjPw1Imlsgg==
         * lng : 116.418324
         * lotteryDepotId : 7
         * languageTypeCode : zh
         * languageId : 46
         * saleMoneySum : 1090.0
         * useStateCode : 1
         * terminalBatchId : 8
         * deployDatetime : 2017-10-30 15:23:33
         * lockCode : 0
         * deployStateCodeLabel : 已部署
         */
        public String useStateCodeLabel;
        public String deployStateCode;
        public int terminalTypeId;
        public String adress;
        public TerminalThemeEntity terminalTheme;
        public int websiteId;
        public String areaNo;
        public String deployDatetimeStr;
        public String onOffCode;
        public int proprietorId;
        public String deployTypeCodeLabel;
        public int id;
        public ProprietorEntity proprietor;
        public String lat;
        public String terminalKey;
        public String lng;
        public int lotteryDepotId;
        public String languageTypeCode;
        public int languageId;
        public double saleMoneySum;
        public String useStateCode;
        public int terminalBatchId;
        public String deployDatetime;
        public String lockCode;
        public String deployStateCodeLabel;

        public class TerminalThemeEntity implements Serializable{
            /**
             * onlineDatetimeBeginStr : 2017-11-05
             * typeCodeLabel :
             * onlineDatetimeBegin : 2017-11-05 00:00:00
             * onlineDatetimeEnd : 2017-12-09 00:00:00
             * name : 测试图标换
             * fileUrl : http://app.caiyingkeji.vizhuo.cn/nfs/nullnull
             * languageTypeCodeLabel :
             * id : 48
             * stateCodeLabel :
             * onlineDatetimeEndStr : 2017-12-09
             */
            public String onlineDatetimeBeginStr;
            public String typeCodeLabel;
            public String onlineDatetimeBegin;
            public String onlineDatetimeEnd;
            public String name;
            public String fileUrl;
            public String languageTypeCodeLabel;
            public int id;
            public String stateCodeLabel;
            public String onlineDatetimeEndStr;
        }

        public class ProprietorEntity implements Serializable{
            /**
             * areaOperate : {"lotteryTypeLabel":"","areaName":"北京区域","id":36}
             * id : 39
             * isSignCodeLabel :
             * accountBalance : 0.0
             */
            public AreaOperateEntity areaOperate;
            public int id;
            public String isSignCodeLabel;
            public double accountBalance;

            public class AreaOperateEntity implements Serializable{
                /**
                 * lotteryTypeLabel :
                 * areaName : 北京区域
                 * id : 36
                 */
                public String lotteryTypeLabel;
                public String areaName;
                public int id;
            }
        }
    }
}
