package com.Allicnce.taobaoAlliance.utils;

import com.orhanobut.logger.Logger;
import com.Allicnce.taobaoAlliance.AppApplication;
import com.Allicnce.taobaoAlliance.model.LoginModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: ChenYang
 * @date: 2017-10-24 18:06
 * @Email 1016224774@qq.com
 * @Description
 */

public class Object2SdCardUtils {


    public static LoginModel userinfo;

    /**
     * 从本地获取该对象
     */
    public static LoginModel getObjectFormSdCard() {
        userinfo = null;
        File file = new File(AppApplication.instance.getFilesDir().getAbsolutePath() + "/userinfo");
        if (!file.isFile()) {
            return null;
        }
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            userinfo = (LoginModel) objIn.readObject();
            objIn.close();
            Logger.v("读取对象成功!");
        } catch (IOException e) {
            Logger.v("读取对象失败!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userinfo;
    }

    /**
     * 将对象保存到本地
     */
    public static void writeObjectToSdCard(Object obj) {
        File file = new File(AppApplication.instance.getFilesDir().getAbsolutePath() + "/userinfo");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
//            Logger.v("写入对象成功");
        } catch (IOException e) {
//            Logger.v("写入对象失败");
            e.printStackTrace();
        }
    }

    /**
     * 删除本地对象
     */
    public static boolean DelateObjectToSdCard() {
        File file = new File(AppApplication.instance.getFilesDir().getAbsolutePath() + "/userinfo");
        userinfo = null;
        if (file.exists()) {
            if (file.delete()) {
//                Logger.v("对象删除成功");
                return true;
            }
            return false;
        } else {
//            Logger.e("没有对象");
            return false;
        }
    }
}
