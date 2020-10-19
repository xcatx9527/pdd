package com.Allicnce.taobaoAlliance.okgohttp.model;

import java.io.Serializable;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:00
 * @Email 1016224774@qq.com
 * @Description
 */
public class ApkModel implements Serializable{


    /**
     * returnCode : 0000
     * returnMessage : 执行成功
     * bean : {"fileName":"hlvan_siji.apk","effectiveDatetime":"2015-10-20 11:30:21","creatorName":"管理员","version":"1.1.2","typeCode":90,"createDatetime":"2015-10-20 11:30:25","fileHttpUrl":"http://10.0.0.235:8801/nfs//appDownload/1.1.1/hlvan_siji.apk","name":"hlvan_siji","flagNew":1,"fileURL":"/appDownload/1.1.1/","force":true,"id":126,"remarks":"1.1.1"}
     */
    public String returnCode;
    public String returnMessage;
    public BeanEntity bean=new BeanEntity();


    public class BeanEntity implements Serializable {
        public int createUserId;
        public String fileName;
        public String fileHttpUrl;
        public String name;
        public String updateCode;
        public String typeCodeLabel;
        public String createDatetimeNYRStr;
        public String filePath;
        public String updateCodeLabel;
        public String createDatetimeStr;
        public String version;
        public String stateCodeLabel;
        public int typeCode;
        public String lastModifyDatetimeStr;
        public String createDatetime;
        public String terminalTypeCodeLabel;
        public int lastModifyUserId;
        public String lastModifyDatetime;
        public String stateCode;
        public String fileUrl;
        public int id;
        public String remarks;
        public String status;
    }

}
