package com.Allicnce.taobaoAlliance.okgohttp.callback;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.Allicnce.taobaoAlliance.AppApplication;
import com.Allicnce.taobaoAlliance.activity.login.LoginActivity;
import com.Allicnce.taobaoAlliance.common.base.BaseMethodInterface;
import com.Allicnce.taobaoAlliance.common.commonutils.GsonUtils;
import com.Allicnce.taobaoAlliance.common.commonutils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: ChenYang
 * @date: 2017-10-24 11:23
 * @Email 1016224774@qq.com
 * @Description
 */
public abstract class ModelCallBack<T> implements Callback<T> {

    private int type = -1;
    public static int SHOWLOADING = 0;//加载dialog
    public static int SHOWMESSAGE = 1;//toast
    public static int SHOWSTATEVIEW = 2;//状态页或者不显示Dialog和message
    private BaseMethodInterface mContext;

    public ModelCallBack(BaseMethodInterface context, int type) {
        this.type = type;
        this.mContext = context;
    }

    public ModelCallBack(BaseMethodInterface context) {
        this.mContext = context;
    }

    public ModelCallBack() {
    }
/*0	成功
-1	Session失效，app返回登陆界面
-2	时时获取的设备信息与最后一次登陆时上传的设备信息不符，出现异常，app停留当前页面，当作没有数据即可。
            -3	参数错误
-4	已在其他设备登录
-5	邀请码错误
-6	没有找到用户
-7	没有找到任务
-8	已派发完|不在白名单
-9	已执行过该任务*/

    private void showErrorMessage(JsonObject jsonObject) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        if (mainHandler == null) {
            Log.e("showErrorMessage", "获取mainhandler失败");
            return;
        }
        int a = jsonObject.get("code").getAsInt();
        switch (a) {
            case 0:
                break;
            case -1:
                mainHandler.post(() -> {
                    Intent intent = new Intent(AppApplication.instance, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppApplication.instance.startActivity(intent);
                });
                return;
            case -2:
                mainHandler.post(() -> {
                    Intent intent = new Intent(AppApplication.instance, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppApplication.instance.startActivity(intent);
                });
                break;
            case -3:

                break;
            case -4:
                mainHandler.post(() -> {
                    Intent intent = new Intent(AppApplication.instance, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppApplication.instance.startActivity(intent);
                });
                break;
            case -5:

                break;
            case -6:
                mainHandler.post(() -> {
                    Intent intent = new Intent(AppApplication.instance, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppApplication.instance.startActivity(intent);
                });
                break;
            case -7:

                break;
            case -8:

                break;
            case -9:

                break;
            default:

        }

        if (jsonObject.get("code").getAsInt() != 0) {
            mainHandler.post(() -> Toast.makeText(AppApplication.instance, jsonObject.get("desc").getAsString(), Toast.LENGTH_SHORT).show());
            return;
        }
    }


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (mContext != null) {
            if (type == SHOWSTATEVIEW) {
                mContext.hideStateView();
            }
            if (type == SHOWLOADING) {
                mContext.showLoading();
            }
        }
    }

    @Override
    public void onSuccess(Response<T> response) {
        if (mContext != null) {
            mContext.showEmptyView();
            if (type == SHOWLOADING) {
                mContext.hideLoading();
            }
        }
    }

    @Override
    public void onCacheSuccess(Response<T> response) {

    }

    @Override
    public void onError(Response<T> response) {
        if (type == SHOWMESSAGE) {
            Toast.makeText(AppApplication.instance, "网络连接异常", Toast.LENGTH_SHORT).show();
        }
        if (mContext != null) {

            if (type == SHOWSTATEVIEW || !NetworkUtils.isConnected()) {
                mContext.showErrorView();
            }
            if (type == SHOWLOADING) {
                mContext.hideLoading();
            }
        }
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        //DialogCallback<LzyResponse<ServerModel>> 得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        Type type = params[0];
        String data = response.body().string();
        T baseResponse = GsonUtils.fromJson(data, type);
        response.close();
        JsonObject jsonObject = new Gson().fromJson(data, JsonObject.class);
        Log.e("Response---->", "\n" + data);
        showErrorMessage(jsonObject);
        return baseResponse;
    }


}
