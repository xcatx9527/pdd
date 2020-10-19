package com.Allicnce.taobaoAlliance.fragment;


import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import butterknife.Bind;
import com.Allicnce.taobaoAlliance.AppApplication;
import com.Allicnce.taobaoAlliance.R;
import com.Allicnce.taobaoAlliance.common.commonutils.AppUtils;
import com.Allicnce.taobaoAlliance.common.commonutils.NetworkUtils;
import com.Allicnce.taobaoAlliance.okgohttp.model.ApkModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * @author: ChenYang
 * @date: 2017-10-24 17:53
 * @Email 1016224774@qq.com
 * @Description 升级
 */


public class UpdateDialogFragment extends BaseDialogFragment {


    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.btn_cancle)
    Button btnCancle;
    @Bind(R.id.btn_commit)
    Button btnCommit;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.progresslable)
    TextView progresslable;
    @Bind(R.id.ll_container)
    LinearLayout llContainer;
    @Bind(R.id.ll_progresscontainer)
    LinearLayout ll_progresscontainer;

    private ApkModel apkModel;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyManager;

    @Override
    protected int getDialogResource() {
        return R.layout.update_dialog;
    }

    @Override
    public WindowManager.LayoutParams setParams(Window window) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
        return layoutParams;
    }

    @Override
    protected void initView() {
        setCancelable(false);
        if (apkModel.bean != null) {
            if (apkModel.bean.updateCode.equals("1")) {
                btnCancle.setVisibility(View.GONE);
            } else {
                btnCancle.setVisibility(View.VISIBLE);
            }
            tvContent.setText(apkModel.bean.remarks);
            tvVersion.setText(apkModel.bean.version);
        }

    }

    @Override
    public void onMessage(Bundle bundle) {
        super.onMessage(bundle);

    }

    @Override
    public void onChildClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancle:
                dismiss();
                break;
            case R.id.btn_commit:
                if (!NetworkUtils.isConnected()) {
                    showShortToast("下载失败,请检查网络...");
                    return;
                }
                if (!NetworkUtils.isWifiConnected()) {
                    AlertDialog.Builder bulder = new AlertDialog.Builder(getActivity());
                    bulder.setTitle("注意");
                    bulder.setMessage("您正在非WIFI状态下进行下载操作,继续下载可能会因为流量产生资费。");
                    bulder.setPositiveButton("继续下载", (dialog1, which) -> {
                        initDown(apkModel);
                        dismiss();
                    });
                    bulder.setNegativeButton("暂不更新", (dialog12, which) -> {
                        dismiss();
                    });
                    bulder.show();
                } else {
                    initDown(apkModel);

                }
                break;
        }
    }


    private void initDown(ApkModel apkModel) {
        initNotifyCation();
        ll_progresscontainer.setVisibility(View.VISIBLE);
        llContainer.setVisibility(View.GONE);
        if (apkModel.bean != null) {
            tvContent.setText(apkModel.bean.remarks);
        }
        this.apkModel = apkModel;

        OkGo.<File>get(apkModel.bean.fileUrl)//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new FileCallback("OkGo.apk") {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        Logger.e("finish");
                        mNotifyManager.cancel(1);
                        dismiss();
                        AppUtils.installApp(getActivity(), "apppath");
                    }

                    @Override
                    public void onError(Response<File> response) {
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        System.out.println(progress);

                        String downloadLength = Formatter.formatFileSize(AppApplication.instance, progress.currentSize);
                        String totalLength = Formatter.formatFileSize(AppApplication.instance, progress.totalSize);
                        String speed = Formatter.formatFileSize(AppApplication.instance, progress.speed);
//                        tvNetSpeed.setText(String.format("%s/s", speed));
//                        tvProgress.setText(numberFormat.format(progress.fraction));
//                        pbProgress.setMax(10000);
//                        pbProgress.setProgress((int) (progress.fraction * 10000));
                        updateProgress((int) (progress.fraction * 10000));
                    }
                });


    }


    public void initNotifyCation() {
        mNotifyManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getActivity());
        mBuilder.setContentTitle("下载更新").setSmallIcon(R.drawable.cash_icon);
    }

    private void updateProgress(int progress) {
        progressbar.setProgress(progress);
        progresslable.setText(progress + "%");
        Log.e("--^_^-->updateProgress", progress + "progress");
        mBuilder.setContentText(getString(R.string.android_auto_update_download_progress, progress)).setProgress(100, progress, false);
        Intent intent = new Intent();
        PendingIntent pendingintent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingintent);
        mNotifyManager.notify(1, mBuilder.build());
    }

    public void show(FragmentManager fragmentManager, ApkModel apkModel) {
        this.apkModel = apkModel;
        show(fragmentManager, "down");
    }


    @Override
    public void setListener() {
        btnCancle.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
    }

}
