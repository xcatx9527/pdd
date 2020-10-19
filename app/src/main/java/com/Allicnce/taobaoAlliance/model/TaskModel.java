package com.Allicnce.taobaoAlliance.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:59
 * @Email 1016224774@qq.com
 * @Description
 */

public class TaskModel implements Serializable {

    /**
     * code : 0
     * taskList : [{"gameLogo":"http://118.145.5.141:4080/shell/gameTaskApp/task/logo1.png","gameName":"至尊裁决","rewardAmount":"1.50","type":0,"taskId":100040},{"gameLogo":"http://118.145.5.141:4080/shell/gameTaskApp/task/logo1.png","gameName":"至尊裁决","amountList":[6,30,68],"rewardAmount":"1.50","type":1,"taskId":100042},{"gameLogo":"http://118.145.5.141:4080/shell/gameTaskApp/task/logo1.png","gameName":"至尊裁决","rewardAmount":"1.50","type":0,"taskId":100044},{"gameLogo":"http://118.145.5.141:4080/shell/gameTaskApp/task/logo1.png","gameName":"至尊裁决","amountList":[6,30,68],"rewardAmount":"1.50","type":1,"taskId":100046}]
     * desc : 成功
     */
    public int code;
    public List<TaskListEntity> taskList;
    public String desc;

    public class TaskListEntity {
        /**
         * gameLogo : http://118.145.5.141:4080/shell/gameTaskApp/task/logo1.png
         * gameName : 至尊裁决
         * amountList : [6,30,68]
         * rewardAmount : 1.50
         * type : 1
         * taskId : 100042
         */
        public String gameLogo;
        public String gameName;
        public String gamePackage;
        public List<Integer> amountList;
        public String rewardAmount;
        public int type;
        public String taskId;
    }
}
