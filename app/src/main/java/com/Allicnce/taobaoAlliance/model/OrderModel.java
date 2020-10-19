package com.Allicnce.taobaoAlliance.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:58
 * @Email 1016224774@qq.com
 * @Description
 */
public class OrderModel implements Serializable {

    public String returnCode;
    public List<LotteryOrderListEntity> lotteryOrderList;
    public String returnMessage;

    public class LotteryOrderListEntity implements Serializable {

        public String orderNo;
        public String appendLabel;
        public String createDatetimeNYRStr;
        public int totalMoney;
        public String lotteryGameTypeCodeLabel;
        public String createDatetimeStr;
        public int terminalId;
        public TerminalEntity terminal;
        public String stateCodeLabel;
        public String createDatetime;
        public String payTypeCode;
        public String lotteryTypeCode;
        public String lotteryPeriodId;
        public String payTypeCodeLabel;
        public String buyTypeCode;
        public String lotteryTypeCodeLabel;
        public int proprietorId;
        public String stateCode;
        public String id;
        public ProprietorEntity proprietor;
        public String status;
        public List<LotteryOrderDetailListEntity> lotteryOrderDetailList;
        public String lotteryGameTypeCode;
        public int betNum;
        public int multiple;
        public String append;
        public String codeData;
        public String betContentRed;
        public String betContentBlue;


        public class TerminalEntity implements Serializable {

            public String useStateCodeLabel;
            public String deployDatetimeStr;
            public String saleMoneySum;
            public String deployTypeCodeLabel;
            public int id;
            public String deployStateCodeLabel;
        }

        public class ProprietorEntity implements Serializable {

            public String name;
            public AreaOperateEntity areaOperate;
            public int id;
            public String isSignCodeLabel;
            public String accountBalance;
            public String account;

            public class AreaOperateEntity implements Serializable {

                public String lotteryTypeLabel;
                public String areaName;
                public int id;
            }
        }

        public class LotteryOrderDetailListEntity implements Serializable {

            public GameEntity game;
            public String orderId;
            public String appendLabel;
            public String betContentBlue;
            public int price;
            public int count;
            public String betTypeCodeLabel;
            public String stateCode;
            public int id;
            public String stateCodeLabel;
            public String betContentRed;
            public String multiple;

            public class GameEntity implements Serializable {

                public String saleStopDate2String;
                public String picSmallUrl;
                public int ticketHeight;
                public String picName;
                public String saleStopDate;
                public String prizeClosingDate;
                public String picUrl;
                public String picSmallName;
                public String lotteryTypeCode;
                public int price;
                public String gameDes;
                public String marketDate2String;
                public boolean checked;
                public String lotteryTypeCodeLabel;
                public String marketDate;
                public String id;
                public int maxBonus;
                public int pricePackage;
                public String prizeClosingDate2String;
                public String stateCodeLabel;
                public String delistDate;
                public String picPath;
                public String name;
                public String stateCode;
                public String underDate2String;
                public String picSmallPath;
            }
        }
    }
}
