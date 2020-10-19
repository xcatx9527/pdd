package com.Allicnce.taobaoAlliance.request;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:06
 * @Email 1016224774@qq.com
 * @Description
 */
public class CanSellRequest extends BaseRequest {

    public LotteryDepotQuery lotteryDepotQuery;

    public CanSellRequest() {
        this.lotteryDepotQuery = new LotteryDepotQuery();
    }

    public  class LotteryDepotQuery {
        public String id;
    }

}
