package com.Allicnce.taobaoAlliance.request;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:07
 * @Email 1016224774@qq.com
 * @Description
 */
public class SalesRequest extends BaseRequest{

    /**
     * pager : {"pageSize":10,"currentPage":1}
     * lotteryOrderQuery : {"priceArr":[5,10],"createDatetimeEnd":"2019-01-01","terminalIdArr":[17000001,17000002],"payTypeCodeArr":["0","1"],"createDatetimeBegin":"2017-01-01"}
     */
    public PagerEntity pager=new PagerEntity();
    public LotteryOrderQueryEntity lotteryOrderQuery=new LotteryOrderQueryEntity();

    public class PagerEntity implements Serializable {
        /**
         * pageSize : 10
         * currentPage : 1
         */
        public int pageSize;
        public int currentPage;
    }

    public class LotteryOrderQueryEntity implements Serializable {
        /**
         * priceArr : [5,10]
         * createDatetimeEnd : 2019-01-01
         * terminalIdArr : [17000001,17000002]
         * payTypeCodeArr : ["0","1"]
         * createDatetimeBegin : 2017-01-01
         */
        public List<String> priceArr;
        public String createDatetimeEnd;
        public List<String> terminalIdArr;
        public List<String> payTypeCodeArr;
        public String createDatetimeBegin;
    }
}
