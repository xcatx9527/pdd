package com.Allicnce.taobaoAlliance.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:59
 * @Email 1016224774@qq.com
 * @Description
 */

public class SalersModel implements Serializable {


    /**
     * returnCode : 0000
     * terminalControlList : [{"ticketControlCodeLabel":"有权限","ticketControlCode":"1","onOffTimeControlCode":"1","onOffControlCode":"1","orderControlCode":"1","onOffControlCodeLabel":"有权限","onOffTimeControlCodeLabel":"有权限","orderControlCodeLabel":"有权限","id":14,"proprietor":{"name":"姚大大","id":21,"isSignCodeLabel":"","accountBalance":0,"account":"18513898689"},"terminalList":[{"useStateCodeLabel":"正常","terminalDepotId":1,"terminalKey":"KTZgE4yWizyOI5vKUwZwEA==","lotteryDepotId":1,"deployStateCode":"1","saleMoneySum":0,"terminalTypeId":1,"adress":"北京市海淀区复兴路29号翠微广场A座14层","useStateCode":"1","serialNo":"32xpt004","areaNo":"110108","deployTypeCode":"2","deployDatetimeStr":"2017-06-13","proprietorId":6,"deployTypeCodeLabel":"故障","id":4,"terminalBatchId":1,"deployDatetime":1497319378000,"deployStateCodeLabel":"已部署"},{"useStateCodeLabel":"正常","terminalDepotId":1,"terminalKey":"23","lotteryDepotId":1,"deployStateCode":"1","saleMoneySum":0,"terminalTypeId":1,"adress":"北京市海淀区复兴路29号翠微广场A座14层","useStateCode":"1","serialNo":"32xpt006","areaNo":"110108","deployTypeCode":"2","deployDatetimeStr":"2017-06-16","proprietorId":6,"deployTypeCodeLabel":"故障","id":6,"terminalBatchId":1,"deployDatetime":1497601692000,"deployStateCodeLabel":"已部署"}],"terminalIds":"4,6"},{"ticketControlCodeLabel":"","onOffControlCodeLabel":"","onOffTimeControlCodeLabel":"","orderControlCodeLabel":"","id":13,"proprietor":{"name":"经济","id":20,"isSignCodeLabel":"","accountBalance":0,"account":"15011550509"},"terminalList":[]},{"ticketControlCodeLabel":"","onOffControlCodeLabel":"","onOffTimeControlCodeLabel":"","orderControlCodeLabel":"","id":5,"proprietor":{"name":"王来利","id":1,"isSignCodeLabel":"","accountBalance":0,"account":"13621168489"},"terminalList":[]}]
     * returnMessage : 执行成功
     */
    public String returnCode;
    public List<TerminalControlListEntity> terminalControlList;
    public String returnMessage;

    public class TerminalControlListEntity implements Serializable {
        /**
         * ticketControlCodeLabel : 有权限
         * ticketControlCode : 1
         * onOffTimeControlCode : 1
         * onOffControlCode : 1
         * orderControlCode : 1
         * onOffControlCodeLabel : 有权限
         * onOffTimeControlCodeLabel : 有权限
         * orderControlCodeLabel : 有权限
         * id : 14
         * proprietor : {"name":"姚大大","id":21,"isSignCodeLabel":"","accountBalance":0,"account":"18513898689"}
         * terminalList : [{"useStateCodeLabel":"正常","terminalDepotId":1,"terminalKey":"KTZgE4yWizyOI5vKUwZwEA==","lotteryDepotId":1,"deployStateCode":"1","saleMoneySum":0,"terminalTypeId":1,"adress":"北京市海淀区复兴路29号翠微广场A座14层","useStateCode":"1","serialNo":"32xpt004","areaNo":"110108","deployTypeCode":"2","deployDatetimeStr":"2017-06-13","proprietorId":6,"deployTypeCodeLabel":"故障","id":4,"terminalBatchId":1,"deployDatetime":1497319378000,"deployStateCodeLabel":"已部署"},{"useStateCodeLabel":"正常","terminalDepotId":1,"terminalKey":"23","lotteryDepotId":1,"deployStateCode":"1","saleMoneySum":0,"terminalTypeId":1,"adress":"北京市海淀区复兴路29号翠微广场A座14层","useStateCode":"1","serialNo":"32xpt006","areaNo":"110108","deployTypeCode":"2","deployDatetimeStr":"2017-06-16","proprietorId":6,"deployTypeCodeLabel":"故障","id":6,"terminalBatchId":1,"deployDatetime":1497601692000,"deployStateCodeLabel":"已部署"}]
         * terminalIds : 4,6
         */
        public String ticketControlCodeLabel;
        public String ticketControlCode;
        public String onOffTimeControlCode;
        public String onOffControlCode;
        public String orderControlCode;
        public String onOffControlCodeLabel;
        public String onOffTimeControlCodeLabel;
        public String orderControlCodeLabel;
        public String id;
        public ProprietorEntity proprietor;
        public List<TerminalListEntity> terminalList;
        public String terminalIds;

        public class ProprietorEntity implements Serializable {
            /**
             * name : 姚大大
             * id : 21
             * isSignCodeLabel :
             * accountBalance : 0.0
             * account : 18513898689
             */
            public String name;
            public String id;
            public String isSignCodeLabel;
            public double accountBalance;
            public String account;
        }

    }
}
