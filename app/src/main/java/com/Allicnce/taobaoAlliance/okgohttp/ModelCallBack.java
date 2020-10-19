package com.Allicnce.taobaoAlliance.okgohttp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.Allicnce.taobaoAlliance.AppApplication;
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

    private void showErrorMessage(JsonObject jsonObject) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        if (mainHandler == null) {
            Log.e("showErrorMessage", "获取mainhandler失败");
            return;
        }
        int a = jsonObject.get("code").getAsInt();
        //重新登录
        switch (a) {
            case 0:
                break;
            case -1:

                return;
            case -2:

                break;
            case -3:

                break;
            case -4:

                break;
            case -5:

                break;
            case -6:

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
        Log.e("Response---->", data);
        showErrorMessage(jsonObject);
        return baseResponse;
    }


}
