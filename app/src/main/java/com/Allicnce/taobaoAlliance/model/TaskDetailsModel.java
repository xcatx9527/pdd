package com.Allicnce.taobaoAlliance.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @time 2018/5/18$
 * @descrition:
 */

public class TaskDetailsModel {


    /**
     * code : 0
     * data : {"gameLogo":"http://118.145.5.141:4080/shell/gameTaskApp/task/100059/cangzhijiyuan_看图王.png","gameName":"hyy测试","statusList":[{"desc":"奖励：￥2.00","status":0}],"demoImgList":["http://118.145.5.141:4080/shell/gameTaskApp/task/100059/微信图片_201803201259213.jpg","http://118.145.5.141:4080/shell/gameTaskApp/task/100059/微信图片_201803201259214.jpg","http://118.145.5.141:4080/shell/gameTaskApp/task/100059/微信图片_201803201259215.jpg"],"gameLink":"http://uri6.com/tkio/eUb2uya","availableFlag":0,"gamePackage":"com.baidu.tieba","rewardAmount":"2.00","type":0,"taskId":100059}
     * desc : 成功
     */
    public int code;
    public DataEntity data;
    public String desc;

    public class DataEntity {
        /**
         * gameLogo : http://118.145.5.141:4080/shell/gameTaskApp/task/100059/cangzhijiyuan_看图王.png
         * gameName : hyy测试
         * statusList : [{"desc":"奖励：￥2.00","status":0}]
         * demoImgList : ["http://118.145.5.141:4080/shell/gameTaskApp/task/100059/微信图片_201803201259213.jpg","http://118.145.5.141:4080/shell/gameTaskApp/task/100059/微信图片_201803201259214.jpg","http://118.145.5.141:4080/shell/gameTaskApp/task/100059/微信图片_201803201259215.jpg"]
         * gameLink : http://uri6.com/tkio/eUb2uya
         * availableFlag : 0
         * gamePackage : com.baidu.tieba
         * rewardAmount : 2.00
         * type : 0
         * taskId : 100059
         */
        public String gameLogo;
        public String gameName;
        public List<StatusListEntity> statusList;
        public ArrayList<String> demoImgList;
        public String gameLink;
        public int availableFlag;
        public String gamePackage;
        public String rewardAmount;
        public int type;
        public int taskId;
        public String orderId;
        public ArrayList<Integer> amountList;

        public class StatusListEntity {
            /**
             * desc : 奖励：￥2.00
             * status : 0
             */
            public String desc;
            public int status;
        }
    }
}
