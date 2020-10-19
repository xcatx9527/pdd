package com.Allicnce.taobaoAlliance.request;

import com.Allicnce.taobaoAlliance.model.Pager;

import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:07
 * @Email 1016224774@qq.com
 * @Description
 */

public class SalesTjRequest extends BaseRequest {

    /**
     * pager : {"pageSize":10,"currentPage":1}
     * lotteryOrderQuery : {"terminalIds ":"1,2","payTimeEnd":"2019-01-01","payTimeBegin":"2017-01-01"}
     */
    public Pager pager;
    public LotteryOrderQueryEntity lotteryOrderQuery;

    public SalesTjRequest() {
        this.pager = new Pager();
        lotteryOrderQuery = new LotteryOrderQueryEntity();
    }

    public class LotteryOrderQueryEntity {
        /**
         * terminalIds  : 1,2
         * payTimeEnd : 2019-01-01
         * payTimeBegin : 2017-01-01
         */
        public List<String> terminalIds;
        public String payTimeEnd;
        public String payTimeBegin;
    }
}
