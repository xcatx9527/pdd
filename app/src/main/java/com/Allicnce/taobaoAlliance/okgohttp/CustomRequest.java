package com.Allicnce.taobaoAlliance.okgohttp;


import android.util.Log;
import com.Allicnce.taobaoAlliance.common.commonutils.EncryptUtils;
import com.Allicnce.taobaoAlliance.common.commonutils.GsonUtils;
import com.Allicnce.taobaoAlliance.model.EncryptData;
import com.Allicnce.taobaoAlliance.request.BaseRequest;
import com.Allicnce.taobaoAlliance.utils.Object2SdCardUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;

import java.util.HashMap;
import java.util.Random;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:02
 * @Email 1016224774@qq.com
 * @Description
 */
public class CustomRequest {


    public static String getReplyFromService(BaseRequest model) {
        String[] interfereOperatorArr = new String[]{"a", "D", "3", "f", "G", "c", "1", "6", "M", "H", "8", "h", "w", "E"};
        String interfereOperator = "";//干扰符
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            interfereOperator += interfereOperatorArr[random.nextInt(interfereOperatorArr.length)];
        }
        String json = GsonUtils.toJson(model);
        EncryptData ed = new EncryptData();
        ed.data = interfereOperator + EncryptUtils.encryptBASE64(json).replace("\n", "");//base64加密后request
        ed.key = EncryptUtils.md5Encrypt(ed.data + "caiying-786a543");

        Log.e("vizhuorequest", "-->" + json);
        return GsonUtils.toJson(ed);
    }

    public static <K extends BaseRequest> void get(K request, String url, Callback callback) {
        String params = getReplyFromService(request);
        GetRequest getRequest = OkGo.get(url);//
        if (Object2SdCardUtils.userinfo != null) {
//            getRequest.headers("Cookie", "JSESSIONID=" + Object2SdCardUtils.userinfo.loginToken);
        }
        getRequest.tag(url)//PostRequest
                .params("req", params)
                .execute(callback);

    }

    public static <K extends BaseRequest> void post(K request, String url, Callback callback) {
        String params = getReplyFromService(request);
        PostRequest getRequest = OkGo.post(url);//
        if (Object2SdCardUtils.userinfo != null) {
//            getRequest.headers("Cookie", "JSESSIONID=" + Object2SdCardUtils.userinfo.loginToken);
        }
        getRequest.tag(url)//PostRequest
                .params("req", params)
                .execute(callback);

    }

    public static <K extends BaseRequest> void GetNoSecret(K request, String url, Callback callback) {
        GetRequest getRequest = OkGo.get(url);//
        getRequest.tag(url)//PostRequest
                .execute(callback);

    }

    public static void PostNoSecret(HashMap<String, String> params, String url, Callback callback) {
        Log.e("Request---->", "\n" + GsonUtils.toJson(params));
        GetRequest getRequest = OkGo.get(url);//
        getRequest.tag(url)
                .params(params, true)
                .execute(callback);
    }
}
